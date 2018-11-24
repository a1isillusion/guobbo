package netty;

import com.alibaba.fastjson.JSON;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import pojo.RpcRequest;
import pojo.RpcResponse;

public class RPCClient extends ChannelInboundHandlerAdapter{
public String ip;
public int port;
public RpcRequest request;
public RpcResponse response;
public RPCClient(String ip,int port,RpcRequest request) {
	this.ip=ip;
	this.port=port;
	this.request=request;
}
public RPCClient(String address,RpcRequest request) {
	String[] ipport=address.split(":");
	this.ip=ipport[0];
	System.out.println("ip"+this.ip);
	this.port=Integer.parseInt(ipport[1]);
	System.out.println("port"+port);
	this.request=request;
}
public void invoke() {
	    EventLoopGroup group = new NioEventLoopGroup();
	    Bootstrap b = new Bootstrap();
	    b.group(group)
	        .channel(NioSocketChannel.class)
	        .handler(new ChannelInitializer<SocketChannel>() {
	          @Override
	          public void initChannel(SocketChannel ch) throws Exception {
	            ChannelPipeline p = ch.pipeline();
	            p.addLast(new RpcDecoder(RpcResponse.class));
	            p.addLast(new RpcEncoder(RpcRequest.class));
	            p.addLast(RPCClient.this);
	          }
	        });
	    try {
	      b.connect(ip,port).sync().channel().closeFuture().sync();
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }  finally {   
	  	  group.shutdownGracefully();  
	    } 
}
@Override
public void channelRead(ChannelHandlerContext ctx, Object response) {
  System.out.println("收到"+response.toString());
  this.response=(RpcResponse)response;
  ctx.close();	
}
@Override    
public void channelActive(ChannelHandlerContext ctx) throws Exception {    
	System.out.println("active");
	System.out.println(JSON.toJSONString(request));
    ctx.writeAndFlush(request); 
    System.out.println("active");
}  
}

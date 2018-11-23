package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import pojo.RpcRequest;
import pojo.RpcResponse;

public class RPCClient extends SimpleChannelInboundHandler<RpcResponse>{
public String ip;
public int port;
public RpcResponse response;
public RPCClient(String ip,int port) {
	this.ip=ip;
	this.port=port;
}
public void invoke(RpcRequest request) {
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
	            p.addLast(this);
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
protected void channelRead0(ChannelHandlerContext ctx, RpcResponse response) throws Exception {
	   this.response=response;
	   ctx.close();	
}
}

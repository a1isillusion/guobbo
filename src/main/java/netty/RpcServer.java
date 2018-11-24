package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import pojo.RpcRequest;
import pojo.RpcResponse;

public class RpcServer extends ChannelInboundHandlerAdapter {
	
public void bind(int port) {
	 NioEventLoopGroup bosseventLoopGroup = new NioEventLoopGroup();
	    NioEventLoopGroup childeventLoopGroup = new NioEventLoopGroup();
		try {
	        ServerBootstrap bootstrap = new ServerBootstrap();
	        bootstrap.group(bosseventLoopGroup,childeventLoopGroup)
	            .channel(NioServerSocketChannel.class)
	            .childHandler(new ChannelInitializer<SocketChannel>() {
	              @Override
	              protected void initChannel(SocketChannel ch) throws Exception {
	                ChannelPipeline p = ch.pipeline();
	                p.addLast(new RpcDecoder(RpcRequest.class));
	                p.addLast(new RpcEncoder(RpcResponse.class));
	                p.addLast(RpcServer.this);
	              }
	            });
	        bootstrap.bind(port).sync().channel().closeFuture().sync();
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      } finally {  
	          // Shut down the event loop to terminate all threads.  
	    	  bosseventLoopGroup.shutdownGracefully();  
	    	  childeventLoopGroup.shutdownGracefully();
	      }  
}
@Override
public void channelRead(ChannelHandlerContext ctx, Object requset) {
	RpcResponse response=new RpcResponse();
	response.setRequestId("tiyubusimama");
	response.setResult("tiyubusb");
	ctx.writeAndFlush(response);
}
}

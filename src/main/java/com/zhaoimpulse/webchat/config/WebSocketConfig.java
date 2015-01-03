package com.zhaoimpulse.webchat.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration //这是一个配置文件
@EnableWebSocketMessageBroker //激活websocket消息代理
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
        //匹配消息代理的url前缀
		config.enableSimpleBroker("/ws","/user");
        //
		config.setApplicationDestinationPrefixes("/ws");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
        //配置sockjs连接的入口
		registry.addEndpoint("/ws").withSockJS();
	}
}

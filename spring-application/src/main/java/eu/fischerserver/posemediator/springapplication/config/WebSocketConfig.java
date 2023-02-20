package eu.fischerserver.posemediator.springapplication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private static final String SEND_PREFIX = "/topic";
    private static final String RECEIVE_PREFIX = "/app";

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(SEND_PREFIX);
        config.setApplicationDestinationPrefixes(RECEIVE_PREFIX);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/pm-websocket").withSockJS();
    }

    public static String getSendTopicPath(String topic) {
        return "%s/%s".formatted(SEND_PREFIX, topic);
    }
}

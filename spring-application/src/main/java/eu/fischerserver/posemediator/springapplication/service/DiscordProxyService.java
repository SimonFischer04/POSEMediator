package eu.fischerserver.posemediator.springapplication.service;

import eu.fischerserver.posemediator.springapplication.model.DiscordRpcConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.service.annotation.PutExchange;

public interface DiscordProxyService {
    @GetMapping("/toggle")
    void toggleMute();

    @PutExchange("/login")
    DiscordRpcConfig login(DiscordRpcConfig config);
}

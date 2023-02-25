package eu.fischerserver.posemediator.springapplication.service;

import eu.fischerserver.posemediator.springapplication.model.DiscordRpcConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PutExchange;

public interface DiscordProxyService {
    @GetExchange("/toggle")
    void toggleMute();

    @SuppressWarnings("UnusedReturnValue")
    @PutExchange("/login")
    ResponseEntity<DiscordRpcConfig> login(@RequestBody DiscordRpcConfig config);
}

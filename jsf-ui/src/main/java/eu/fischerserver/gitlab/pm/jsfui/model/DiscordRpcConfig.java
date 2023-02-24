package eu.fischerserver.gitlab.pm.jsfui.model;

import lombok.Builder;

import java.util.Optional;

@Builder
public record DiscordRpcConfig(
        String clientId,
        String clientSecret,
        Optional<String> accessToken,
        Optional<String> refreshToken,
        Optional<String> redirectUrl,
        int expireTime
) {
    public DiscordRpcConfig(String clientId, String clientSecret) {
        this(clientId, clientSecret, Optional.empty(), Optional.empty(), Optional.empty(), -1);
    }
}

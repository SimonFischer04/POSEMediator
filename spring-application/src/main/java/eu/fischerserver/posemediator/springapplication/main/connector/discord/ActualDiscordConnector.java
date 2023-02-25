package eu.fischerserver.posemediator.springapplication.main.connector.discord;

import eu.fischerserver.posemediator.springapplication.exception.InvalidDiscordConfigException;
import eu.fischerserver.posemediator.springapplication.main.Mediator;
import eu.fischerserver.posemediator.springapplication.service.CredentialService;
import eu.fischerserver.posemediator.springapplication.service.DiscordProxyService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Service
@RequiredArgsConstructor
public class ActualDiscordConnector implements DiscordConnector {
    private final CredentialService credentialService;
    @SuppressWarnings("FieldCanBeLocal")
    private Mediator mediator;
    private DiscordProxyService discordProxy;

    // IMPROVEMENT: get from API
    private boolean isMuted = false;

    private boolean discordConnected = false;

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public boolean isMuted() {
        return isMuted;
    }

    @Override
    public void setMuted(boolean muted) {
        this.isMuted = muted;

        // behave like dummy-discord when discord not available
        if (discordConnected)
            discordProxy.toggleMute();
        else
            System.err.println("WANING: Discord not Connected - playing dummy service");
    }

    private void onChange() {
        // IMPROVEMENT:
        // when the actual mute state in discord changes, f.e. the user has manually clicked the mute button in discord
        // for illustration purposes only.
        // mediator.onDiscordChange(/* DISCORD Event Data */);
    }

    @PostConstruct
    private void init() {
        var client = WebClient.builder().baseUrl("http://localhost:3000/api/proxy").build();
        var factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
        this.discordProxy = factory.createClient(DiscordProxyService.class);
        try {
            this.login();
        } catch (InvalidDiscordConfigException ignored) {
        }
    }

    @Override
    public void login() throws InvalidDiscordConfigException {
        if (!credentialService.hasValidDiscordConfig()) {
            System.err.println("WARNING: Currently no valid discord config available");
            throw new InvalidDiscordConfigException(credentialService.getDiscordConfig().orElse(null));
        }

        //noinspection OptionalGetWithoutIsPresent
        var config = credentialService.getDiscordConfig().get();

        try {
            var authenticatedConfig = discordProxy.login(config);
            // save config with refreshToken, ...
            credentialService.saveDiscordConfig(authenticatedConfig.getBody());
            discordConnected = true;
        } catch (Exception ignored) {
            System.out.println();
        }
    }
}

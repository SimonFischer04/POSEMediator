package eu.fischerserver.posemediator.springapplication;

import eu.fischerserver.posemediator.springapplication.model.Credential;
import eu.fischerserver.posemediator.springapplication.service.CredentialService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        int port = 42000;
        if (args.length > 0 && args[0].matches("\\d*]")) {
            port = Integer.parseInt(args[0]);
        }

        SpringApplication app = new SpringApplication(Application.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", port));
        app.run(args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            CredentialService credentialService
    ) {
        return args -> {
            if (!credentialService.existsByKey("test")) {
                credentialService.save(new Credential("test", "A VerySecret Test-Value"));
            }
            System.out.println();
        };
    }
}

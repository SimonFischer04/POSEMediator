package eu.fischerserver.posemediator.springapplication.controller;

import eu.fischerserver.posemediator.springapplication.exception.InvalidDiscordConfigException;
import eu.fischerserver.posemediator.springapplication.model.PMData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/remote")
public interface RemoteController {
    @PutMapping("toggleMute")
    void toggleMute();

    @GetMapping("current")
    PMData getCurrentState();

    @PutMapping("discord/login")
    @Operation(summary = "login to discord", description = "try login in into discord using the credentials stored server-side")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "510", description = "config on server invalid", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = InvalidDiscordConfigException.InvalidDiscordConfigExceptionProblemDetail.class))})
    })
    void loginToDiscord();
}

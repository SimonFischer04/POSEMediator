package eu.fischerserver.posemediator.springapplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/debug")
public class DebugController {
    @GetMapping
    public void debug(){
        System.out.println("debug");
    }
}

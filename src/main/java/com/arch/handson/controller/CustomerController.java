package com.arch.handson.controller;

import com.arch.handson.enums.StateMachine;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class CustomerController {

    Logger slf4jLogger = LoggerFactory.getLogger(this.getClass());
    org.apache.logging.log4j.Logger log4jLogger = LogManager.getLogger();

    @GetMapping("/hello")
    public String list(@RequestParam(name = "name", defaultValue = "world") String name) {
        StateMachine state = StateMachine.Approved;
        log4jLogger.info("Hello, {} [State: {} - log4j]", name, state);
        slf4jLogger.info("Hello, {} [State: {} - slf4j]", name, state);
        return String.format("Hello, %s [State: %s]", name, state);
    }

    @PostMapping("/go")
    public ResponseEntity go(@RequestBody ObjectNode body) throws URISyntaxException {
        RequestEntity request = new RequestEntity(HttpMethod.GET, new URI(body.get("url").textValue()));
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.exchange(request, String.class);
        slf4jLogger.info("Response Status is {}",  response.getStatusCode());
        return response;
    }

    @GetMapping("/log")
    private ResponseEntity log() {
        slf4jLogger.trace("TRACE     [slf4j]");
        slf4jLogger.debug("DEBUG     [slf4j]");
        slf4jLogger.info( "INFO      [slf4j]");
        slf4jLogger.warn( "WARN      [slf4j]");
        slf4jLogger.error("ERROR     [slf4j]");
        System.out.println("*****************************");
        log4jLogger.trace("TRACE     [log4j]");
        log4jLogger.debug("DEBUG     [log4j]");
        log4jLogger.info( "INFO      [log4j]");
        log4jLogger.warn( "WARN      [log4j]");
        log4jLogger.error("ERROR     [log4j]");
        log4jLogger.fatal("FATAL     [log4j]");
        System.out.println("-----------------------------");
        System.out.println("");
        return ResponseEntity.ok("success");
    }

}

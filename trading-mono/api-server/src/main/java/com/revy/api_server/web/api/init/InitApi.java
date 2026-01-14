package com.revy.api_server.web.api.init;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/init")
public class InitApi {

    @GetMapping
    public String init() {
        return "init";
    }
}

package com.codemind.gitsample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String index2() {
        // 1
        return "Hello index page~";
    }
}

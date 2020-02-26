package org.lamisplus.base.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TomcatController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Emeka from Tomcat ";
    }
}
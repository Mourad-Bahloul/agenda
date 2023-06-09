package com.hc.agenda.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

    @GetMapping("/test1")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("hello from secured end Point");
    }

    @GetMapping("/test2")
    public ResponseEntity<String> test2(){
        return ResponseEntity.ok("hello from secured end Point 2");
    }
}

package com.mist.corps.chat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomController {

    // value is the endpoint in the URL | example: localhost:8080/dog to redirect to this
    @GetMapping("/dog")
    public String sendMessage() {
        return "Yow";
    }

}

package com.wanger.example.webfluxkotlin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simple")
public class SimpleController {
    @RequestMapping(value = "/printContent", method = {RequestMethod.GET, RequestMethod.POST})
    public String printContent(String content){
        return String.format("Received java content : %s", content);
    }
}

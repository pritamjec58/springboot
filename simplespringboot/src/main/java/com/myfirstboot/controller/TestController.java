package com.myfirstboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
@RequestMapping("/p1")
public String home() {
return "Spring boot is working!";
}
}
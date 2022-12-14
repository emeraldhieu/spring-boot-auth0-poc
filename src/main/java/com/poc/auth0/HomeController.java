package com.poc.auth0;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    @ResponseBody
    public String home() {
        return "Home is empty. You may want to call /login";
    }
}

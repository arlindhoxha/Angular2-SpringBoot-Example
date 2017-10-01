package com.ah.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Arlind Hoxha on 7/10/2017.
 */

@RestController
public class HelloRestController {

    @RequestMapping("/api/hello")
    public String greet() {
        return "Hello using REST";
    }
}

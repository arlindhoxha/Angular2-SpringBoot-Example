package com.ah.session;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by Arlind Hoxha on 3/23/2018.
 */

@RestController
public class LoginController {

    @GetMapping("/user")
    @ResponseBody
    public Principal user(Principal user) {
        return user;
    }
}

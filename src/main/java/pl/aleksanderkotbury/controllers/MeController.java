package pl.aleksanderkotbury.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

@RestController
public class MeController {

    @GetMapping("/me")
    public Principal user(Principal principal) {
        return principal;
    }
}

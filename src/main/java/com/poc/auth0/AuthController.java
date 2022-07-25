package com.poc.auth0;

import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.Tokens;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final SecurityConfiguration config;
    private final AuthenticationController authenticationController;

    @GetMapping(value = "/login")
    protected void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String redirectUri = config.getCallbackUrl();
        String authorizeUrl = authenticationController.buildAuthorizeUrl(request, response, redirectUri)
            .withScope("openid email")
            .build();
        response.sendRedirect(authorizeUrl);
    }

    @GetMapping(value = "/callback")
    public void loginCallback(HttpServletRequest request, HttpServletResponse response) throws IdentityVerificationException, IOException {
        Tokens tokens = authenticationController.handle(request, response);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    /**
     * This endpoint is called if you configure "Allowed Logout URLs" in Auth0's app.
     */
    @GetMapping(value = "/logoutCallback")
    @ResponseBody
    public String logoutCallback() {
        return "You have just logged out of Auth0";
    }
}
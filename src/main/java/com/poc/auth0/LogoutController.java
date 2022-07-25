package com.poc.auth0;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class LogoutController implements LogoutSuccessHandler {

    private final SecurityConfiguration config;

    @Override
    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) {
        if (req.getSession() != null) {
            req.getSession().invalidate();
        }
        String logoutCallbackUrl = config.getLogoutCallbackUrl();
        String logoutUrl = config.getLogoutUrl() + "?client_id=" + config.getClientId() + "&returnTo=" + logoutCallbackUrl;
        try {
            res.sendRedirect(logoutUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
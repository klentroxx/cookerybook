package hu.cookerybook.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/kijelentkezes")
public class LogoutController {

    @GetMapping
    @PostMapping
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/");
        HttpSession session = request.getSession();
        session.removeAttribute("user_uid");
        session.removeAttribute("user_username");
        session.removeAttribute("user_email");
        session.removeAttribute("user_first_name");
        session.removeAttribute("user_last_name");
        session.removeAttribute("user_user_role");
        session.invalidate();
    }

}

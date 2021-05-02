package hu.cookerybook.web.controller;

import hu.cookerybook.core.dao.UserDAO;
import hu.cookerybook.core.dao.UserDAOImpl;
import hu.cookerybook.core.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/bejelentkezes")
public class LoginController {

    @GetMapping
    public String loginPage() {
        return "pages/login";
    }

    @PostMapping
    public void loginProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.validateUser(email, password);

        if (user.getId() == -1) {
            response.sendError(200, "Ilyen felhasználó nem létezik. Próbáld meg újra!");
        } else if (user.getId() == -2) {
            response.sendError(200, "A jelszó hibás. Próbáld meg újra!");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user_uid", user.getId());
            session.setAttribute("user_username", user.getUsername());
            session.setAttribute("user_email", user.getEmail());
            session.setAttribute("user_first_name", user.getFirstName());
            session.setAttribute("user_last_name", user.getLastName());
            session.setAttribute("user_user_role", user.getUserRole());
            response.sendRedirect("/");
        }
    }

    public List<User> getUsers() {
        return new UserDAOImpl().getAllUsers();
    }


}

package by.vit.ban.servlet;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class AuthenticationServlet extends HttpServlet {
    private static final Logger logger = (Logger) LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        final String pass = "root";
        try {
            if (pass.equals(req.getParameter("password"))) {
                req.getSession();
                resp.sendRedirect(req.getContextPath() + "/clients");
            } else {
                resp.getWriter().write("Wrong password");
                resp.setStatus(401);
            }
        } catch (IOException exception) {
            logger.error("Input error", exception);
            resp.setStatus(500);
        }
    }
}


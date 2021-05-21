package by.vit.ban.servlet;

import by.vit.ban.dao.PersonDao;
import by.vit.ban.dao.PersonDaoImpl;
import by.vit.ban.model.Person;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/clients")
public class ClientsServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ClientsServlet.class.getName());
    private final PersonDao personDao = new PersonDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        PrintWriter printWriter;
        Gson gson = new Gson();
        try {
            printWriter = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            printWriter.print(gson.toJson(personDao.getAll()));
            resp.setStatus(200);
        } catch (IOException exception) {
            resp.setStatus(400);
            logger.log(Level.WARNING, "Containing information not found", exception);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Person person = new Person();
        try {
            person.setFirstName(req.getParameter("firstName"));
            person.setSecondName(req.getParameter("secondName"));
            person.setPassword(req.getParameter("password"));
            person.setPhoneNumber(req.getParameter("phoneNumber"));
            personDao.insert(person);
            resp.setStatus(200);
            resp.sendRedirect(req.getContextPath() + "/clients");
        } catch (IOException exception) {
            resp.setStatus(400);
            logger.log(Level.WARNING, "Containing information not found", exception);
        }
    }

}

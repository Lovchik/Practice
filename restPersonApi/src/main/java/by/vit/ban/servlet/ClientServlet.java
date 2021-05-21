package by.vit.ban.servlet;

import by.vit.ban.dao.PersonDao;
import by.vit.ban.dao.PersonDaoImpl;
import by.vit.ban.model.Person;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/clients/*")
public class ClientServlet extends HttpServlet {
    private static final Logger logger = (Logger) LogManager.getLogger();
    private final PersonDao personDao = new PersonDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Gson gson = new Gson();
        try {
            PrintWriter printWriter = resp.getWriter();
            if (personDao.findById(Integer.parseInt(req.getPathInfo().substring(1))) == null) {
                resp.setStatus(400);
            } else {
                printWriter.print(gson.toJson(personDao.findById(Integer.parseInt(req.getPathInfo().substring(1)))));
                resp.setStatus(200);
            }
        } catch (NumberFormatException exception) {
            logger.error("Trying to parse incorrect symbol", exception);
            resp.setStatus(400);
        } catch (IOException exception) {
            resp.setStatus(400);
            logger.error("Input error", exception);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (personDao.findById(Integer.parseInt(req.getPathInfo().substring(1))) != null) {
                personDao.delete(Integer.parseInt(req.getPathInfo().substring(1)));
                resp.setStatus(200);
            } else {
                resp.setStatus(400);
            }
        } catch (NumberFormatException exception) {
            logger.error("Trying to parse a incorrect symbol", exception);
            resp.setStatus(400);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        Gson gson = new Gson();
        try {
            Person person = gson.fromJson(req.getReader().readLine(), Person.class);
            if (personDao.findById(Integer.parseInt(req.getPathInfo().substring(1))) == null ||
                    person.getId() != Integer.parseInt(req.getPathInfo().substring(1))) {
                resp.setStatus(400);
            } else {
                personDao.update(person);
                resp.setStatus(200);
                resp.sendRedirect(req.getContextPath() + "/clients");
            }
        } catch (IOException exception) {
            resp.setStatus(400);
            logger.error("Containing information not found", exception);
        } catch (JsonSyntaxException exception) {
            resp.setStatus(400);
            logger.error("Can not parse input json file into object", exception);
        } catch (NumberFormatException exception){
            logger.error("Trying parse incorrect symbol", exception);
            resp.setStatus(400);
        }
    }
}

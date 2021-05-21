package by.vit.ban.servlet;

import by.vit.ban.model.Person;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientsServletTest {

    @InjectMocks
    private final ClientsServlet clientsServlet = new ClientsServlet();
    @Mock
    private PrintWriter writer;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Test
    public void doGetTest() throws IOException {
        List<Person> personList = new ArrayList<>();
        Gson gson = new Gson();
        personList.add(new Person(2, "Sasha", "Casha", "gevvr", "+375297537543"));
        personList.add(new Person(3, "Vlaf", "Cucu", "1212", "88005553535"));
        when(response.getWriter()).thenReturn(writer);
        clientsServlet.doGet(request, response);
        verify(writer).print(gson.toJson(personList));
    }

    @Test
    public void doPostTest() throws IOException {
        when(request.getParameter("firstName")).thenReturn("Vlaf");
        when(request.getParameter("secondName")).thenReturn("Cucu");
        when(request.getParameter("password")).thenReturn("1212");
        when(request.getParameter("phoneNumber")).thenReturn("88005553535");
        when(request.getContextPath()).thenReturn("http://localhost:8000");
        clientsServlet.doPost(request, response);
        verify(response).sendRedirect(request.getContextPath() + "/clients");
    }
}

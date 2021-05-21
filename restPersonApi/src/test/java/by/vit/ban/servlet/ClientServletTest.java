package by.vit.ban.servlet;

import by.vit.ban.model.Person;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServletTest {

    @InjectMocks
    private final ClientServlet clientServlet = new ClientServlet();
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PrintWriter printWriter;
    @Mock
    private BufferedReader bufferedReader;

    @Test
    public void doDeleteTest() {
        when(request.getPathInfo()).thenReturn("/1");
        clientServlet.doDelete(request, response);
        verify(response).setStatus(200);
    }

    @SneakyThrows
    @Test
    public void doPutTest() {
        when(request.getReader()).thenReturn(bufferedReader);
        when(bufferedReader.readLine()).thenReturn("{ \"id\": 1, \"firstName\": \"Vitaly\", \"secondName\": \"Lovchik\", \"password\": \"general\", \"phoneNumber\": \"+375297068350\" }");
        when(request.getPathInfo()).thenReturn("/1");
        clientServlet.doPut(request, response);
        verify(response).setStatus(200);
    }

    @Test
    public void deleteWithWrongParamTest() {
        when(request.getPathInfo()).thenReturn("/r");
        clientServlet.doDelete(request, response);
        verify(response).setStatus(400);
    }

    @SneakyThrows
    @Test
    public void doGetTest() {
        Gson gson = new Gson();
        Person person = new Person(1, "Vitaly", "Lovchik", "general", "+375297068350");
        when(response.getWriter()).thenReturn(printWriter);
        when(request.getPathInfo()).thenReturn("/1");
        clientServlet.doGet(request, response);
        verify(printWriter).print(gson.toJson(person));
    }

    @Test
    public void getNonExistentPersonTest() {
        when(request.getPathInfo()).thenReturn("/6");
        clientServlet.doGet(request, response);
        verify(response).setStatus(400);
    }
}

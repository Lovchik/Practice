package by.vit.ban.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServletTest {

    @InjectMocks
    private final AuthenticationServlet authenticationServlet = new AuthenticationServlet();
    @Mock
    private PrintWriter writer;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private HttpServletResponse response;

    @Test
    public void doPostAuthWithRightPassTest() {
        when(request.getParameter("password")).thenReturn("root");
        when(request.getSession()).thenReturn(session);
        authenticationServlet.doPost(request, response);
        verify(request).getSession();
    }

    @Test
    public void doPostAuthWithWrongPassTest() throws IOException {
        when(request.getParameter("password")).thenReturn("wrong");
        when(response.getWriter()).thenReturn(writer);
        authenticationServlet.doPost(request, response);
        verify(writer).write("Wrong password");
    }
}

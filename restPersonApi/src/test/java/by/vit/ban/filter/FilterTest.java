package by.vit.ban.filter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FilterTest {

    @InjectMocks
    private final PassFilter passFilter = new PassFilter();
    @Mock
    private HttpSession session;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;

    @Test
    public void doFilterWithoutSessionTest() throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn("/login");
        passFilter.doFilter(request, response, filterChain);
        verify(request).getRequestURI();
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void doFilterWhenSessionTest() throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn("/clients");
        when(request.getSession(false)).thenReturn(session);
        passFilter.doFilter(request, response, filterChain);
        verify(filterChain).doFilter(request, response);
    }
}

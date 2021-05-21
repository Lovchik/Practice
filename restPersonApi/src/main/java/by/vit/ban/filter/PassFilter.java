package by.vit.ban.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class PassFilter implements Filter {

    /**
     * Filter all request except "/login".
     * If session not exists print "First login please".
     * If session exists request passes.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String path = req.getRequestURI();

        if (path.startsWith("/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        HttpSession session = req.getSession(false);
        if (session == null) {
            res.setStatus(401);
            res.getWriter().print("First login please");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}

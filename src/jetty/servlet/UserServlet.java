package jetty.servlet;

import jetty.controller.UserController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/users")
public class UserServlet extends HttpServlet {
    private final UserController controller = new UserController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String json = controller.getAllUsers();
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}

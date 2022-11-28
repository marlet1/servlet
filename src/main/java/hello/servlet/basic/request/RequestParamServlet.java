package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requestPartServlet",urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RequestParamServlet.service");
        System.out.println("[전체 파라미터 조회- start");
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName->
                        System.out.println(paramName+"="+req.getParameter(paramName)));
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();
        String username = req.getParameter("username");
        String age = req.getParameter("age");
        System.out.println("username="+username);
        System.out.println("age="+age);
        System.out.println("[이름이 같은 반복안되게 복수 파라미터 조회]");
        String[] usernames = req.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username="+name);
        }
        resp.getWriter().write("ok");
    }
}

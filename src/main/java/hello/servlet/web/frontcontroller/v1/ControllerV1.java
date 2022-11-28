package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 {
    void process(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException;
    //process서블릿에 똑같은 인터페이스를 만든 것

}

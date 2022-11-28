package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1",urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {
    private Map<String,ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form",new MemberFormControllerV1());
        //"/front-controller/v1/members/new-form"예가 요청되면 MemberFormControllerV1()예가 실행된다.
        controllerMap.put("/front-controller/v1/members/save",new MemberSaveServlet());
        controllerMap.put("/front-controller/v1/members",new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");
        String requestURI = req.getRequestURI();

        ControllerV1 controller = controllerMap.get(requestURI);
        if(controller == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controller.process(req,resp);
    }
}

package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
    //1 - json도 문자 이며, 문자로 바꿀것이다. 예를 들면 바꿀려면 오브젝트 매퍼가 필요하다.
    //2 - {"username":"kim","age":20} 김이랑 20을 바꿀려면 오브젝트매퍼가 필요하다. ObjectMapper();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Content-Type:application/json
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(20);

        //3 - 객체값을 써서 문자값으로 바꿔라 objectMapper.writeValueAsString(helloData)
        String result = objectMapper.writeValueAsString(helloData);
        resp.getWriter().write(result);

    }
}

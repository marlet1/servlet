package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestHeaderServlet",urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printStartLine(req);
        printHeaders(req);
        printHeaderUtils(req);
        printEtc(req);
    }
    private void  printStartLine(HttpServletRequest req){
        System.out.println("---REQUEST-LINE - start ---");
        System.out.println("request.getMethod()="+req.getMethod());//GET
        System.out.println("request.getProtocol()="+req.getProtocol());//HTTP/1.1
        System.out.println("request.getScheme()="+req.getScheme());//http//http://localhost:8080/request-header
        System.out.println("request.getRequestURL()="+req.getRequestURL());//request-header
        System.out.println("request.getRequestURI()="+req.getRequestURI());//username=hi
        System.out.println("request.getQueryString()="+req.getQueryString());
        System.out.println("request.isSecure()="+req.isSecure());//https 사용 유무
        System.out.println("--- REQUEST-LINE - end---");
        System.out.println();
    }
    //Header 모든 정보
    private void printHeaders(HttpServletRequest req) {
        System.out.println("--- Headers - start1 ---");
        /* Enumeration<String> headerNames = req.getHeaderNames();
         *//*겟헤더네임즈라고 하면 http요청메시지에 모든정보 다꺼내서 전체 출력이 가능 하다.*//*
        while (headerNames.hasMoreElements()){
            *//*헤즈네임에 헤즈모어에 값이 있냐 있으면 와일문 돌린다.*//*
            String headerName = headerNames.nextElement();
            System.out.println(headerName+":"+headerName);
            //이 위에 코드는 구문법으로 된 코드로 보면 간결해 보이는데 아래가 더욱 깔끔하고 차이점이 있기 때문 이다.
        }*/
        req.getHeaderNames().asIterator()
                .forEachRemaining(headerName-> System.out.println("hearderName="+headerName));
        /*현재 최신 문법으로 간결하게 나오는것을 볼 수 있다.*/
    /*
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
    String headerName = headerNames.nextElement();
    System.out.println(headerName + ": " + request.getHeader(headerName));
    }
    */
        System.out.println("--- Headers - end ---");
        System.out.println();
    }
    //Header 편리한 조회
    private void printHeaderUtils(HttpServletRequest req) {
        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " +
                req.getServerName()); //Host 헤더
        System.out.println("request.getServerPort() = " +
                req.getServerPort()); //Host 헤더
        System.out.println();
        System.out.println("[Accept-Language 편의 조회]");
        req.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " +
                        locale));
        System.out.println("request.getLocale() = " + req.getLocale());
        System.out.println();
        System.out.println("[cookie 편의 조회]");
        if (req.getCookies() != null) {
            for (Cookie cookie : req.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();
        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " +
                req.getContentType());
        System.out.println("request.getContentLength() = " +
                req.getContentLength());
        System.out.println("request.getCharacterEncoding() = " +
                req.getCharacterEncoding());
        System.out.println("--- Header 편의 조회 end ---");
        System.out.println();
    }
    private void printEtc(HttpServletRequest req) {
        System.out.println("--- 기타 조회 start ---");
        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = " + req.getRemoteHost()); //
        System.out.println("request.getRemoteAddr() = " + req.getRemoteAddr()); //
        System.out.println("request.getRemotePort() = " + req.getRemotePort()); //
        System.out.println();
        System.out.println("[Local 정보]");
        System.out.println("request.getLocalName() = " + req.getLocalName()); //
        System.out.println("request.getLocalAddr() = " + req.getLocalAddr()); //
        System.out.println("request.getLocalPort() = " + req.getLocalPort()); //
        System.out.println("--- 기타 조회 end ---");
        System.out.println();
    }
}

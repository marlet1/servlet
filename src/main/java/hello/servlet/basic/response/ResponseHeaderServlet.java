package hello.servlet.basic.response;

import jdk.jshell.Snippet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //[status-line]
        resp.setStatus(HttpServletResponse.SC_OK);

        //[response-headers]
        resp.setHeader("Content-Type","text/plain;charset=utf-8"); //utf-8안넣으면 결과 ok에서 한글로해도 한글이 깨진다.
        /*"Cache-Control","Pragma"과거버전까지 두줄은 캐쉬무료화 하면서 과거버전으로 사용 하는중 이다.*/
        resp.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma","no-cache");
        resp.setHeader("my-header","hello");
        //[Header 편의 메서드]
        //content(response);
        cookie(resp);
        redirect(resp);
        PrintWriter writer = resp.getWriter();
        writer.println("헬롱");
    }
    private void content(HttpServletResponse resp){
        //Content- Type:text/plain;charset=utf-8
        //Content-Length:2
        //resp.setHeader("Content-typ","");
        resp.setContentType("text/plan");
        resp.setContentType("utf-8");
        //resp.setCharacterEncoding(2);//생략시 자동 생성
    }
    private void cookie(HttpServletResponse resp){
        //Set-Cookie:myCookie=good; Max-Age=600;
        //resp.setHeader("Set-Cookie","myCookie=good; Max-Age=600");
        /*셋쿠키 마이쿠키 굳일아 */
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        resp.addCookie(cookie);
        /*cookie은 마이쿠키랑 굳이 쿠기객체들어가있고 cookie.setMaxAge(600);은 쿠키안에 설정값을 추가 하여 600초로 들어가서
        응답할려는 추가된 객체쿠키가 불러온다.
        */
    }
    private  void redirect(HttpServletResponse resp)throws IOException{
        //Status Code 302
        //Location:/basic/hello-form.html
        //resp.setStatus(HttpServletResponse.SC_FOUND); //302
        //resp.setHeader("Location","/basic/hello-form.html");
        resp.sendRedirect("/basic/hello-form.html");
    }
}

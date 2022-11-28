<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("MemberSaveServlet.service");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username,age);
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!--<%@ page contentType="text/html;charset=UTF-8" language="java" %> jsp파일 생성후 꼭 넣어야하는 구문-->
<!--<%%> 사용하면 자바코드를 넣을수 있다.-->
<!--request,response는 서비스로 자동으로 조직이 바뀌게 되며 jsp사용하면 자동으로 로직이 지원 된다고 보면 된다.-->

성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
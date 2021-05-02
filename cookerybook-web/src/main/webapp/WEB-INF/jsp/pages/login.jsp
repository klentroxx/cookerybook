<%@ page import="hu.cookerybook.web.controller.LoginController" %>
<%@ page import="java.util.List" %>
<%@ page import="hu.cookerybook.core.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
if (session.getAttribute("user_username") != null) {
    response.sendRedirect("/");
}
%>
<html>
<head>
    <title>Bejelentkezés</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<main>
<section class="login-section">
    <div class="content-wrap">
        <form action="${pageContext.request.contextPath}/bejelentkezes" method="post">
            <label for="email">E-mail</label>
            <input type="text" id="email" name="email">
            <label for="password">Jelszó</label>
            <input type="password" id="password" name="password">
            <input type="submit" name="submit" value="Bejelentkezés">
        </form>
        <%
            LoginController lc = new LoginController();
            List<User> users = lc.getUsers();
            for (User user : users) {
                out.print(user.getEmail());
            }
        %>
    </div>
</section>
</main>
<jsp:include page="../includes/footer.jsp" />
</body>
</html>

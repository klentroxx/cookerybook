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
        <div class="login-form-container">
            <form action="${pageContext.request.contextPath}/bejelentkezes" method="post">
                <div class="input-fields">
                    <label for="email">E-mail vagy felhasználónév</label>
                    <input type="text" id="email" name="email" placeholder="pl. email@example.com">
                </div>
                <div class="input-fields">
                    <label for="password">Jelszó</label>
                    <input type="password" id="password" name="password" placeholder="********">
                </div>
                <div class="input-fields">
                    <input type="submit" name="submit" value="Bejelentkezés">
                </div>
            </form>
        </div>
    </div>
</section>
</main>
<jsp:include page="../includes/footer.jsp" />
</body>
</html>

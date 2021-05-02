<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Főoldal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="includes/header.jsp" />
<main>
    <div class="content-wrap">
        <h1>Szia ${user_first_name}!</h1>
    </div>
</main>
<jsp:include page="includes/footer.jsp" />
</body>
</html>
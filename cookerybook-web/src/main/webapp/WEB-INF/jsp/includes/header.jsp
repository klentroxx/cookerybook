<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <nav>
        <ul>
            <li><a href="<c:url value="/"/>">Főoldal</a></li>
            <% if (session.getAttribute("user_uid") == null) { %>
            <li><a href="<c:url value="/bejelentkezes"/>">Bejelentkezés</a></li>
            <% } %>

            <% if (session.getAttribute("user_uid") != null) { %>
            <li><a href="<c:url value="/kijelentkezes"/>">Kijelentkezés</a></li>
            <% if (session.getAttribute("user_user_role").equals(2) || session.getAttribute("user_user_role").equals(1)) {%>
            <li><a href="<c:url value="/recept-feltoltes"/>">Új recepet</a></li>
            <% } %>
            <% } %>
        </ul>
    </nav>
    <% if (session.getAttribute("user_uid") != null) { %>
    <div class="welcome-user">
        Üdv,&nbsp;<span class="username"><%
        out.print(!session.getAttribute("user_first_name").equals("") ? session.getAttribute("user_first_name") : session.getAttribute("user_username")); %></span>
    </div>
    <% } %>
</header>
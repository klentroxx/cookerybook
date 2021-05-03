<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Új recept feltöltése</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
</head>
<body>
<jsp:include page="../includes/header.jsp"/>
<main>
    <section id="new_recipe">
        <div class="content-wrap">
            <% if (request.getSession().getAttribute("recipe_response") != null) {%>
            <div class="response_message <% if(request.getSession().getAttribute("recipe_response").equals("A recept sikeresen feltöltve!")) {out.print("successful");}else {out.print("error");}%>">${recipe_response}</div>
            <%
            }
                request.getSession().setAttribute("recipe_response", null);
            %>
            <div class="form-container">
                <form action="${pageContext.request.contextPath}/recept-feltoltes/feltolt" method="post" enctype="multipart/form-data">
                    <div class="input-box">
                        <label for="name"><span class="required">*</span>Étel neve:</label>
                        <input type="text" id="name" name="name" placeholder="pl. paprikás krumpli" required>
                    </div>
                    <div class="input-box">
                        <label for="other_names">További nevek (vesszővel elválasztva):</label>
                        <input type="text" id="other_names" name="other_names"
                               placeholder="pl. krumplikás papri, papi krumpli">
                    </div>
                    <div class="input-box">
                        <label for="photo">Fénykép:</label>
                        <input type="file" id="photo" name="photo" accept=".jpg, .jpeg, .png">
                    </div>
                    <div class="input-box">
                        <label for="directions"><span class="required">*</span>Elkészítési útmutató:</label>
                        <textarea name="directions" id="directions" cols="10" rows="30" required></textarea>
                    </div>
                    <div class="input-box">
                        <label for="difficulty"><span class="required">*</span>Elkészítés nehézsége:</label>
                        <input type="range" min="1" max="10" step="1" id="difficulty" name="difficulty">
                        <div class="slider-values">
                            <span>Kezdőknek is megy</span>
                            <span>Csak profiknak</span>
                        </div>
                    </div>
                    <div class="input-box">
                        <label for="time_to_cook"><span class="required">*</span>Elkésztési idő (percben megadva):</label>
                        <input type="number" id="time_to_cook" name="time_to_cook" step="1" min="1"
                               placeholder="pl. 45" required>
                    </div>
                    <div class="table-container">
                        <table id="ingredients_list">
                            <caption><span class="required">*</span>Hozzávalók listája:</caption>
                            <thead>
                            <tr>
                                <th class="text-center">
                                    Hozzávaló neve
                                </th>
                                <th class="text-center">
                                    Mennyiség
                                </th>
                                <th class="text-center"
                                    style="border-top: 1px solid #ffffff; border-right: 1px solid #ffffff;">
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr id='ingredient0' data-id="0">
                                <td data-name="ingredient_name">
                                    <select name="ingredient_name0" required>
                                        <c:forEach items="${ingredients}" var="ingredient">
                                            <option value="${ingredient.getId()}">${ingredient}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td data-name="quantity">
                                    <input type="number" step="0.001" min="0" name="quantity0" required>
                                </td>
                                <td data-name="del">
                                    <button type="button" name="del0" class='delete-button row-remove'>
                                        <span aria-hidden="true">×</span></button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <a id="add_row" class="add_row_button">Új hozzáadása</a>
                    </div>
                    <div class="input-box">
                        <label for="servings"><span class="required">*</span>Adagok száma:</label>
                        <input type="number" id="servings" name="servings" step="1" min="1" placeholder="pl. 4" required>
                    </div>
                    <div class="input-box">
                        <label for="category"><span class="required">*</span>Kategória:</label>
                        <input type="text" id="category" name="category" step="1" min="1" placeholder="pl. főétel" required>
                    </div>
                    <div class="info">A <span class="required">*</span>-gal jelölt mezők kitöltése kötelező</div>
                    <input type="submit" value="Recept feltöltése">
                </form>
            </div>
        </div>
    </section>
    <script src="${pageContext.request.contextPath}/js/form-functions.js"></script>
</main>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>

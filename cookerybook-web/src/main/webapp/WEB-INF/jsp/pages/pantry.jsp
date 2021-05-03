<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spájz</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<main>
    <div class="content-wrap">
        <% if (request.getSession().getAttribute("recipe_response") != null) {%>
        <div class="response_message <%
        if(request.getSession().getAttribute("recipe_response").equals("Az alapanyag sikeresen feltöltve!") ||
           request.getSession().getAttribute("recipe_response").equals("Az alapanyag sikeresen módosítva!") ||
           request.getSession().getAttribute("recipe_response").equals("Az alapanyag sikeresen törölve!")) {
            out.print("successful");
        } else {
            out.print("error");
        }
        %>">${recipe_response}</div>
        <%
            }
            request.getSession().setAttribute("recipe_response", null);
        %>
        <div class="new-item">
            <form action="${pageContext.request.contextPath}/spajz/feltolt" enctype="multipart/form-data" method="post">
                <div class="input-box">
                    <label for="ingredient">Alapanyag</label>
                    <select name="ingredient" id="ingredient" required>
                        <c:forEach items="${select_ingredients}" var="ingredient">
                            <option value="${ingredient.getId()}">${ingredient}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="input-box">
                    <label for="quantity">Vásárolt alapanyag mennyisége</label>
                    <input type="number" name="quantity" id="quantity" step="0.001" min="0.001" required>
                </div>
                <div class="input-box">
                    <label for="minimum_amount">Minimálisan szükséges mennyiség</label>
                    <input type="number" name="minimum_amount" id="minimum_amount" step="0.001" min="0">
                </div>
                <div class="input-box">
                    <input type="submit" name="submit" id="submit" value="Hozzáadás a spájzhoz">
                </div>
            </form>
        </div>
    </div>
    <c:if test="${!pantry_of_user.isEmpty()}">
    <div class="content-wrap">
        <div class="user-pantry">
            <div class="table-wrap">
                <table>
                    <caption>Saját spájzom</caption>
                    <thead>
                    <tr>
                        <th id="ingredient-name">Alapanyag neve</th>
                        <th id="available-quantity">Meglévő mennyiség</th>
                        <th id="minimum-amount">Minimális mennyiség</th>
                        <th id="modifications" colspan="2">Műveletek</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pantry_of_user}" var="pantryItem">
                        <tr>
                            <td <c:if test="${pantryItem.getIngredientQuantity()<pantryItem.getMinimumAmount()}">${"class='warning'"}</c:if>>${ingredient_dao.getIngredient(pantryItem.getIngredientId()).getName()}</td>
                            <td <c:if test="${pantryItem.getIngredientQuantity()<pantryItem.getMinimumAmount()}">${"class='warning'"}</c:if>>${pantryItem.getIngredientQuantity()} ${unit_dao.getUnit(ingredient_dao.getIngredient(pantryItem.getIngredientId()).getUnitId()).getShortName()}</td>
                            <td <c:if test="${pantryItem.getIngredientQuantity()<pantryItem.getMinimumAmount()}">${"class='warning'"}</c:if>>${pantryItem.getMinimumAmount()} ${unit_dao.getUnit(ingredient_dao.getIngredient(pantryItem.getIngredientId()).getUnitId()).getShortName()}</td>
                            <td class="modify-form">
                                <form action="/spajz/modosit" method="post" enctype="multipart/form-data">
                                    <input type="number" name="pantry_item_quantity" step="0.001" min="0" placeholder="Új mennyiség" aria-label="Meglévő mennyiség új értéke" title="Meglévő mennyiség új értéke" required>
                                    <input type="hidden" name="pantry_item_mod" id="pantry_item_mod" value="${pantryItem.getId()}">
                                    <input type="submit" value="Módosít">
                                </form>
                            </td>
                            <td class="delete-form">
                                <form action="/spajz/torol" method="post" enctype="multipart/form-data">
                                    <input type="hidden" name="pantry_item_del" id="pantry_item_del" value="${pantryItem.getId()}">
                                    <input type="submit" value="×" class="delete-button" title="Listaelem törlése">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    </c:if>
</main>
<jsp:include page="../includes/footer.jsp" />
</body>
</html>

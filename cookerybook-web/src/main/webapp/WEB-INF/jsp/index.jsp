<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Főoldal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="includes/header.jsp"/>
<main>
    <div class="content-wrap">
        <h1>Szakácskönyv</h1>
    </div>
    <div class="recipes">
        <div class="content-wrap">
            <c:forEach items="${recipes}" var="recipe">
                <div class="recipe-box">
                    <div class="image-box">
                        <img src="data:image/jpg;base64, ${recipe.getPhoto()}" alt="${recipe.getName()}">
                    </div>
                    <div class="recipe-data">
                        <h2>${recipe.getName()}</h2>
                        <p><span class="data-type">Elkészítés:</span> ${recipe.getDirections()}</p>
                        <p><span class="data-type">Nehézség:</span> ${recipe.getDifficulty()}</p>
                        <p><span class="data-type">Elkészítési idő:</span> ${recipe.getTimeToCook()} perc</p>
                        <p><span class="data-type">Kategória:</span> ${recipe.getCategory()}</p>
                        <p><span class="data-type">Hozzávalók:</span></p>
                        <ul class="ingredients">
                            <c:forEach items="${required_ingredients}" var="reqIng">
                                <c:if test="${reqIng.getRecipeId()==recipe.getId()}">
                                    <li>
                                        <div class="ingredient-name">${ingredients_dao.getIngredient(reqIng.getIngredientId()).getName()}</div>
                                        <div class="required-amount">${reqIng.getIngredientAmount()} ${unit_dao.getUnit(ingredients_dao.getIngredient(reqIng.getIngredientId()).getUnitId()).getShortName()}</div>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>
<jsp:include page="includes/footer.jsp"/>
</body>
</html>
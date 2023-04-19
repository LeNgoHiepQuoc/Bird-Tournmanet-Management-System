<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/manageAccount-Bird.css">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <title>Ranking</title>
    </head>
    <body>
        <header>
            <%@include file="userHeader.jsp" %>
        </header>
        <section>
            <div class="top-bird-heading d-flex justify-content-center align-item-center w-100">
                <h1>Top Ranking Bird </h1>
            </div>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Top</th>
                        <th scope="col">Bird Name</th>
                        <th scope="col">Owner Name</th>
                        <th scope="col">Total Score</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.brank}" var="r" varStatus="loop">
                        <tr>
                            <th scope="row">${r.achivement.top}</th>
                            <td>${r.birdName}</td>
                            <td>${r.account.name}</td>
                            <td>${r.achivement.totalScore}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
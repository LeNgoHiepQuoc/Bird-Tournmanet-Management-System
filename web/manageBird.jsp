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
        <title>Manage Bird</title>
    </head>
    <body>
        <header>
            <%@include file="adminHeader.jsp" %>
        </header>
        <section>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Bird Name</th>
                        <th scope="col">Owner ID</th>
                        <th scope="col">Status</th>
                        <th scope="col">Block</th>
                        <th scope="col">Unblock</th>
                        <th scope="col">Detail</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${birds}" var="b">
                        <tr>
                            <th scope="row">${b.birdID}</th>
                            <td>${b.birdName}</td>
                            <td>${b.accountID}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${b.birdStatus == 1}">Active</c:when>
                                    <c:otherwise>Inactive</c:otherwise>
                                </c:choose>
                            <td>
                                <c:choose>
                                    <c:when test="${b.birdStatus == 0}">Block</c:when>
                                    <c:otherwise>
                                        <a class="custom-button_2" href="ManageBirdController?action=Block&birdID=${b.birdID}">Block</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${b.birdStatus == 1}">Unblock</c:when>
                                    <c:otherwise>
                                        <a class="custom-button_2" href="ManageBirdController?action=Unblock&birdID=${b.birdID}">Unblock</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a class="custom-button_2" href="ManageBirdController?action=Detail&birdID=${b.birdID}">Detail</a>
                            </td>
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
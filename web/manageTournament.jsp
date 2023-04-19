<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/manageTournamnet-Blog.css">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <title>Manage Tournament</title>
    </head>
    <body>
        <header>
            <%@include file="adminHeader.jsp" %>
        </header>
        <section>
            <div>
                <a class="custom-button_3" href="MainController?action=LOAD_BIRD_CATEGORY">Add Tournament</a>
            </div>
            <div>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Tournament Name</th>
                            <th scope="col">Min Player</th>
                            <th scope="col">Max Player</th>
                            <th scope="col">Status</th>
                            <th scope="col">Participant</th>
                            <th scope="col">Remove</th>
                            <th scope="col">Detail</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${tours}" var="t">
                            <tr>
                                <th scope="row">${t.tournamentID}</th>
                                <td>${t.tournamentName}</td>
                                <td>${t.minParticipant}</td>
                                <td>${t.maxParticipant}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${t.tournamentStatus == 0}">Up Coming</c:when>
                                        <c:when test="${t.tournamentStatus == 1}">Open Form</c:when>
                                        <c:when test="${t.tournamentStatus == 2}">Closed Form</c:when>
                                        <c:when test="${t.tournamentStatus == 3}">On Going</c:when>
                                        <c:when test="${t.tournamentStatus == 4}">Finished</c:when>
                                        <c:otherwise>Delay</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a class="custom-button_2" href="MainController?action=LOAD_PARTICIPANT&tournamentID=${t.tournamentID}">View</a>
                                </td>
                                <td>
                                    <a class="custom-button_2" href="MainController?action=REMOVE_TOURNAMENT&tournamentID=${t.tournamentID}">Remove</a>
                                </td>
                                <td>
                                    <a class="custom-button_2" href="ManageTournamentDetailController?tournamentID=${t.tournamentID}">Detail</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
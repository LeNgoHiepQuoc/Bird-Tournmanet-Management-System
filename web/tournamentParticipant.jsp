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
        <title>Manage Participant</title>
    </head>
    <body>
        <header>
            <%@include file="adminHeader.jsp" %>
        </header>
        <section>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Participant ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">Bird Name</th>
                        <th scope="col">Status</th>
                        <th scope="col">Approve</th>
                        <th scope="col">Deny</th>
                        <th scope="col">Detail</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${participant}" var="p">
                        <tr>
                            <th scope="row">${p.registrationFormID}</th>
                            <td>${p.acc.name}</td>
                            <td>${p.bird.birdName}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${p.formStatus == 1}">Pending</c:when>
                                    <c:when test="${p.formStatus == 2}">Approved</c:when>
                                    <c:otherwise>Denied</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${p.formStatus == 2}">Approve</c:when>
                                    <c:otherwise>
                                        <a class="custom-button_2" href="MainController?action=APPROVE_PARTICIPANT&participantID=${p.registrationFormID}&tournamentID=${p.tournamentID}">Approve</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${p.formStatus == 3}">Deny</c:when>
                                    <c:otherwise>
                                        <a class="custom-button_2" href="MainController?action=DENY_PARTICIPANT&participantID=${p.registrationFormID}&tournamentID=${p.tournamentID}">Deny</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a class="custom-button_2" href="MainController?action=LOAD_FORM_DETAIL&formID=${p.registrationFormID}&tournamentID=${p.tournamentID}">Detail</a>
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
    <script>
        document.addEventListener("DOMContentLoaded", function (event) {
            var scrollpos = sessionStorage.getItem('scrollpos');
            if (scrollpos) {
                window.scrollTo(0, scrollpos);
                sessionStorage.removeItem('scrollpos');
            }
        });
        window.addEventListener("beforeunload", function (e) {
            sessionStorage.setItem('scrollpos', window.scrollY);
        });
    </script>
</html>

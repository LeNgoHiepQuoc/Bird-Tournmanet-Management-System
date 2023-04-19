<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
        <link href='https://fonts.googleapis.com/css?family=Baloo' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href='https://fonts.googleapis.com/css?family=Oswald' rel='stylesheet'>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
        <link rel="stylesheet" href="./CSS/birdRepository.css">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>Manage Bird Detail</title>
    </head>
    <body>
        <header>
            <c:choose>
                <c:when test="${sessionScope.acc.role == 0}">
                    <%@include file="userHeader.jsp" %>
                </c:when>
                <c:otherwise>
                    <%@include file="adminHeader.jsp"%>
                </c:otherwise>
            </c:choose>
        </header>
        <section style="margin-top: 85px;">
            <div class="bird-repository container">
                <div class="bird-repositoryp-main-card">
                    <div class="bird-repositoryp-left-card">
                        <div class="img-card-container">
                            <img class="card-img-top" style="width: 40rem; height: 30rem;" src="${bird.birdPhoto}" alt="Card image cap">  
                            <p>
                                <c:choose>
                                    <c:when test="${bird.birdStatus == 0}">Status: Dead</c:when>
                                    <c:when test="${bird.birdStatus == 1}">Status: Alive</c:when>
                                    <c:when test="${bird.birdStatus == 2}">Status: Unavailable</c:when>
                                </c:choose>
                            </p>
                        </div>
                    </div>
                    <div class="bird-repositoryp-right-card">
                        <div class="card-body main-body-bird">
                            <div class="left-main-site">
                                <li>Account ID</li>
                                <li>Bird ID</li>
                                <li>Bird Name</li>
                                <li>Height</li>
                                <li>Weight</li>
                                <li>Color</li>
                                <li>Dentification</li>
                            </div>
                            <div class="right-main-site">
                                <li>${bird.getAccountID()}</li>
                                <li>${bird.getBirdID()}</li>
                                <li>${bird.getBirdName()}</li>
                                <li>${bird.getHeight()}</li>
                                <li>${bird.getWeight()}</li>
                                <li>${bird.getColor()}</li>
                                <li>${bird.getDentification()}</li>
                            </div>
                        </div>
                        <form action="ManageBirdDetailController" method="GET">
                            <div class="card-body main-body-bird">
                                <div class="left-achivement-site">
                                    <h1>Achievement</h1>
                                    <c:forEach items="${fn:split(bird.achivement.medals,';')}" var="medals">
                                        <li>${medals} times</li>
                                        </c:forEach>
                                    <li>Medals: <input type="text" value="${bird.achivement.medals}" name="medals"></li>
                                    <li>Total score: <input type="number" class="top" value="${bird.achivement.totalScore}" name="totalScore"> and at Rank: <input  class="top" type="number" value="${bird.achivement.top}" name="top"></li> 
                                </div>
                            </div>
                            <div class="btn-update-bird">
                                <input type="hidden" value="${bird.birdID}" name="birdID" />
                                <input class="btn btn-success" type="submit" value="Update Achievement" name="action" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>

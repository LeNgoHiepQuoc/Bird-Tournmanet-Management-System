<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
        <link href='https://fonts.googleapis.com/css?family=Baloo' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href='https://fonts.googleapis.com/css?family=Oswald' rel='stylesheet'>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
        <link rel="stylesheet" href="CSS/tournament.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </head>
    <body>
        <header>
            <c:choose>
                <c:when test="${sessionScope.acc.role == 0}">
                    <%@include file="userHeader.jsp" %>
                </c:when>
                <c:otherwise>
                    <%@include file="guestHeader.jsp"%>
                </c:otherwise>
            </c:choose>
        </header>>
        <!-- Pircture heading -->
        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img class="d-block w-100" style="height: 800px;" src="./image/intro2.jpg" alt="First slide">
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" style="height: 800px;" src="./image/intro3.jpg" alt="Second slide">
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100"  style="height: 800px;" src="./image/intro4.jpg" alt="Third slide">
                </div>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
        <!-- search tnm by name site -->
        <div class="search-tnm-site">
            <div class="search-tnm-main-site">
                <form action="MainController" class="search-tnm-form" method="post">
                    <input type="text" placeholder="Search tournament" class="form-control" name="TournamentName">
                    <button class="btn btn-search" type="search" name="action" value="SEARCH_TOURNAMENT"><i class='fas fa-search' style='font-size:40px'></i></button>
                </form>
            </div>     
        </div>
        <!-- tnm categories heading  -->
        <div class="container tnm-categories-heading">
            <a href="MainController?action=TOURNAMENT"><p>All Tournaments     <i class='fas fa-award' style='font-size:24px;color: #dbbd0e;;'></i></p></a>
            <a href="MainController?action=ON_GOING_TOURNAMENT"><p>On Going Tournaments</p></a>
            <a href="MainController?action=OLD_TOURNAMENT"><p>Old Tournaments</p></a>
            <a href="MainController?action=DELAY_TOURNAMENT"><p>Delay Tournament     <i class='fas fa-hourglass-end' style='font-size:24px;color: green;'></i></p></a>
        </div>
        <!-- tnm-list -->
        <div class="tnm-site-all container">
            <div class="tnm-list container">
                <!-- card 1 -->
                <c:forEach var="list" items="${sessionScope.GET_LIST}">
                    <div class="tnm-card-site-ptr">     
                        <div class="card" style="width: 45rem;">
                            <img class="card-img-top" src="${list.image}" alt="Card image cap">
                            <div class="card-body" style="height:20rem;">
                                <!-- status -->
                                <div class="status-heading d-flex justify-content-center" style="font-weight: bold;">
                                    <h3>${list.tournamentName}</h3>
                                </div>
                                <div class="status-heading d-flex justify-content-center" style="font-weight: bold;">
                                    <p>
                                        <c:choose>
                                            <c:when test="${list.tournamentStatus == 0}">Up Coming</c:when>
                                            <c:when test="${list.tournamentStatus == 1}">Open Form</c:when>
                                            <c:when test="${list.tournamentStatus == 2}">Close Form</c:when>
                                            <c:when test="${list.tournamentStatus == 3}">On Going</c:when>
                                            <c:when test="${list.tournamentStatus == 4}">Finished</c:when>
                                            <c:otherwise>Delay</c:otherwise>
                                        </c:choose>
                                    </p>
                                </div>
                                <!-- Other information -->
                                <div class="other-inf-tnm">
                                    <div class="each-other-inf-tnm">
                                        <h5>Date and Time</h5>
                                        <p>${list.dateTime}</p>
                                    </div>
                                    <div class="each-other-inf-tnm">
                                        <h5>Location</h5>
                                        <p>${list.location}</p>
                                    </div>
                                    <div class="each-other-inf-tnm">
                                        <h5>Fee</h5>
                                        <p>${list.fee} VND</p>
                                    </div>
                                </div>
                            </div>
                            <div class="btn-card-tnm">
                                <a href="MainController?action=TOURNAMENT_DETAIL&ID=${list.tournamentID}">More Detail</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <!-- footer site -->
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>

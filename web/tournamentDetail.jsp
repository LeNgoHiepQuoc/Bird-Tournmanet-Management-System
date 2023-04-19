<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
              integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
        <link rel="stylesheet" href="./CSS/tourmamentDetail.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>Tournament Detail</title>
    </head>
    <body>  
        <header>
            <%@include file="userHeader.jsp" %>
        </header>
        <!-- tournament Detail -->
        <div class="tnmde-container container">
            <div class="tnm-basic-info">
                <div class="tnm-picture">
                    <div class="card" style="width: 90rem;">
                        <img class="card-img-top" style="width: 90rem; height: 70rem;"
                             src="${requestScope.utour.image}"
                             alt="Card image cap">
                    </div>
                </div>
                <!-- section 2  -->
                <div class="tnm-heading mt-5">
                    <h1>${requestScope.utour.tournamentName}<i class="fa-solid fa-crown"></i></h1>
                    <p>
                        <c:choose>
                            <c:when test="${requestScope.utour.tournamentStatus == 0}">Status: Up Coming</c:when>
                            <c:when test="${requestScope.utour.tournamentStatus == 1}">Status: Open Form</c:when>
                            <c:when test="${requestScope.utour.tournamentStatus == 2}">Status: Close Form</c:when>
                            <c:when test="${requestScope.utour.tournamentStatus == 3}">Status: On Going</c:when>
                            <c:when test="${requestScope.utour.tournamentStatus == 4}">Status: Finished</c:when>
                            <c:when test="${requestScope.utour.tournamentStatus == 5}">Status: Delay</c:when>
                            <c:otherwise>Status: Cancel</c:otherwise>
                        </c:choose>
                    </p>
                </div>
                <!-- Section 3 -->
                <div class="basic-tnm-form">
                    <div class="basic-tnm-form-categories">
                        <h1><i class="fa-solid fa-clock"></i>  Date and Time</h1>
                        <p>${requestScope.utour.dateTime}</p>
                    </div>
                    <div class="basic-tnm-form-categories"
                         style="border-left: 4px solid #A87B24; border-right: 4px solid #A87B24;">
                        <h1><i class="fa-solid fa-location-dot"></i>  Location</h1>
                        <p>${requestScope.utour.location}</p>
                    </div>
                    <div class="basic-tnm-form-categories">
                        <h1><i class="fa-sharp fa-solid fa-money-bill"></i> Fee</h1>
                        <p>${requestScope.utour.fee} VND</p>
                    </div>
                </div>
                <!-- div line -->
                <div class="line-section">
                    <p></p>
                </div>
                <c:choose>
                    <c:when test="${requestScope.utour.tournamentStatus == 3}">
                        <div class="both-prize-player-site">
                            <div class="prize-site" style="border-right: 4px solid #A87B24;">
                                <h1><i class="fa-sharp fa-solid fa-trophy"></i> Prize:  </h1>
                                <c:forEach items="${fn:split(requestScope.utour.prize,';')}" var="prizes">
                                    <p>${prizes}</p>
                                </c:forEach>
                            </div>
                            <div class="basic-tnm-form-categories">
                                <h1><i class="fa-sharp fa-solid fa-person"></i> Number of Player:  </h1>
                                <p>${requestScope.numberPlayer}</p>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="both-prize-player-site">
                            <div class="prize-site" style="border-right: 4px solid #A87B24;">
                                <h1><i class="fa-sharp fa-solid fa-trophy"></i> Prize:  </h1>
                                <c:forEach items="${fn:split(requestScope.utour.prize,';')}" var="prizes">
                                    <p>${prizes}</p>
                                </c:forEach>
                            </div>
                            <div class="basic-tnm-form-categories">
                                <h1><i class="fa-sharp fa-solid fa-person"></i> Min Player:  </h1>
                                <p>${requestScope.utour.minParticipant}</p>
                            </div>
                            <div class="basic-tnm-form-categories">
                                <h1><i class="fa-sharp fa-solid fa-person"></i> Max Player:  </h1>
                                <p>${requestScope.utour.maxParticipant}</p>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="sponsor-site" style="margin-top: 10px">
                    <h1>Description: </h1>
                    <textarea rows="10" cols="100" class="custom-font-3" name="description">${requestScope.utour.description}</textarea>
                </div>
                <div class="line-section">
                    <p></p>
                </div>
                <!-- Section 5 -->
                <div class="sponsor-site">
                    <h1>Main Sponsor <i class="fa-solid fa-hand-holding-heart" style="color: green;"></i></h1>
                    <div class="sponsor-site-picture">
                        <img src="${requestScope.utour.sponsor}" alt="Avatar"
                             class="avatar-sponsor ">
                    </div>
                </div>
                <div class="line-section">
                    <p></p>
                </div>
                <c:if test="${requestScope.ufinishTournament eq 'true'}">
                    <div class="sponsor-site">
                        <a class="custom-button_2" href="LoadTournamentRankingController?action=load&tournamentID=${requestScope.utour.tournamentID}">Ranking</a>
                    </div>
                </c:if>

                <!-- div-site -->
                <div class="line-section">
                    <p></p>
                </div>
                <!-- Section 7 -->
                <c:if test="${!empty requestScope.urounds}">
                    <div class="tnm-heading mt-5">
                        <h1>Round</h1>
                    </div>
                    <div class="basic-tnm-form">
                        <c:forEach items="${requestScope.urounds}" var="r">
                            <div class="basic-tnm-form-categories">
                                <c:choose>
                                    <c:when test="${requestScope.uround.roundID == r.roundID}">
                                        <a style="color:burlywood" id="roud-id" href="RoundController?roundID=${r.roundID}&roundStatus=${r.roundStatus}&roundName=${r.roundName}&ID=${utour.tournamentID}">${r.roundName}</a><br/>
                                    </c:when>
                                    <c:otherwise>
                                        <a id="roud-id" href="RoundController?roundID=${r.roundID}&roundStatus=${r.roundStatus}&roundName=${r.roundName}&ID=${utour.tournamentID}">${r.roundName}</a><br/>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${r.roundStatus == 0}">
                                        <a>Coming soon</a>
                                    </c:when>
                                    <c:when test="${r.roundStatus == 1}">
                                        <a>On Going</a>
                                    </c:when>
                                    <c:when test="${r.roundStatus == 2}">
                                        <a>Finish</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a></a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="line-section">
                        <p></p>
                    </div>
                    <c:if test="${!empty requestScope.uround.roundID}">
                        <div class="round-detail">
                            <div class="round-detail-site" style="border-right: 4px solid #A87B24;">
                                <p>Type of Round: ${requestScope.uround.typeOfRound}</p>
                                <p>Number Bird Attend:   ${requestScope.uround.birdAttend}</p>
                                <p>
                                    Status: 
                                    <c:choose>
                                        <c:when test="${requestScope.uround.roundStatus == 0}">
                                            <a>Coming soon</a>
                                        </c:when>
                                        <c:when test="${requestScope.uround.roundStatus == 1}">
                                            <a>On Going</a>
                                        </c:when>
                                        <c:when test="${requestScope.uround.roundStatus == 2}">
                                            <a>Finish</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a></a>
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                            <div class="player-site">
                                <h1><i class="fa-sharp fa-solid fa-person"></i> Number Bird Pass</h1>
                                <p>${requestScope.uround.birdPass}</p>
                            </div>
                        </div>
                        </form>

                        <c:if test="${not empty ucands}">



                            <div>
                                <c:choose>
                                    <c:when test="${requestScope.uround.roundStatus == 2 && requestScope.uround.roundName eq 'Top4'}">
                                        <h1 class="candidate-heading">Winner</h1>
                                    </c:when>

                                    <c:otherwise>
                                        <h1 class="candidate-heading">Participant</h1>
                                    </c:otherwise>
                                </c:choose>
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th scope="col">CID</th>
                                            <th scope="col">Bird Name</th>
                                            <th scope="col">Owner Name</th>

                                            <th scope="col">Point</th>
                                                <c:choose>
                                                    <c:when test="${requestScope.uround.roundName eq 'Top4'}">
                                                    <th scope="col">TOP</th>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <th scope="col">Result</th>
                                                    </c:otherwise>
                                                </c:choose>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.ucands}" var="c">
                                        <form action="UpdateCandidatesController" method="GET">
                                            <tr>
                                                <th scope="row">${c.candidatesID}</th>
                                                <td>${c.bird.birdName}</td>
                                                <td>${c.bird.account.name}</td>



                                                <c:choose>
                                                    <c:when test="${requestScope.unexton eq 'true' && requestScope.uround.roundID ne c.round.roundID}">
                                                        <td></td>
                                                    </c:when>
                                                    <c:when test="${requestScope.unexton eq 'true' && requestScope.uround.roundID eq c.round.roundID}">
                                                        <td>${c.score}</td>
                                                    </c:when>
                                                    <c:when test="${requestScope.unexton eq 'false'}">
                                                        <td>${c.score}</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td>${c.score}</td>
                                                    </c:otherwise>
                                                </c:choose>   
                                                <c:choose>
                                                    <c:when test="${c.result eq 'pass' && requestScope.uround.roundID eq c.round.roundID}">
                                                        <td style="font-weight: bold; color: green">${c.result}</td>
                                                    </c:when>
                                                    <c:when test="${c.result eq 'fail' && requestScope.uround.roundID eq c.round.roundID}">
                                                        <td style="font-weight: bold; color: red">${c.result}</td>
                                                    </c:when>
                                                    <c:when test="${requestScope.unexton eq 'true' && requestScope.uround.roundID ne c.round.roundID}">
                                                        <td style="font-weight: bold; color: green">pass</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td style="font-weight: bold; color: red">${c.result}</td>
                                                    </c:otherwise>
                                                </c:choose>
                                            </tr>
                                        </form>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div> 
                        </c:if>
                    </c:if>
                </c:if>
                <c:if test="${empty requestScope.urounds && requestScope.registered eq 'false'}">
                    <c:choose>
                        <c:when test="${requestScope.utour.tournamentStatus == 0}">
                            <div class="regis-site container">
                                <div class="regis-both-site">
                                    <div class="regis-left-site">
                                        <h1><a>Please wait until the date of the registration form</h1></a>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${requestScope.utour.tournamentStatus == 1}">
                            <div class="regis-site container">
                                <div class="regis-both-site">
                                    <div class="regis-left-site">
                                        <h1><a>PLAY WITH US NOW</h1>
                                        <p>Gift, Achievement waiting you</p>
                                        <c:choose>
                                            <c:when test="${!empty sessionScope.acc}">
                                                <a class="left-join-tnm" style="color: white" href="MainController?action=RegisterForm&tID=${requestScope.utour.tournamentID}&aID=${sessionScope.acc.accountID}">PLAY NOW</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a class="left-join-tnm" style="color: white" href="login.jsp">PLAY NOW</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${requestScope.utour.tournamentStatus == 2}">
                            <div class="regis-site container">
                                <div class="regis-both-site">
                                    <div class="regis-left-site">
                                        <h1><a>Oh Sorry :( The form has been closed. Please choose another tournament</h1></a>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${requestScope.utour.tournamentStatus == 3}">
                            <div class="regis-site container">
                                <div class="regis-both-site">
                                    <div class="regis-left-site">
                                        <h1><a>The match is in progress so can't register</h1></a>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${requestScope.utour.tournamentStatus == 4}">
                            <div class="regis-site container">
                                <div class="regis-both-site">
                                    <div class="regis-left-site">
                                        <h1><a>Oh Sorry :( The Tournament finished.</h1></a>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${requestScope.utour.tournamentStatus == 5}">
                            <div class="regis-site container">
                                <div class="regis-both-site">
                                    <div class="regis-left-site">
                                        <h1><a>Sorry for this problem. Tournament is pausing</h1></a>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="regis-site container">
                                <div class="regis-both-site">
                                    <div class="regis-left-site">
                                        <h1><a>Sorry for this problem. Tournament canceled.</h1></a>
                                    </div>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </div>
        </div>

        <footer>
            <%@ include file="footer.jsp" %>
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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="stylesheet" href="CSS/guest-userPage.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>Welcome</title>
    </head>
    <body>
        <header>
            <c:choose>
                <c:when test="${sessionScope.acc.role == 0}">
                    <%@include file="userHeader.jsp" %>
                </c:when>
                <c:when test="${sessionScope.acc.role == 1}">
                    <%@include file="adminHeader.jsp" %>
                </c:when>
                <c:otherwise>
                    <%@include file="guestHeader.jsp"%>
                </c:otherwise>
            </c:choose>
        </header>
        <section style="margin-bottom: 50px;">
            <!-- Tnm-site -->
            <div class="container tnm-site">
                <div class="tnm-heading-content d-flex justify-content-center align-item-center mt-4">
                    <p>NEAR AND BIG TOURNAMENT</p> 
                    <i class='fas fa-certificate' style='font-size:24px;color: orange ;'></i>
                </div>
                <div class="tnm-card-site">
                    <c:forEach var="list" items="${sessionScope.GET_TOURNAMENT}">
                        <div class="card" style="width: 35rem; height: 65rem;">
                            <div class="card-img" style="width: 35rem; height: 30rem;">
                                <img class="card-img-top" src="${list.image}" alt="Card image cap">
                            </div>
                            <div class="card-body tnm-card-body"  style="height: 70rem;">
                                <div class="tnm-card-top-body">
                                    <div class="tnm-card-left-site">
                                        <h5>Tournament Name:</h5>
                                        <h5>Date and Time: </h5>
                                        <h5>Status: </h5>
                                        <h5>Number of Player(min): </h5>
                                        <h5>Number of Player(max): </h5>
                                        <h5>Tournament Fee: </h5>
                                        <h5>Prizes: </h5>
                                    </div>
                                    <div class="tnm-card-right-site">
                                        <p>${list.tournamentName}</p>
                                        <p>${list.dateTime}</p>
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
                                        <p>${list.minParticipant}</p>
                                        <p>${list.maxParticipant}</p>
                                        <p>${list.fee} VND</p>
                                        <p>${list.prize} VND</p>
                                    </div>
                                </div>
                                        <div class="card-end">
                                            <a href="MainController?action=TOURNAMENT_DETAIL&ID=${list.tournamentID}">More Detail</a>
                                        </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>   
            </div>
            <!-- Blog site -->
            <div class=" container blog-site">
                <div class="blog-site-heading d-flex justify-content-center align-item-center mt-4">
                    <p class="m-5">BLOGS<i class='fas fa-certificate' style='font-size:24px;color: orange ;'></i></p>
                </div>
                <div class="blog-content-both-site">
                    <c:forEach var="a" items="${sessionScope.GET_BLOG}">
                        <div class="card" style="width: 35rem; height: 60rem; ">
                            <img src="${a.getMedia()}" class="card-img-top" alt="...">
                            <div class="card-body">
                                <div style="display: flex; justify-content: flex-end"><p style="font-size: 15px; color: green">${a.getCreateTime()}</p></div>
                                <h1 class="card-text">${a.getTitle()}</h1>
                                <div class="card-end-2" style="display: flex; justify-content: flex-end; padding: 0px 30px; margin-top: 150px;" >
                                    <a href="MainController?action=BlogDetail&blogID=${a.getBlogID()}">Read more</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>   
            </div>
            <!-- bird site -->
            <div class=" container bird-site">
                <div class="bird-site-heading d-flex justify-content-center align-items-center mt-4">
                    <p>TOP HOT BIRDS 2023<i class='fas fa-certificate' style='font-size:24px;color: orange ;'></i></p>
                </div>
                <div class="bird-site-main-body mt-5">
                    <!-- 1 -->
                    <c:forEach var="bird" varStatus="counter" items="${sessionScope.GET_BIRD}">
                        <div class="card" style="width: 35rem;">
                            <img class="card-img-top" src="${bird.birdPhoto}" alt="Card image cap">
                            <div class="card-body" style="height: 40rem;">
                                <div class="bird-rank-number d-flex justify-content-center">
                                    TOP ${counter.count}
                                </div>
                                <div class="bird-info-site">
                                    <div class="bird-left-info-site">
                                        <h4>Bird Name:</h4>
                                        <h4>Height:</h4>
                                        <h4>Weight:</h4>
                                        <h4>Color:</h4>
                                        <h4>Bird Status:</h4>
                                        <h4>Score:</h4>
                                    </div>
                                    <div class="bird-left-right-site">
                                        <h4>${bird.birdName}</h4>
                                        <h4>${bird.height}</h4>
                                        <h4>${bird.weight}</h4>
                                        <h4>${bird.color}</h4>
                                        <h4>
                                            <c:choose>
                                                <c:when test="${bird.birdStatus == 1}">Live</c:when>
                                                <c:otherwise>Dead</c:otherwise>
                                            </c:choose></h4>
                                        <h4>${bird.achivement.totalScore}</h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="view-ranking-site w-100 d-flex justify-content-end align-item-center">
                    <a class="custom-button_2" href="LoadTotalRankingController?action=load">View Ranking</a>
                </div>
            </div>
           
            <c:if test="${sessionScope.acc.role != 1}">
                <!-- Support-site -->
                <div class=" container sp-site">
                    <div class="sp-both-site">
                        <div class="sp-left-site">
                            <div class="card" style="width: 50rem;">
                                <img class="card-img-top" src="https://i.pinimg.com/236x/f6/62/af/f662af3b9a1cd3cf54d5ce49295bb25c.jpg" alt="Card image cap">
                            </div>
                        </div>
                        <div class="sp-right-site">
                            <p class="sp-right-site-heading">We Are Professional Support Team<br>
                            <p class="sp-right-site-sub">Let us know when you have problems</p>
                            <p><a href=""><i class="fa fa-facebook-f" style="font-size:24px"></i>     An Le</a></p>
                            <p><a href=""><i class="fa fa-envelope" style="font-size:24px"></i>     ledinhduyan@gmail.com</a></p>
                            <p><a href=""><i class="fa fa-phone" style="font-size:24px"></i>     (78)454-33-432</a></p>
                        </div>
                    </div>
                </div>
                <!-- Sub site -->
                <div class="sub-site container">
                    <div class="sub-box-site">
                        <div class="sub-box-left-site">
                            <img src="https://www.saigontourist.net/images/promotion.png" alt="">
                        </div>
                        <div class="sub-box-right-site">
                            <h4>FREE CONSULTATION</h4>
                            <P>Free online consultation support</P>
                        </div>
                    </div>
                    <!-- 2 -->
                    <div class="sub-box-site">
                        <div class="sub-box-left-site">
                            <img src="https://www.saigontourist.net/images/star.png" alt="">
                        </div>
                        <div class="sub-box-right-site">
                            <h4>PRESTIGE WEBSITE</h4>
                            <P>Top reputable website in Vietnam</P>
                        </div>
                    </div>
                    <!-- 3 -->
                    <div class="sub-box-site">
                        <div class="sub-box-left-site">
                            <img src="https://www.saigontourist.net/images/price.png" alt="">
                        </div>
                        <div class="sub-box-right-site">
                            <h4>GUI Friendly</h4>
                            <P>Quick and simple operation</P>
                        </div>
                    </div>
                    <!-- 4 -->
                    <div class="sub-box-site">
                        <div class="sub-box-left-site">
                            <img src="https://www.saigontourist.net/images/star.png" alt="">
                        </div>
                        <div class="sub-box-right-site">
                            <h4>PROFESSIONAL ENVIRONMENT</h4>
                            <P>Trustworthy and legit</P>
                        </div>
                    </div>
                </div>
            </c:if>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>

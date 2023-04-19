<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="./CSS/registrationForm.css">
        <title>Registration Form</title>
    </head>
</head>
<body>
    <header>
        <%@include file="userHeader.jsp" %>
    </header>
    <section>
        <form action="MainController" method="post">
            <div class="registration-form container">
                <div class="registration-main-form">
                    <!-- left site -->
                    <div class="registration-left-site">
                        <!-- img site -->
                        <div class="registration-img-form-tnm">
                            <img class="card-img-top"
                                 src="${sessionScope.DETAIL_TOUR.tour.image}"
                                 alt="Card image cap">
                        </div>
                        <div class="registration-inf-form-tnm">
                            <div class="mb-3 large-categories">
                                <label class="form-label form-name-categories">Tournament Name</label>
                                <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="${sessionScope.DETAIL_TOUR.tour.tournamentName}" readonly=""/>
                            </div>
                            <div class="mb-3 large-categories">
                                <label class="form-label form-name-categories">Status</label>
                                <input type="text" class="form-control" id="exampleInputPassword1" value="${sessionScope.DETAIL_TOUR.tour.tournamentStatus == 0 ? 'Up Coming' : (sessionScope.DETAIL_TOUR.tour.tournamentStatus == 1 ? 'Open Form' : (sessionScope.DETAIL_TOUR.tour.tournamentStatus == 2 ? 'Close Form' : (sessionScope.DETAIL_TOUR.tour.tournamentStatus == 3 ? 'On Going' : (sessionScope.DETAIL_TOUR.tour.tournamentStatus == 4 ? 'Finished' : 'Delay'))))}" readonly="">
                            </div>
                            <!-- Small input -->
                            <div class="other-inf-tnm-regis-form">
                                <div class="mb-3 small-categories">
                                    <label class="form-label form-name-categories"> Player</label>
                                    <input type="text" class="form-control" id="exampleInputPassword1" value="${sessionScope.DETAIL_TOUR.tour.minParticipant}" readonly="">
                                </div>
                                <div class="mb-3 small-categories">
                                    <label class="form-label form-name-categories">Date Time</label>
                                    <input type="text" class="form-control" id="exampleInputPassword1" value="${sessionScope.DETAIL_TOUR.tour.dateTime}" readonly="">
                                </div>
                                <div class="mb-3 small-categories">
                                    <label class="form-label form-name-categories">Location</label>
                                    <input type="text" class="form-control" id="exampleInputPassword1" value="${sessionScope.DETAIL_TOUR.tour.location}" readonly="">
                                </div>
                            </div>
                            <!-- Sponsor -->
                            <div class="mb-3 large-categories">
                                <label class="form-label form-name-categories">Main Sponsor </label>
                                <div class="img-sponsor">
                                    <img src="${sessionScope.DETAIL_TOUR.tour.sponsor}"
                                         alt="Avatar" class="avatar">
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- right -site -->
                    <div class="registration-right-site">
                        <div class="registration-right-heading">
                            <h1>REGISTRATION FORM</h1>
                        </div>
                        <div class="registration-right-form">
                            <div class="mb-3 right-small-site">
                                <label for="exampleInputEmail1" class="form-label lable-right">Telephone number Now*</label>
                                <input type="tel" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" readonly="" value="${sessionScope.acc.phone}">
                                <div id="emailHelp" class="form-text" style="color:#ffff;">If you want to change telephone. Go to [Account Setting] </div>
                            </div>
                            <div class="mb-3 right-small-site">
                                <label class="form-label lable-right">Choose your bird</label><br>
                                <c:forEach var="b" items="${sessionScope.DETAIL_BIRD}" varStatus="loop">
                                    <div class="bird-option" style="margin-bottom: 20%; ">
                                        <input type="radio" name="birdName" id="birdName_${loop.index}" value="${b.bird.birdName}" required="">
                                        <label for="birdName_${loop.index}" class="form-label lable-right">Bird ${b.bird.birdName}</label>
                                        <img src="${b.bird.birdPhoto}" width="150" height="150">
                                        <input type="hidden" name="birdPhoto" value="${b.bird.birdPhoto}">
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="fee-registration right-small-site" style="padding-right: 100px; width: 60%;">
                                <label class="form-label lable-right"> Tournament Fee</label>
                                <input type="text" class="form-control" id="exampleInputPassword1" value="${sessionScope.DETAIL_TOUR.tour.fee} VND" readonly="">
                                <div class="form-text" style="color: #ffff;">Cash Payment Only</div>
                            </div>
                        </div>
                        <div class="right-button">
                            <button type="submit" name="action" value="ConfirmForm">NEXT</button>
                            <input type="hidden" name="accID" value="${sessionScope.acc.accountID}">
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </section>
    <footer>
        <%@include file="footer.jsp" %>
    </footer>
</body>
</html>

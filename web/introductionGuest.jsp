<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
        <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
        <link href='https://fonts.googleapis.com/css?family=Baloo' rel='stylesheet'>
        <link href="./CSS/introductionPage.css" rel="stylesheet">
        <title>Introduction Page</title>
    </head>
    <body>
        <!--header site-->
        <header>
            <%@include file="guestHeader.jsp" %>
        </header>
        <div class="introduction-site-container">
            <div class="introduction-main-site-container">
                <!-- heading img title site introduction -->
                <div class="introduction-img-background-heading-site">
                    <!-- Introduction main title  -->
                    <div class="title-introduction-para">
                        <h3>BIRD TOURNAMENT COMMUNITY</h3>
                        <h1>INTRODUCTION</h1>
                    </div>
                </div>
                <!-- other categories section -->
                <div class="section-categories-introduction-site container">
                    <!-- The root of community -->
                    <div class="one-section-categories-introduction-site">
                        <h1>The root of our community</h1>
                        <p>We recognize that many people who are interested in bird want to have a community among "bird player", particularly "Hill Myna". Therefore, we want to create a 
                            community for all want to learn, find and interact with other people in community throught out tournaments, blogs, and bird ranking that we provide. The most 
                            main feature in this community is small and big tournaments among birds in "this area".
                            <!-- picture are -->
                        <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <img class="d-block w-100" src="./image/intro6.jpg" alt="First slide">
                                </div>
                                <div class="carousel-item">
                                    <img class="d-block w-100" src="./image/intro4.jpg" alt="Second slide">
                                </div>
                                <div class="carousel-item">
                                    <img class="d-block w-100" src="./image/intro2.jpg" alt="Third slide">
                                </div>
                            </div>
                            <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>
                        </p>
                    </div>
                    <!-- rules and policies -->
                    <div class="one-section-categories-introduction-site">
                        <h1>The rules and policies of community</h1>
                        <ul>
                            <li><h5>Eliglibility:</h5> Tournaments may have specific eligibility requirements, such as age limits, skill levels, or membership in a particular organization.</li>
                            <li><h5>Registration:</h5>Participants must register for the tournament before a specified deadline and pay a fee, if applicable.</li>
                            <li><h5>Format:</h5>The tournament format may be single-elimination, double-elimination, round-robin, or another structure. The number of matches, rounds, or games may also vary.</li>
                            <li><h5>Schedule:</h5>The schedule of matches, including start times and locations, will be communicated to participants in advance.</li>
                            <li><h5>Rules of play:</h5>The rules of the game or sport will be enforced during the tournament. Participants should familiarize themselves with the rules prior to the event.</li>
                            <li><h5>Conduct:</h5>Participants are expected to behave in a sportsmanlike manner and follow all tournament policies and rules. Unsportsmanlike behavior may result in disqualification.</li>
                            <li> <h5>Equipment:</h5>Participants must bring their own equipment and ensure it meets the tournament's requirements. For team sports, uniforms may also be required.</li>
                            <li><h5>Safety:</h5>The tournament organizers will take measures to ensure the safety of all participants. Participants are expected to follow safety guidelines and report any accidents or injuries.</li>
                            <li><h5>Awards</h5>Prizes or awards may be given to winners or participants in certain categories.</li>
                            <h5>***It's important to read and understand the rules and policies of a tournament before participating to ensure a fair and enjoyable experience for all involved.</h5>

                        </ul>
                        <!-- picture area -->
                        <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <img class="d-block w-100" src="./image/intro5.jpg" alt="First slide">
                                </div>
                                <div class="carousel-item">
                                    <img class="d-block w-100" src="./image/intro6.jpg" alt="Second slide">
                                </div>
                                <div class="carousel-item">
                                    <img class="d-block w-100" src="./image/intro3.jpg" alt="Third slide">
                                </div>
                            </div>
                            <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>
                    </div>
                    <!-- About us and Contact -->
                    <div class="one-section-categories-introduction-site">
                        <h1>About us and Contact</h1>
                        <p>We are admin of community and those who create and manage tournaments about bird as well as create and manage this website to help players have more good experience.When ypu have 
                            anyproblmes or question. Please contact us throught:
                        </p>
                        <div class="social-admin-site">
                            <li><i class="fa-brands fa-facebook-f"></i>Facebook</li>
                            <li><i class="fa-solid fa-phone"></i>(043)234-23-112</li>
                            <li><i class="fa-solid fa-envelope"></i>An Le</li>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>

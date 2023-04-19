<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="stylesheet" href="./CSS/account.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>Profile</title>
    </head>
    <body>
        <header>
            <c:choose>
                <c:when test="${sessionScope.acc.role == 0}">
                    <%@include file="userHeader.jsp" %>
                </c:when>
                <c:otherwise><%@include file="adminHeader.jsp" %></c:otherwise>
            </c:choose>
        </header>
        <section>
            <!-- Both account site -->
            <div class="both-site-account">
                <!-- left account site -->
                <div class="left-site-account">
                    <div class="left-top-site-account">
                        <img src="${sessionScope.acc.profilePhoto}" alt="Avatar" class="avatar1">
                        <p>${sessionScope.acc.name}</p>
                    </div>
                    <div class="left-bottom-site-account">
                        <p><i class="fa fa-phone" style="font-size:20px"></i>${sessionScope.acc.phone}</p>
                        <p><i class="fa fa-envelope" style="font-size:20px"></i>${sessionScope.acc.email}</p>
                    </div>
                </div>
                <!-- right account site -->
                <div class="right-site-account">
                    <div class="private-inf-account">
                        <div class="header-inf">
                            <p>ACCOUNT INFORMATION</p>
                        </div>
                        <form class="form-inf-account">
                            <div class="icon-update">
                                <a href=""> <i class='fas fa-pen' style='font-size:25px'></i></a>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Email address</label>
                                <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="${sessionScope.acc.email}" readonly="">
                                <small id="emailHelp" class="form-text text-muted">You never share your email with anyone else.</small>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Telephone number</label>
                                <input type="text" class="form-control" id="exampleInputPassword1" value="0${sessionScope.acc.phone}" readonly="">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Full Name</label>
                                <input type="tel" class="form-control" id="exampleInputPassword1" value="${sessionScope.acc.name}" readonly="">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Password</label>
                                <input type="password" class="form-control" id="exampleInputPassword1" value="${sessionScope.acc.password}" readonly="">
                            </div>
                        </form>
                    </div>
                    <!-- Update account information form -->
                    <form action="">
                        <div class="private-inf-account">
                            <div class="header-inf">
                                <p>ACCOUNT UPDATE FORM</p>
                            </div>
                            <div class="form-inf-account">
                                <div class="form-group">
                                    <div class="icon-update">
                                        <a href=""> <i class='fas fa-pen' style='font-size:20px'></i></a>
                                    </div>
                                    <label for="exampleInputEmail1">Email address</label>
                                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="${requestScope.Profile_Form.getEmail()}" placeholder="Enter email" name="email-update" required pattern=".{1,40}@gmail\.com">
                                    <small id="emailHelp" class="form-text text-muted">You never share your email with anyone else.</small>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Telephone number</label>
                                    <input type="tel" class="form-control" id="exampleInputPassword1" placeholder="Telephone" value="${requestScope.Profile_Form.getPhone()}" name="phone-update" required pattern="[0-9]{10}">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Full Name</label>
                                    <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Name" value="${requestScope.Profile_Form.getName()}" name="name-update" required pattern=".{6,17}">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Password</label>
                                    <input type="password" class="form-control" id="exampleInputPassword1"value="${requestScope.Profile_Form.getPassword()}" name="password-update" placeholder="*******" required pattern=".{1,50}">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Re-Password</label>
                                    <input type="password" class="form-control" id="exampleInputPassword1"value="${requestScope.Profile_Form.getPassword()}" name="repass-update" placeholder="*******" required pattern=".{1,50}">
                                </div>
                                <div class="btn-update-acc">
                                    <a class="btn btn-danger btn-close" href="homePage.jsp"><p>Close</p></a>
                                    <form action="MainController" method="POST">
                                        <input type="hidden" name="accID" value="${sessionScope.acc.accountID}"/>
                                        <button class="btn btn-update btn-success" value="Update_Account" type="submit" name="action"><p>Update</p></button>
                                    </form>
                                </div>
                            </div>  
                        </div>
                    </form>
                </div>
            </div>
        </section>
        <!-- footer -->
        <footer>
            <%@include file="footer.jsp"%>
        </footer>
    </body>
</html>

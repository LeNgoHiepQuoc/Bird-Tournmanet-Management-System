<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <link href="./CSS/blogs.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
        <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
        <title>Blogs</title>
    </head>
    <body>
        <!--header section-->
        <header>
            <c:choose>
                <c:when test="${sessionScope.acc.role == 0}">
                    <%@include file="userHeader.jsp" %>
                </c:when>
                <c:otherwise>
                    <%@include file="guestHeader.jsp"%>
                </c:otherwise>
            </c:choose>
        </header>
        <!-- body section -->
        <div class="blogs-container-section">
            <div class="blogs-main-site">
                <div class="introduction-blogs-site">
                    <div class=" card-top" style="width: 100%;">
                        <h5>TOURNMANT AND BIRD</h5>
                        <h1>BLOGS</h1>
                    </div>
                </div>
                <!------------------------------->
                <!-- other-blog-site-small -->
                <c:forEach var = "a" items="${sessionScope.LIST_BLOG}">
                    <div class="small-categories-blogs-site container">
                        <!-- each card blog site -->
                        <div class="small-categories-blogs-main-body-site">
                            <div class=" card-categories">
                                <div class="card-categories-img-left-site">
                                    <img class="card-img-top" src="${a.getMedia()}" alt="Card image cap">
                                </div>
                                <div class="card-body time-heading-site">
                                    <h5>${a.getTitle()}</h5>
                                    <p>${a.getCreateTime()}</p>
                                    <div class="link-read-button">
                                        <a href="MainController?action=BlogDetail&blogID=${a.getBlogID()}">Read more</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <!-- footer section -->
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>

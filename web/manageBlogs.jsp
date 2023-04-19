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
        <link href="CSS/blogManagement.css" rel="stylesheet">
        <title>Manage Blogs</title>
    </head>
    <body>
        <!--header-->
        <header>
            <%@include file="adminHeader.jsp" %>
        </header>
        <div class="blog-admin-site-page container">
            <div class="blog-admin-site-categories w-100">
                <div class="blogs-add-button d-flex">
                    <a href="manageAddBlogs.jsp" style="color: white">Add New Blog</a>
                </div>
                <div class="one-blog-admin-site-categories">
                    <c:forEach var="a" items="${sessionScope.LIST_BLOG}">
                        <div class="card-blog">
                            <img class="card-img-top" src="${a.getMedia()}" alt="Card image cap">
                            <div class="card-body">
                                <div class="id-blog-site">#${a.getBlogID()}</div>
                                <p style="font-size: 15px;">${a.getCreateTime()}</p>
                                <h5 class="card-title">${a.getTitle()}</h5>
                                <p class="card-blog-body">${a.getBody()}</p>
                                <div class="blogs-card-button">
                                    <a href="MainController?action=UPDATE_BLOGS&blogID=${a.getBlogID()}">Update</a>
                                    <a href="MainController?action=DELETE_BLOGS&blogID=${a.getBlogID()}" style="color: white">Delete</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>

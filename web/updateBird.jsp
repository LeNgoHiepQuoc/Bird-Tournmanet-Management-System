<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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
        <link rel="stylesheet" href="./CSS/updateBird.css">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </head>
    <body>
        <header>
            <%@include file="userHeader.jsp" %>
        </header>
        <div class="update-bird-form container">
            <div class="update-bird-form-main">
                <form action="MainController" method="POST">
                    <div class="update-bird-heading-form">
                        <h1>Bird Update Form</h1>
                    </div>
                    <input type="hidden" name="accID" class="form-control" value="${sessionScope.acc.accountID}"/>
                    <input type="hidden" name="birdID" class="form-control" value="${sessionScope.birdID}"/>
                    <div class="form-group">
                        <label>Bird Name :</label><br>
                        <input type="text" value="${requestScope.bird.getBirdName()}" name="bName" class="form-control input-form" required pattern=".{6,17}">
                    </div>
                    <div class="form-group">
                        <label>Height(cm) :</label><br>
                        <input type="tet" value="${requestScope.bird.getHeight()}" name="bHeight" class="form-control input-form" required>
                    </div>
                    <div class="form-group">
                        <label>Weight(gam) :</label><br>
                        <input type="tet" value="${requestScope.bird.getWeight()}" name="bWeight" class="form-control input-form" required> 
                    </div>
                    <div class="form-group">
                        Select categories: 
                        <select name="bCate" required>
                            <c:forEach var="b" items="${sessionScope.BIRD_CATE}">
                                <option value="${b.categoriesID}">${b.categoriesName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Color :</label><br>
                        <input type="text"  value="${requestScope.bird.getColor()}" name="bColor" class="form-control input-form" required>
                    </div>
                    <div class="form-group">
                        <label>Status: </label>
                        <c:choose>
                            <c:when test="${requestScope.bird.getBirdStatus() == 1}">
                                <select name="bStatus" required>
                                    <option value=0> Dead</option>
                                    <option value=1> Alive</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                <select name="bStatus">
                                    <option value=0> Dead</option>
                                </select>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Dentification</label>
                        <input type="text" class="form-control" name="denfitication" required value="${requestScope.bird.getDentification()}">
                    </div>

                    <div class="form-group">
                        <label >Tournament Image :</label>
                        <button type="button"onclick="document.getElementById('input-img2').click()">Upload</button>
                        <div>
                            <label for="input-img2" class="preview">
                                <img class="tournament-img" id="previewImg2" src="${requestScope.bird.getBirdPhoto()}">
                            </label>
                            <input type="file" hidden id="input-img2" name="image" required/>
                        </div>
                    </div>
                    <div class=" btn-update-bird">
                        <button type="submit" class="btn btn-primary" name="action" value="updateBird">Update</button>
                    </div>
            </form>
        </div>
    </div>
<foote>
    <%@include file="footer.jsp" %>
</foote>
</body>
<script> const fileInput2 = document.getElementById('input-img2');
    const previewImg2 = document.getElementById('previewImg2');
    fileInput2.addEventListener('change', function () {
        if (fileInput2.files && fileInput2.files[0]) {
            const reader = new FileReader();
            reader.addEventListener('load', function (e) {
                previewImg2.src = e.target.result;
            });
            reader.readAsDataURL(fileInput2.files[0]);
        }
    });</script>
</html>

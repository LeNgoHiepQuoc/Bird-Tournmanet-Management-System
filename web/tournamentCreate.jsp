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
        <link rel="stylesheet" href="CSS/createTournament.css">
        <title>Registration Form</title>
    </head>
</head>
<body>
    <header>
        <%@include file="adminHeader.jsp" %>
    </header>
    <section>
        <div class="registration-form container">
            <div class="registration-main-form">
                <!-- left site -->
                <div class="registration-left-site">
                    <div class="registration-inf-form-tnm">
                        <div class="registration-right-heading">
                            <h1>CREATE<br>TOURNAMENT<br>FORM</h1>
                        </div>
                    </div>
                </div>
                <!-- right -site -->
                <form action="MainController" method="post">
                    <div class="registration-right-form">
                        <div class="registration-inf-form-tnm">
                            <div class="mb-3 large-categories">
                                <label class="form-label form-name-categories">Tournament Name*</label>
                                <input type="text" class="form-control" name="tournamentName" required=""/>
                            </div>
                            <!-- Small input -->
                            <div class="other-inf-tnm-regis-form">
                                <div class="mb-3 small-categories">
                                    <label class="form-label form-name-categories">Date Time*</label>
                                    <input type="text" class="form-control" name="dateTime" required="">
                                </div>
                                <div class="mb-3 small-categories">
                                    <label class="form-label form-name-categories">Location*</label>
                                    <input type="text" class="form-control" name="location" required="">
                                </div>
                            </div>
                            <div class="other-inf-tnm-regis-form">
                                <div class="mb-3 small-categories">
                                    <label class="form-label form-name-categories">Min Slot*</label>
                                    <input type="text" class="form-control" name="minSlot" required="">
                                </div>
                                <div class="mb-3 small-categories">
                                    <label class="form-label form-name-categories">Max Slot*</label>
                                    <input type="text" class="form-control" name="maxSlot" required="">
                                </div>
                            </div>
                            <div class="mb-3 small-categories">
                                <label class="form-label lable-right">Prize*</label>
                                <br>
                                <p class="custom-font-2"><input type="text" class="form-control-custom" name="prize" required="" >  VND</p>
                            </div>
                            <div class="mb-3 small-categories">
                                <label class="form-label lable-right">Choose Bird Category*</label><br>
                                <select class="section-bird" name="bCateID">
                                    <c:forEach var="b" items="${sessionScope.BIRD_CATE}">
                                        <option value="${b.categoriesID}">${b.categoriesName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="fee-registration mb-3 small-categories">
                                <label class="form-label lable-right custom-label"> Description*</label>
                                <br>
                                <textarea class="custom-font-3" rows="4" cols="35" name="description"></textarea>
                            </div>
                            <div class="fee-registration mb-3 small-categories">
                                <label class="form-label lable-right"> Fee*</label>
                                <p class="custom-font-2"><input type="text" class="form-control-custom" name="fee" required="">  VND</p>
                            </div>
                            <!-- Sponsor -->
                            <div class="mb-3 large-categories">
                                <label class="form-label form-name-categories upload-label">Sponsor Image:</label>
                                <button type="button" class="btn btn-light" onclick="document.getElementById('input-img1').click()">Upload</button>
                                <div class="bird-identify-container">
                                    <label for="input-img1" class="preview">
                                        <img class="sponsor-img" id="previewImg1" src="">
                                    </label>
                                    <input type="file" hidden id="input-img1" name="sponsor"/>
                                </div>
                            </div>
                            <!-- image section -->
                            <div class="mb-3 large-categories">
                                <label class="form-label form-name-categories upload-label">Tournament Image:</label>
                                <button type="button" class="btn btn-light" onclick="document.getElementById('input-img2').click()">Upload</button>
                                <div class="bird-identify-container">
                                    <label for="input-img2" class="preview">
                                        <img class="tournament-img" id="previewImg2" src="">
                                    </label>
                                    <input type="file" hidden id="input-img2" name="image"/>
                                </div>
                            </div>
                        </div>
                        <div class="right-button">
                            <button type="submit" name="action" value="CREATE_TOURNAMENT">CREATE</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
    <footer>
        <%@include file="footer.jsp" %>
    </footer>
    <script>
        const fileInput1 = document.getElementById('input-img1');
        const previewImg1 = document.getElementById('previewImg1');
        fileInput1.addEventListener('change', function () {
            if (fileInput1.files && fileInput1.files[0]) {
                const reader = new FileReader();
                reader.addEventListener('load', function (e) {
                    previewImg1.src = e.target.result;
                });
                reader.readAsDataURL(fileInput1.files[0]);
            }
        });

        const fileInput2 = document.getElementById('input-img2');
        const previewImg2 = document.getElementById('previewImg2');
        fileInput2.addEventListener('change', function () {
            if (fileInput2.files && fileInput2.files[0]) {
                const reader = new FileReader();
                reader.addEventListener('load', function (e) {
                    previewImg2.src = e.target.result;
                });
                reader.readAsDataURL(fileInput2.files[0]);
            }
        });
    </script>

</body>
</html>

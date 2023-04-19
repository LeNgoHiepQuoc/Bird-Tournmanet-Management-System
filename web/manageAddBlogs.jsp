<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
        <link href='https://fonts.googleapis.com/css?family=Baloo' rel='stylesheet'>
        <link rel="stylesheet" href="./CSS/addBlogManagement.css">
        <title>Add Blog Form</title>
    </head>
    <body>
        <header>
            <%@include file="adminHeader.jsp" %>
        </header>
        <div class="add-blog-admin-main-site container">
            <div class="add-blog-form">
                <div class="blog-heading-add">
                    <h1>ADD BLOG FORM</h1>
                </div>
                <form action="MainController" method="post">    
                    <div class="card car-blogs-add" style="width: 100%;">
                        <input type="hidden" name="accountID" value="${sessionScope.acc.accountID}">
                        <label for="">Blogs Image</label>
                        <button type="button"onclick="document.getElementById('input-img2').click()">Upload</button>
                        <div>
                            <label for="input-img2" class="preview">
                                <img class="tournament-img" id="previewImg2" src="">
                            </label>
                            <input type="file" hidden id="input-img2" name="Media"/>
                        </div>
                        <div class="card-body">
                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">Blogs Title</label>
                                <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="Title">
                            </div>
                            <div class="mb-3">
                                <label for="exampleInputPassword1" class="form-label">Blog Body</label>
                                <textarea name="Body" id="" rows="20" style="width: 100%;"></textarea>
                            </div>
                            <div class="button-add-site">
                                <button type="submit" class="btn btn-primary" value="AddNewBlog" name="action">ADD NEW BLOG</button>
                            </div> 
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
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
            });
        </script>
    </body>
</html>

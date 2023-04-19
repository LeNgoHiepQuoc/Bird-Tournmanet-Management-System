<%-- 
    Document   : forgotPassword
    Created on : Mar 25, 2023, 9:09:07 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="./CSS/forgotPassword.css">
        <title>Forgot Password | Can not login into home page</title>
        <title>JSP Page</title>
    </head>
    <body>
        <!-- Heading  -->
        <!-- Body -->
        <div class="forgotpassword-site-main-body ">
            <div class="forgotpassword-site-main form-group">
                <h3>Find you account</h3>
                <form action="MainController">
                    <div class="forgot-password-form form-control">
                        <label for="">Please input your email or phonenumber to help them look your account:</label>
                        <input type="text" class="form-control" placeholder="Email or telephone number" name="phoneoremail">    
                        <div class="forgot-password-button-site">
                            <button class="btn btn-secondary" name="action" value="forgotPassordCancle">Cancle</button>
                            <button class="btn btn-primary" name="action" value="forgotPaasswordSearch">Search</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
        <!-- End body -->
        <!-- footer -->
    </body>
</html>

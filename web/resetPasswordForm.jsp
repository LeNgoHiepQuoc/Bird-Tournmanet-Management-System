<%-- 
    Document   : ResetPasswordForm
    Created on : Mar 25, 2023, 10:03:57 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="./forgot.css">
        <title>Reset Password Form</title>
    </head>
    <body>
        <!-- Heading  -->
        <!-- Body -->
        <div class="forgotpassword-site-main-body ">
            <div class="forgotpassword-site-main form-group">
                <h3>Update new Password</h3>
                <form action="MainController" method="POST">
                    <div class="forgot-password-form form-control">
                        <input type="hidden" class="form-control" value="${requestScope.accountdto.getAccountID()}" name="idReset">
                        <input type="text" class="form-control" value="${requestScope.accountdto.getEmail()}" name="emailReset">  
                        <input type="text" class="form-control" value="${requestScope.accountdto.getPhone()}">  
                        <label for="">Please input new password:</label>
                        <input type="password" class="form-control" placeholder="New Password" name="newpassword">  
                        <label for="">Please enter again:</label>
                        <input type="password" class="form-control" placeholder="New PASS" name="renewpassword">      
                        <div class="forgot-password-button-site">
                            <button class="btn btn-secondary" name="action" value="resetPasswordCancel">Cancel</button>
                            <button class="btn btn-primary" name="action" value="resetPasswordUpdate">Update</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
        <!-- End body -->
        <!-- footer -->
    </body>
</html>

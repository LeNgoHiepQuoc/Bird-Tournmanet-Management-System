<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href='https://fonts.googleapis.com/css?family=Baloo' rel='stylesheet'>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="CSS/login.css">
    </head>
    <body>
        <div class="login-container">
            <div class="login-body-container">
                <div class="login-form d-flex justify-content-between">
                    <div class="login-left-site">
                        <div class="left-content">
                            <div class="left-content-heading">
                                <b>Welcome To</b><br>
                                <b>The Bird Tournament</b>
                                <span style="font-size: 25px;">Now here?</span>
                            </div>                              
                        </div>
                        <div class="btn-left">
                            <form action="MainController"  method="POST">
                                <button type="submit" name="action" value="Register" class="btn left-btn">Register</button>
                            </form>
                        </div>
                    </div>
                    <div class="login-right-site">
                        <div class="login-right-site-body">
                            <div class="login-right-heading">
                                <b>SIGN IN</b>
                            </div>
                            <div class="login-register">
                                <!-- Login Form -->
                                <form action="MainController" method="post">
                                    <p class="text-danger">${requestScope.mess}</p>
                                    <input type="email" name="email" value="" class="form-control" placeholder="Enter email"required="" >
                                    <input type="password" name="password" value="" class="form-control" placeholder="Password"required="" >
                                    <a href="forgotPassword.jsp">Forgot Password</a>
                                    <button type="submit" name="action" value="Login"  class="btn btn-login">Sign in</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

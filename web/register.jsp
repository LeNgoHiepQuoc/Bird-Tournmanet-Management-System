<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign up</title>
        <link href='https://fonts.googleapis.com/css?family=Baloo' rel='stylesheet'>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="CSS/register.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
                                <button type="submit" name="action" value="LoginBar" class="btn left-btn">Login</button>
                            </form>
                        </div>
                    </div>
                    <div class="login-right-site">
                        <div class="login-right-site-body">
                            <div class="signup-right-heading">
                                <b>Create new Account</b>
                            </div>
                            <div class="login-register">
                                <!-- Form login  -->
                                <form action="MainController" method="post" id="form">
                                    <div class="form-group">
                                        <input type="text"  placeholder="Enter telephone number" name="phone" value="" id="txtphone" class="form-control"required="" >
                                        <i class="fa fa-check-square"></i>
                                        <i class="fa fa-thumbs-o-down"></i>
                                        <p class="text-danger">${requestScope.ERROR.phone}</p>
                                    </div> 
                                    <div class="form-group">
                                        <input type="email"  placeholder="Enter email" name="email" value="" id="txtemail"  class="form-control"required="">
                                        <i class="fa fa-check-square"></i>
                                        <i class="fa fa-thumbs-o-down"></i>
                                        <p class="text-danger">${requestScope.ERROR.duplicate}</p>
                                    </div>  
                                    <div class="form-group" >
                                        <input type="text"  placeholder="Enter username" name="name" value="" id="txtname"   class="form-control"required="" >
                                        <i class="fa fa-check-square"></i>
                                        <i class="fa fa-thumbs-o-down"></i>
                                        <p class="text-danger">${requestScope.ERROR.name}</p>
                                    </div>      
                                    <div class="form-group">
                                        <input type="password"  placeholder="Password" name="password" value="" id="txspass" class="form-control"required="" >
                                        <i class="fa fa-check-square"></i>
                                        <i class="fa fa-thumbs-o-down"></i>
                                        <p class="text-danger">${requestScope.ERROR.password}</p>
                                    </div> 
                                    <div class="form-group">
                                        <input type="password"  placeholder="Enter password again" name="repass" value="" id="txtRepass" class="form-control"required="">
                                        <i class="fa fa-check-square"></i>
                                        <i class="fa fa-thumbs-o-down"></i>
                                        <p class="text-danger">${requestScope.ERROR.repass}</p>
                                    </div>  
                                    <input type="radio" style="margin-top: 20px;"> I agree with all these policies
                                    <button type="submit" name="action" value="Register" class="btn btn-login">Sign up</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login page</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="buildweb/fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="buildweb/css/style.css">
 
</head>
<body>

    <div class="main">

        <div class="container">
            <div class="signup-content">
                <form action="login" method="POST" id="signup-form" class="signup-form">
                    <h2>Login </h2>
                   <br>
                    <div class="form-group">
                        <input type="text" class="form-input" name="fullname" id="name" placeholder="Your Name"/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-input" name="email" id="email" placeholder="Email"/>
                    </div>                
                    <div class="form-group">
                        <input type="password" class="form-input" name="password" id="password" placeholder="Password"/>
                        <span toggle="#password" class="zmdi zmdi-eye field-icon toggle-password"></span>
                    </div>
                    <div class="form-group">
                        <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                        <label for="agree-term" class="label-agree-term"><span><span></span></span>Remember my detais 
                    </div>
                    <div class="form-group">
                        <input type="submit" name="submit" id="submit" class="form-submit submit" value="Login"/>
                        <a href="RegistrationPage.jsp" class="submit-link submit">Register</a>
                    </div>
                </form>
            </div>
        </div>

    </div>

    <!-- JS -->
    <script src="buildweb/vendor/jquery/jquery.min.js"></script>
    <script src="buildweb/js/main.js"></script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Xworkz-commonModule Application</title>

    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: white;
        }

        h4 {
            text-align: center;
            color: black;
            margin-top: 20px;
        }

        .container {
            width: 50%;
            margin: 0 auto;
            background-color: CornflowerBlue;
            padding: 25px;
            border-radius: 8px;
            box-shadow: black;
            margin-top: 20px;
        }

        .form-group {
            margin-bottom: 10px;
        }

        .msg {
            color: red;
            text-align: center;
            margin-bottom: 10px;
        }

        .msgs {
            color: green;
            text-align: center;
            margin-bottom: 10px;
        }

        .button-container {
            display: flex;
            justify-content: center;
        }

        .navbar-custom {
            background-color: black;
            padding: 10px 0;
        }

        .navbar-brand img {
            height: 50px;
        }

        .navbar-nav {
            margin-left: auto;
        }

        .nav-link {
            font-size: 14px;
            color: white;
            text-decoration: none;
            padding: 1px;
        }

        .nav-link:hover {
            color: #0056b3;
        }

        /* Style for the Forgot Password link */
        .forgot-password-link {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: black; /* Link color */
            font-size: 16px; /* Font size */
        }

        .forgot-password-link:hover {
            text-decoration: underline;
            color: #0056b3; /* Hover color */
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-custom">
    <a class="navbar-brand" href="https://x-workz.in/">
        <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png">
    </a>
    <div class="navbar-nav ml-auto">
        <a class="nav-link" href="SignUp.jsp">SignUp</a>
        <a class="nav-link" href="SignIn.jsp">SignIn</a>
    </div>
</nav>

<!-- Main Content Area -->
<div class="container">
    <!-- Display success, error, and other messages -->
    <div class="msg">${SuccessMsg}</div>
    <div class="msg">${error}</div>
    <div class="msg">${lock}</div>
    <div class="msg">${say}</div>
    <div class="msgs">${message}</div>

    <h4>Xworkz SignIn</h4>

    <!-- SignIn Form -->
    <form action="signIn" method="post">
        <div class="form-group">
            <label for="email" class="font-weight-bold">Email</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
        </div>

        <div class="form-group">
            <label for="password" class="font-weight-bold">Password</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
        </div>

        <div class="form-group">
            <div class="button-container">
                <button type="submit" class="btn btn-success">Submit</button>
            </div>
        </div>
    </form>

    <!-- Forgot Password Link -->
    <a class="forgot-password-link" href="ForgotPassword.jsp">Forgot password? Change here!</a>
</div>


</body>
</html>

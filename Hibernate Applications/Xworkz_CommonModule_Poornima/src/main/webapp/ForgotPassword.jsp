<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reset Password</title>

    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: white;
            margin: 0;
            padding: 0;
           background-image: url('https://th.bing.com/th/id/OIP.QhgRvtMFC7Ih3SeUPY88egHaEo?w=262&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7');
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

        .container {
            width: 50%;
            margin: 0 auto;
            background-color: cornflowerblue;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            margin-top: 40px;
        }

        h4 {
            text-align: center;
            color: black;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .message {
            color: red;
            text-align: center;
            margin-bottom: 10px;
        }

        button {
            width: 50%;
            padding: 12px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }

        .button-container {
            display: flex;
            justify-content: center;
        }

        @media (max-width: 600px) {
            .container {
                width: 90%;
                padding: 20px;
            }

            h4 {
                font-size: 24px;
            }

            button {
                padding: 10px;
                font-size: 14px;
            }
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-custom">
    <a class="navbar-brand" href="https://x-workz.in/">
        <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="Xworkz Logo">
    </a>
    <div class="navbar-nav ml-auto">
        <a class="nav-link" href="SignUp.jsp">SignUp</a>
        <a class="nav-link" href="SignIn.jsp">SignIn</a>
    </div>
</nav>
 <div class="message">${ForgotError}</div>
<div class="container">
    <h4>Set Your Password</h4>

    <form action="forgotPassword" method="POST">

        <div class="form-group">
            <label for="email" class="font-weight-bold">Email:</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
        </div>

        <div class="form-group">
            <label for="newPassword" class="font-weight-bold">New Password:</label>
            <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="Enter your new password" required>
        </div>

        <div class="form-group">
            <label for="confirmPassword" class="font-weight-bold">Confirm Password:</label>
            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Re-enter your new password" required>
        </div>

        <div class="form-group button-container">
            <button type="submit" class="btn btn-success">Save</button>
        </div>

        <div class="message">
            <p>${message}</p>
        </div>
    </form>
</div>

</body>
</html>

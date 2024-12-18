<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .form-container {
            background: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px; /* Adjust max-width to a smaller size for better centering */
            margin: 20px; /* Added margin for spacing around the form */
        }
        .form-container h2 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 24px; /* Make title a little bigger */
        }
        .form-group label {
            font-weight: bold; /* Making labels bold for better visibility */
        }
        .form-control {
            border-radius: 5px; /* Rounded corners for form fields */
        }
        button[type="submit"] {
            margin-top: 20px; /* Space between the form fields and the button */
        }

        /* Responsive adjustments for small screens */
        @media (max-width: 576px) {
            .form-container {
                padding: 20px;
                width: 90%;  /* Adjust form width on smaller screens */
            }
        }
    </style>
</head>
<body>

    <div class="form-container">
        <h2>SignIn Form</h2>
        <form action="send" method="post">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Submit</button>
        </form>
    </div>

</body>
</html>

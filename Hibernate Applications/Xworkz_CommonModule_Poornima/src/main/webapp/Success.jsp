<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Success Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
             background-img:"https://th.bing.com/th/id/OIP.QhgRvtMFC7Ih3SeUPY88egHaEo?w=262&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7";
        }

        .container {
            text-align: center;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: green;
            font-size: 30px;
        }

        p {
            font-size: 18px;
            color: #555;
        }

        .btn {
            background-color: green;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
        }

        .btn:hover {
            background-color: darkgreen;
        }
    </style>
</head>

<body>
${message}
    <div class="container">
        <h1>User successfully signed In!</h1>
        <a href="index.jsp" class="btn">Go to Home</a>
    </div>
</body>

</html>

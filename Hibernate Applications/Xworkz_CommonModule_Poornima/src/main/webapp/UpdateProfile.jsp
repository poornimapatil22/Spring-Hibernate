<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Xworkz-commonModule Application</title>

  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

  <style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        margin: 0;
        padding: 0;
        background-color: white;
    }

    .container {
        max-width: 700px;
        margin: 50px auto;
        padding: 30px;
        background-color: CornflowerBlue;
        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        border-radius: 10px;
        border: 1px solid #dee2e6;
    }

    h2 {
        text-align: center;
        color: #343a40;
        margin-bottom: 30px;
        font-size: 28px;
        font-weight: 600;
    }

    form {
        display: flex;
        flex-direction: column;
    }

    label {
        font-weight: 500;
        margin: 10px 0 5px;
        color: #495057;
    }

    input[type="text"],
    input[type="email"],
    input[type="tel"],
    select {
        padding: 12px 15px;
        margin-bottom: 20px;
        border: 1px solid #ced4da;
        border-radius: 5px;
        width:100%;
        margin:5px;
        font-size: 16px;
        transition: border-color 0.3s ease;
    }

    input[type="text"]:focus,
    input[type="email"]:focus,
    input[type="tel"]:focus,
    select:focus {
        border-color: #007bff;
        outline: none;
    }

    input[type="submit"] {
        background-color: #28a745;
        color: white;
        padding: 12px 20px;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    input[type="submit"]:hover {
        background-color: #218838;
    }

    .form-group {
        margin-bottom: 10px;
    }

    .error-msg {
        color: #dc3545;
        font-size: 14px;
        text-align: center;
        margin-bottom: 30px;
        font-weight: 600;
    }

    .button-container {
        text-align: center;
    }

    .location-dropdown {
        margin-top: 10px;
    }

    /* Media Queries for responsiveness */
    @media (max-width: 768px) {
        .container {
            padding: 15px;
        }

        h2 {
            font-size: 24px;
            margin:5px;
        }

        input[type="text"],
        input[type="email"],
        input[type="tel"],
        select {
            font-size: 14px;
        }

        input[type="submit"] {
            font-size: 14px;
        }
    }
  </style>
</head>
<body>

  <!-- Display error message (if any) -->
  <div class="error-msg">
    ${errorIs}
  </div>

  <div class="container">
    <h2>Update Profile</h2>

    <form action="updateProfile" method="POST">
      <!-- Name -->
      <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>
      </div>

      <!-- Email -->
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
      </div>

      <!-- Alternate Phone -->
      <div class="form-group">
        <label for="altPhone">Alternate Phone:</label>
        <input type="tel" id="altPhone" name="altPhone">
      </div>

      <!-- Phone -->
      <div class="form-group">
        <label for="phone">Phone:</label>
        <input type="tel" id="phone" name="phone" required>
      </div>

      <!-- Alternate Email -->
      <div class="form-group">
        <label for="altEmail">Alternate Email:</label>
        <input type="email" id="altEmail" name="altEmail">
      </div>

      <!-- Location Dropdown -->
      <div class="form-group">
        <label for="location">Location</label>
        <select id="location" name="location" class="form-control" required>
          <option value="">Select a location</option>
          <c:forEach items="${locationListSend}" var="location">
            <option value="${location.name()}">${location.getLocation()}</option>
          </c:forEach>
        </select>
      </div>

      <!-- Submit Button -->
      <div class="form-group">
        <div class="button-container">
          <input type="submit" class="btn btn-success" value="Submit">
        </div>
      </div>
    </form>
  </div>

</body>
</html>

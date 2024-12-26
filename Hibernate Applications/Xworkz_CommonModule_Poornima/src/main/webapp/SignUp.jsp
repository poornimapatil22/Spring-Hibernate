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
      color: green;
      text-align: center;
      margin-bottom: 10px;
    }
    .error-msg {
      color: red;
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
      font-size: 10px;
      color: white;
      text-decoration: none;
      padding: 1px;
    }
    .nav-link:hover {
      color: #0056b3;
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


<c:if test="${not empty error}">
        <div class="error-msg">
         <c:forEach items="${error}" var="err">
         <div>${err.defaultMessage}</div>
         </c:forEach>
        </div>
</c:if>
        
<div class="container">
  <h4>SignUp</h4>

  <form action="signUp" method="post">
      <div class="form-group">
        <label for="name" class="font-weight-bold">User Name</label>
        <input type="text" class="form-control" name="name" id="name" value="${user.name}"onchange="onNameChange()" value="${user.name}" required>
        <span id="displayName" style="color:red"></span>
      </div>

      <div class="form-group">
        <label for="phone" class="font-weight-bold">Phone Number</label>
        <input type="tel" class="form-control" name="phone" id="phone" value="${user.phone}" onchange="onPhoneChange()" value="${user.phone}" required>
        <span id="displayPhone" style="color:red"></span>
      </div>

      <div class="form-group">
        <label for="altPhone" class="font-weight-bold">Alternative Mobile Number</label>
        <input type="tel" class="form-control" name="altPhone" id="altPhone" value="${user.altPhone}" onchange="onAltPhoneChange()" value="${user.altPhone}" required>
         <span id="diaplayAltPhone" style="color:red"></span>
      </div>

      <div class="form-group">
        <label for="email" class="font-weight-bold">Email</label>
        <input type="text" class="form-control" name="email" id="email" value="${user.email}" onchange="onEmailChange()" value="${user.email}" required>
       <span id="displayEmail" style="color:red"></span>
      </div>

      <div class="form-group">
        <label for="altEmail" class="font-weight-bold">Alternative Email</label>
        <input type="text" class="form-control" name="altEmail" id="altEmail" onchange="onAltEmailChange()" value="${user.altEmail}" required>
        <span id="displayAltEmail" style="color:red"></span>
      </div>

      <div class="form-group">
        <label for="location" class="font-weight-bold">Location</label>
        <input type="text" class="form-control" name="location" id="location" onchange="onLocationChange()" value="${user.location}" required>
        <span id="displayLocation" style="color:red"></span>
      </div>

      <div class="form-group">
        <div class="button-container">
          <input type="submit" class="btn btn-success" value="Submit">
        </div>
      </div>
    </form>
     <script>
        function onNameChange() {
          var name = document.getElementById('name').value;
          var xhttp = new XMLHttpRequest();
          xhttp.open("GET", "http://localhost:9999/Xworkz_CommonModule_Poornima/name/" + name, true);
          xhttp.onload = function() {
            document.getElementById("displayName").innerHTML = this.responseText;
          };
          xhttp.send();
        }

        function onEmailChange() {
          var email1 = document.getElementById('email').value;
          var xhttp = new XMLHttpRequest();
          xhttp.open("GET", "http://localhost:9999/Xworkz_CommonModule_Poornima/email1/" + email1, true);
          xhttp.onload = function() {
            document.getElementById("displayEmail").innerHTML = this.responseText;
          };
          xhttp.send();
        }

        function onAltEmailChange() {
          var altEmail1 = document.getElementById('altEmail').value;
          var xhttp = new XMLHttpRequest();
          xhttp.open("GET", "http://localhost:9999/Xworkz_CommonModule_Poornima/altEmail1/" + altEmail1, true);
          xhttp.onload = function() {
            document.getElementById("displayAltEmail").innerHTML = this.responseText;
          };
          xhttp.send();
        }

        function onPhoneChange() {
          var phone1 = document.getElementById('phone').value;
          var xhttp = new XMLHttpRequest();
          xhttp.open("GET", "http://localhost:9999/Xworkz_CommonModule_Poornima/phone1/" + phone1, true);
          xhttp.onload = function() {
            document.getElementById("displayPhone").innerHTML = this.responseText;
          };
          xhttp.send();
        }

        function onAltPhoneChange() {
          var altPhone1 = document.getElementById('altPhone').value;
          var xhttp = new XMLHttpRequest();
          xhttp.open("GET", "http://localhost:9999/Xworkz_CommonModule_Poornima/altPhone1/" + altPhone1, true);
          xhttp.onload = function() {
            document.getElementById("diaplayAltPhone").innerHTML = this.responseText;
          };
          xhttp.send();
        }

        function onLocationChange() {
          var location = document.getElementById('location').value;
          var xhttp = new XMLHttpRequest();
          xhttp.open("GET", "http://localhost:9999/Xworkz_CommonModule_Poornima/location/" + location, true);
          xhttp.onload = function() {
            document.getElementById("displayLocation").innerHTML = this.responseText;
          };
          xhttp.send();
        }
      </script>
</div>

</body>
</html>

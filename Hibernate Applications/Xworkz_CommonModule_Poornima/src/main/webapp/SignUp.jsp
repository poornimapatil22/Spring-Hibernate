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

<div class="error-msg">
  ${lock}
  ${say}
</div>

<div class="container">
  <h4>SignUp</h4>

  <form action="signUp" method="post">
    <div class="form-group">
        <label for="name" class="font-weight-bold">User Name</label>
        <input type="text" class="form-control" name="name" id="name" value="${user.name}" onchange="onNameChange()" required>
        <span id="displayName" style="color:red"></span>
    </div>

    <div class="form-group">
        <label for="phone" class="font-weight-bold">Phone Number</label>
        <input type="tel" class="form-control" name="phone" id="phone" value="${user.phone}" onchange="onPhoneChange()" required>
        <span id="displayPhone" style="color:red"></span>
    </div>

    <div class="form-group">
        <label for="altPhone" class="font-weight-bold">Alternative Mobile Number</label>
        <input type="tel" class="form-control" name="altPhone" id="altPhone" value="${user.altPhone}" onchange="onAltPhoneChange()" required>
        <span id="displayAltPhone" style="color:red"></span>
    </div>

    <div class="form-group">
        <label for="email" class="font-weight-bold">Email</label>
        <input type="email" class="form-control" name="email" id="email" value="${user.email}" onchange="onEmailChange()" required>
        <span id="displayEmail" style="color:red"></span>
    </div>

    <div class="form-group">
        <label for="altEmail" class="font-weight-bold">Alternative Email</label>
        <input type="email" class="form-control" name="altEmail" id="altEmail" onchange="onAltEmailChange()" value="${user.altEmail}" required>
        <span id="displayAltEmail" style="color:red"></span>
    </div>


    <div class="form-group">
    <label for="location" class="font-weight-bold">Location</label>
    <select id="location" name="location" class="form-control">
                   <option value=""> select the option</option>
                   <c:forEach items="${locationListSend}" var="location">
                   <option value="${location}"> ${location}</option>
                   </c:forEach>
                   </select>
                   </div>

        <div class="form-group">
        <div class="button-container">
            <input type="submit" class="btn btn-success" value="Submit">
        </div>
    </div>
  </form>
</div>

<script>
  function onNameChange(){
               var name = document.getElementById('name');
               var nameValue = name.value;

               if (nameValue.trim().length < 3){
                   document.getElementById("displayName").innerHTML = "Name must be at least 3 characters long.";
                   return;
               }else {
                   document.getElementById("displayName").innerHTML = "";
               }

               var xhttp = new XMLHttpRequest();
               xhttp.open("GET","http://localhost:9999/Xworkz_CommonModule_Poornima/name/" + nameValue);
               xhttp.send();

               xhttp.onload = function(){
               document.getElementById("displayName").innerHTML = this.responseText;
               }
               }

   function onEmailChange() {
      var email = document.getElementById('email');
      var emailValue = email.value;
      var altEmail = document.getElementById('altEmail');
      var altEmailValue = altEmail.value;

      if (!emailValue.includes('@gmail.com') && !emailValue.includes('@yahoo.com') && !emailValue.includes('@outlook.com') &&
              !emailValue.includes('.edu') && !emailValue.includes('.org') && !emailValue.includes('.info') && !emailValue.includes('.net')) {
              document.getElementById("displayEmail").innerHTML = "Enter a valid email address.";
              return;
            } else {
              document.getElementById("displayEmail").innerHTML = "";
            }

      if (emailValue === altEmailValue) {
        document.getElementById("displayEmail").innerHTML = "Email and alternative email must be different.";
        return;
      }

      var xhttp = new XMLHttpRequest();
      xhttp.open("GET", "http://localhost:9999/Xworkz_CommonModule_Poornima/email/" + emailValue);
      xhttp.send();

      xhttp.onload = function () {
        document.getElementById("displayEmail").innerHTML = this.responseText;
      }
    }


  function onAltEmailChange() {
      var altEmail = document.getElementById('altEmail');
      var altEmailValue = altEmail.value;
      var email = document.getElementById('email');
      var emailValue = email.value;


      if (!altEmailValue.includes('@gmail.com') && !altEmailValue.includes('@yahoo.com') && !altEmailValue.includes('@outlook.com') &&
        !altEmailValue.includes('.edu') && !altEmailValue.includes('.org') && !altEmailValue.includes('.info') && !altEmailValue.includes('.net')) {
        document.getElementById("displayAltEmail").innerHTML = "Enter a valid alternate email address.";
        return;
      } else {
        document.getElementById("displayAltEmail").innerHTML = "";
      }


      if (altEmailValue === emailValue) {
              document.getElementById("displayAltEmail").innerHTML = "Email and alternative email must be different.";
              return;
            }

      var xhttp = new XMLHttpRequest();
      xhttp.open("GET", "http://localhost:9999/Xworkz_CommonModule_Poornima/altEmail/" + altEmailValue);
      xhttp.send();

      xhttp.onload = function () {
        document.getElementById("displayAltEmail").innerHTML = this.responseText;
      }
    }

  function onPhoneChange() {
      var phone = document.getElementById('phone');
      var phoneValue = phone.value;
      var altPhone = document.getElementById('altPhone');
      var altPhoneValue = altPhone.value;


      if (phoneValue.trim().length !== 10 || (!phoneValue.startsWith("6") && !phoneValue.startsWith("7")
        && !phoneValue.startsWith("8") && !phoneValue.startsWith("9"))) {
        document.getElementById("displayPhone").innerHTML = "Phone number must contain 10 digits and should be valid.";
        return;
      } else {
        document.getElementById("displayPhone").innerHTML = "";
      }

       if (phoneValue === altPhoneValue) {
              document.getElementById("displayPhone").innerHTML = "Phone number and alternative phone number must be different.";
              return;
            }

      var xhttp = new XMLHttpRequest();
      xhttp.open("GET", "http://localhost:9999/Xworkz_CommonModule_Poornima/phone/" + phoneValue);
      xhttp.send();

      xhttp.onload = function () {
        document.getElementById("displayPhone").innerHTML = this.responseText;
      }
    }




 function onAltPhoneChange() {
     var altPhone = document.getElementById('altPhone');
     var altPhoneValue = altPhone.value;
     var phone = document.getElementById('phone');
     var phoneValue = phone.value;

     if (altPhoneValue.trim().length !== 10 || (!altPhoneValue.startsWith("6") && !altPhoneValue.startsWith("7")
       && !altPhoneValue.startsWith("8") && !altPhoneValue.startsWith("9"))) {
       document.getElementById("displayAltPhone").innerHTML = "Alternative phone number must contain 10 digits and should be valid.";
       return;
     } else {
       document.getElementById("displayAltPhone").innerHTML = "";
     }

     if (altPhoneValue === phoneValue) {
            document.getElementById("displayAltPhone").innerHTML = "Phone number and alternative phone number must be different.";
            return;
          }

     var xhttp = new XMLHttpRequest();
     xhttp.open("GET", "http://localhost:9999/Xworkz_CommonModule_Poornima/altPhone/" + altPhoneValue);
     xhttp.send();

     xhttp.onload = function () {
       document.getElementById("displayAltPhone").innerHTML = this.responseText;
     }
   }


</script>

</body>
</html>

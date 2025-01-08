<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Update Profile</title>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background-color: white;
    }
    .container {
      max-width: 700px;
      margin: 50px auto;
      padding: 30px;
      background-color: CornflowerBlue;
      border-radius: 10px;
      box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
    }
    h2 {
      text-align: center;
      color: #343a40;
    }
    form {
      display: flex;
      flex-direction: column;
    }
    label {
      font-weight: 500;
      margin: 10px 0 5px;
    }
    input[type="text"], input[type="email"], input[type="tel"], select {
      padding: 12px 15px;
      margin-bottom: 20px;
      border: 1px solid #ced4da;
      border-radius: 5px;
      width: 100%;
    }
    input[type="submit"] {
      background-color: #28a745;
      color: white;
      padding: 12px 20px;
      border: none;
      border-radius: 5px;
      font-size: 16px;
      cursor: pointer;
    }
    input[type="submit"]:hover {
      background-color: #218838;
    }
    .form-group {
      margin-bottom: 10px;
    }

    .error-msg {
          color: white;
          text-align: center;
          margin-bottom: 10px;
          background: #DC143C;
        }
  </style>
</head>
<body>
<div class="error-msg">
 ${errorIsIn}
</div>

  <div class="container">
    <h2>Update Profile</h2>


   <form action="updateProfile" method="POST" enctype="multipart/form-data">


      <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${user.name}" onchange="onNameChange()" required>
      <span id="displayName" style="color:red"></span>
      </div>

      <div class="form-group">
              <label for="email">Email:</label>
              <input type="email" id="email" name="email" value="${user.email}" onchange="onEmailChange()" required>
              <span id="displayEmail" style="color:red"></span>
            </div>

            <div class="form-group">
                    <label for="altEmail">Alternate Email:</label>
                    <input type="email" id="altEmail" name="altEmail" value="${user.altEmail}" onchange="onAltEmailChange()">
                  <span id="displayAltEmail" style="color:red"></span>
                  </div>

      <div class="form-group">
        <label for="altPhone">Alternate Phone:</label>
        <input type="tel" id="altPhone" name="altPhone" value="${user.altPhone}" onchange="onAltPhoneChange()" >
      <span id="displayAltPhone" style="color:red"></span>
      </div>

      <div class="form-group">
        <label for="phone">Phone:</label>
        <input type="tel" id="phone" name="phone" value="${user.phone}" onchange="onPhoneChange()" required>
      <span id="displayPhone" style="color:red"></span>
      </div>

      <div class="form-group">
        <label for="location">Location:</label>
        <select id="location" name="location" required>
          <option value="">Select a location</option>
          <c:forEach items="${locationListSend}" var="location">
            <option value="${location}"  ${location == user.location ? 'selected' : ''}> ${location}</option>
          </c:forEach>
        </select>
      </div>


      <div class="form-group">
        <label for="pic">Upload Image:</label>
        <input name="pic" class="form-control" type="file" id="pic">
      </div>


      <input type="submit" value="Update Profile">
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

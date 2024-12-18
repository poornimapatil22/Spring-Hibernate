<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Xworkz-commounModule Application</title>

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
    .button-container {
      display: flex;
      justify-content: center;
    }
  </style>
</head>
<body>

  <div class="container">

    <div class="msg">${SuccessMsg}</div>

    <h4>Xworkz Login Form&#128516</h4>

    <form action="signUp" method="post">
      <div class="form-group">
        <label for="name" class="font-weight-bold">User Name</label>
        <input type="text" class="form-control" name="name">
      </div>

      <div class="form-group">
        <label for="phone" class="font-weight-bold">Phone Number</label>
        <input type="tel" class="form-control" name="phone">
      </div>

      <div class="form-group">
        <label for="altPhone" class="font-weight-bold">Alternative Mobile Number</label>
        <input type="tel" class="form-control" name="altPhone">
      </div>

      <div class="form-group">
        <label for="email" class="font-weight-bold">Email</label>
        <input type="email" class="form-control" name="email">
      </div>
      <div class="form-group">
        <label for="alterEmail" class="font-weight-bold"> Alternavtive Email</label>
        <input type="alterEmail" class="form-control" name="alterEmail">
      </div>

      <div class="form-group">
        <label for="location" class="font-weight-bold">Location</label>
        <input type="text" class="form-control" name="location">
      </div>
        <div class="form-group">
        <div class="button-container">
          <input type="submit" class="btn btn-success" value="Submit">
        </div>
      </div>
    </form>
  </div>
</body>
</html>

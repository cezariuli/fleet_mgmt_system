<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>


<html>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<head>
    <style>
        html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif;}
        .w3-sidebar {
          z-index: 3;
          width: 250px;
          top: 43px;
          bottom: 0;
          height: inherit;
        }
        .footer {
          position: fixed;
          left: 250px;
          bottom: 0;
          width: 100%;
        }
    </style>
    <title>Fleet Management System</title>
</head>

<body>

    <!-- Navbar -->
    <div class="w3-top">
      <div class="w3-bar w3-theme w3-top w3-left-align w3-large">
        <a class="w3-bar-item w3-button w3-right w3-hide-large w3-hover-white w3-large w3-theme-l1" href="javascript:void(0)" onclick="w3_open()"><i class="fa fa-bars"></i></a>
        <a href="index.jsp" class="w3-bar-item w3-button w3-hide-small w3-hover-white ">Home</a>
        <a href="fleet" class="w3-bar-item w3-button w3-theme-l1">Fleet</a>
        <a href="bookings" class="w3-bar-item w3-button w3-hide-small w3-hover-white">Bookings</a>
        <a href="static/contact.jsp" class="w3-bar-item w3-button w3-hide-small w3-hover-white">Contact</a>       
      </div>
    </div>

    <!-- Sidebar -->
    <nav class="w3-sidebar w3-bar-block w3-collapse w3-large w3-theme-l5 w3-animate-left" id="mySidebar">
      <a href="javascript:void(0)" onclick="w3_close()" class="w3-right w3-xlarge w3-padding-large w3-hover-black w3-hide-large" title="Close Menu">
      <i class="fa fa-remove"></i>
      </a>
      <a class="w3-bar-item w3-button w3-hover-black" href="fleet">List cars</a>
      <a class="w3-bar-item w3-button w3-hover-black" href="fleet?action=maintenance">Maintenance</a>
      <a class="w3-bar-item w3-button w3-hover-black" href="fleet?action=add">Add new car</a>

    </nav>

    <!-- Overlay effect when opening sidebar on small screens -->
    <div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

    <!-- Main content: shift it to the right by 250 pixels when the sidebar is visible -->
    <div class="w3-main" style="margin-left:250px">

      <div class="w3-row w3-padding-64">
        <div class="w3-twothird w3-container w3-display-container">
            <form method="post" target="_self">

                <div class="w3-third w3-container">
                    <label for="car_maker">Car Maker:* </label><br>
                    <select class="w3-margin-bottom" id="oems" name="car_maker" required>
                        <c:forEach var="oem" items="${requestScope.oems}">
                        <option>
                        <c:out value="${oem}"/>
                        </option>
                        </c:forEach>
                    </select><br>
                    <label for="car_model">Car Model: </label><br>
                    <input class="w3-margin-bottom" type="text" id="car_model" name="car_model" placeholder="i.e. Logan" required><br>
                    <label for="model_year">Model Year: </label><br>
                    <input class="w3-margin-bottom" type="number" id="model_year" name="model_year" min="2000" max="2021" placeholder="2019" required><br>
                    <label for="vin">VIN:</label><br>
                    <input class="w3-margin-bottom" type="text" id="vin" name="vin" placeholder="UU1BSDA1PJ0000001" required><br>
                    <label for="fuel_type">Fuel:</label><br>
                    <select class="w3-margin-bottom" id="fuel_type" name="fuel_type" required><br>
                        <option>Diesel</option>
                        <option>Diesel + Electric</option>
                        <option>Petrol</option>
                        <option>Petrol + Electric</option>
                        <option>Petrol + LPG</option>
                        <option>Petrol + LPG + Electric</option>
                        <option>Electric</option>
                    </select><br>
                    <label for="license_plate">License Plate:</label><br>
                    <input class="w3-margin-bottom" type="text" id="license_plate" name="license_plate" placeholder="CJ 01 FMS" required><br>
                </div>

                <div class="w3-third w3-container">
                    <label for="odometer">Odometer [km]: </label><br>
                    <input class="w3-margin-bottom" type="number" id="odometer" name="odometer" value="0" required><br>

                    <label for="transmission">Transmission: </label><br>
                    <select class="w3-margin-bottom" id="transmission" name="transmission">
                        <option>Manual</option>
                        <option>Automatic</option>
                    </select><br>

                    <label for="power">Power [kW]: </label><br>
                    <input class="w3-margin-bottom" type="number" name="power" name="power" value="0" required><br>

                    <label for="fuel_consumption">Fuel Consumption [l/100km]: </label><br>
                    <input class="w3-margin-bottom" type="number" id="fuel_consumption" name="fuel_consumption" value="0.0" required=""><br>

                    <label for="Body">Body: </label><br>
                    <select class="w3-margin-bottom" id="body" name="body" required="">
                        <option>Hatchback</option>
                        <option>Sedan</option>
                        <option>Break</option>
                        <option>SUV</option>
                        <option>Mini VAN</option>
                        <option>VAN</option>
                    </select><br>
                    
                    <label for="no_of_passengers">No. of passengers: </label><br>
                    <input type="number" name="no_of_passengers" id="no_of_passengers" value="0" required><br>
                </div>

                <div class="w3-third w3-container">
                    <label for="luggage">Luggage capacity: </label><br>
                    <input class="w3-margin-bottom" name="luggage" type="number" id="luggage" value="0" required><br>

                    <label for="no_of_doors">No of doors: </label><br>
                    <input class="w3-margin-bottom" name="no_of_doors" type="number" id="no_of_doors" value="0" required><br>

                    <label for="co2">CO2 emission [g/km]: </label><br>
                    <input class="w3-margin-bottom" name="co2" type="number" id="co2" value="0" required><br>

                    <label for="air_cond">A/C: </label><br>
                    <select class="w3-margin-bottom" id="air_cond" name="air_cond" required > 
                        <option>Automatic</option>
                        <option>Manual</option>
                        <option selected>No</option>
                    </select><br>

                    <label for="navigation">Navigation: </label><br>
                    <select class="w3-margin-bottom" id="navigation" name="navigation" required>
                        <option>Yes</option>
                        <option selected>No</option>
                    </select><br>
                </div> 
                <input  class="w3-display-bottomright" type="submit" name="submit" value="Submit">
            </form>  
        </div>
    </div>

    <!-- END MAIN -->
    </div>

    <!-- FOOTER -->
    <footer class="footer" id="myFooter">
        <div class="w3-container w3-theme-l2 w3-padding-32">
            <h4>Fleet Management System - ALPHA stage</h4>
        </div>

        <div class="w3-container w3-theme-l1">
          <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
        </div>
    </footer>
    <script>
    // Get the Sidebar
    var mySidebar = document.getElementById("mySidebar");
    // Get the DIV with overlay effect
    var overlayBg = document.getElementById("myOverlay");

    // Toggle between showing and hiding the sidebar, and add overlay effect
    function w3_open() {
    
      if (mySidebar.style.display === 'block') {
        mySidebar.style.display = 'none';
        overlayBg.style.display = "none";
      } else {
        mySidebar.style.display = 'block';
        overlayBg.style.display = "block";
      }
    }

    // Close the sidebar with the close button
  function w3_close() {
    mySidebar.style.display = "none";
    overlayBg.style.display = "none";
  }
  </script>
</body>
</html>
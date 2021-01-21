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
        <a href="index.jsp" class="w3-bar-item w3-button w3-hide-small w3-hover-white">Home</a>
        <a href="fleet" class="w3-bar-item w3-button w3-hide-small w3-hover-white">Fleet</a>
        <a href="bookings" class="w3-bar-item w3-button w3-theme-l1">Bookings</a>
        <a href="#" class="w3-bar-item w3-button w3-hide-small w3-hover-white">Contact</a>       
      </div>
    </div>

    <!-- Sidebar -->
    <nav class="w3-sidebar w3-bar-block w3-collapse w3-large w3-theme-l5 w3-animate-left" id="mySidebar">
      <a href="javascript:void(0)" onclick="w3_close()" class="w3-right w3-xlarge w3-padding-large w3-hover-black w3-hide-large" title="Close Menu">
      <i class="fa fa-remove"></i>
      </a>
      <!-- Classic method 
      <a class="w3-bar-item w3-button w3-hover-black" href="bookings?action=current">Current</a>
      <a class="w3-bar-item w3-button w3-hover-black" href="bookings?action=future">Future</a>
      <a class="w3-bar-item w3-button w3-hover-black" href="bookings?action=past">Past</a>
      <a class="w3-bar-item w3-button w3-hover-black" href="bookings?action=create">Create new</a> -->

      <!-- Experiment new method -->
      <a class="w3-bar-item w3-button w3-hover-black" onclick="updatePage('Current')">Current</a>
      <a class="w3-bar-item w3-button w3-hover-black" onclick="updatePage('Future')">Future</a>
      <a class="w3-bar-item w3-button w3-hover-black" onclick="updatePage('Past')">Past</a>
      <a class="w3-bar-item w3-button w3-hover-black" onclick="updatePage('Create')">Create new</a>

    </nav>

    <!-- Overlay effect when opening sidebar on small screens -->
    <div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

    <!-- Main content: shift it to the right by 250 pixels when the sidebar is visible -->
    <div class="w3-main" style="margin-left:250px">

        <div class="w3-row w3-padding-64">
          <div class="w3-twothird w3-container">

              <!-- Content on main Booking page -->
              <div id="MainBooking" class="w3-hide w3-show">
                <h1 class="w3-text-teal">Bookings</h1>
                <p>Bookings main page</p>
              </div>

              <!-- Content of the Current bookings page -->
              <div id="Current" class="w3-hide">
                <h1 class="w3-text-teal">Current bookings</h1>
                <table class="w3-table-all w3-hoverable">
                  <tr class="w3-light-blue">
                    <th>License plate</th>
                    <th>Constructor</th>
                    <th>Model</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Client</th>
                    <th>Status</th>
                  </tr>
                </table>
              </div>

              <!-- Content of the Future bookings page --> 
              <div id="Future" class="w3-hide">
                <h1 class="w3-text-teal">Future bookings</h1>
                <table class="w3-table-all w3-hoverable">
                  <tr class="w3-light-blue">
                    <th>License plate</th>
                    <th>Constructor</th>
                    <th>Model</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Client</th>
                    <th>Status</th>
                  </tr>
                </table>
              </div>

              <!-- Content of the Past bookings page --> 
              <div id="Past" class="w3-hide">
                <h1 class="w3-text-teal">Past bookings</h1>
                <table class="w3-table-all w3-hoverable">
                  <tr class="w3-light-blue">
                    <th>License plate</th>
                    <th>Constructor</th>
                    <th>Model</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Client</th>
                    <th>Status</th>
                  </tr>
                </table>
              </div>

              <!-- Content of the Create new booking page -->
              <div id="Create" class="w3-hide">
                <h1 class="w3-text-teal">Create a new bookings</h1>
              </div>
          
          </div>
        </div>
    <!-- END MAIN -->
    </div>
        
    <!-- Footer -->
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

  function updatePage(page) {
    
    var pages = ["Current", "Future", "Past", "Create", "MainBooking"];
  
    for ( i=0; i<pages.length; i++) {
      var x = document.getElementById(pages[i]);
      if ( page == pages[i]) {
        if (x.className.indexOf("w3-show") == -1) {
          x.className += " w3-show";
        }
      }
      else {
         x.className = x.className.replace(" w3-show", "");
      }
    }
  }

  function showFuture() {

  }

  function showPast() {

  }

  function showCreateNew() { 
  }
  </script>

</body>
</html>

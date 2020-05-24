<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
        crossorigin="anonymous">


    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<%--  <link href="${pageContext.request.contextPath}/resources/css/temp.css"
    rel="stylesheet">
     --%>
<link href="resources/css/temp.css" rel="stylesheet">

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Home</h1>

<div class="modal fade" id="modalContactForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header text-center">
        <h4 class="modal-title w-100 font-weight-bold">Write to us</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        	<span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="submitImage" method="post">
      	<div class="modal-body mx-3">
        	<div class="md-form mb-5">
        		<i class="fas fa-user prefix grey-text"></i>
		        <input type="file" id="uploadImage" class="form-control validate">
		        <BR><BR>
		        <input type="hidden" id="data-url" name="data-url">
		        <img id="previewImage" height="200" width="200">
			</div>
	    </div>
      	<div class="modal-footer d-flex justify-content-center">
        	<input type="submit" value="Send" id="save" class="btn btn-unique"> 
      	</div>
      </form>
    </div>
  </div>
</div>

<div class="text-center">
  <a href="" class="btn btn-default btn-rounded mb-4" data-toggle="modal" data-target="#modalContactForm">Launch
    Modal Contact Form</a>
</div>

<form id="form" action="submitImage" method="post">

	
</form>

<br>
<a href = "showImage">showImage</a>
<br>

<a href="${pageContext.request.contextPath}/home?userId=1&postId=0&prev=0&next=1">click to see home</a>
<BR>

<img src="${imgPath }">

</body>

 <script>
 
 
 	//var formid =  document.getElementById("form");
	var fileId = document.getElementById("uploadImage");
    //var savebtn = document.getElementById("save");
    var inputData = document.getElementById("data-url");
	
    var previewImage = document.getElementById("previewImage");
    
    fileId.addEventListener("change", function() {
      var file = this.files[0]; //incase of multiple files we can use this array as to select any one of them

      console.log(file);

      if (file) {
        var reader = new FileReader();

        reader.addEventListener("load", function() {
          //console.log(this);
          alert(this.result);
          //inputData.setAttribute("placeholder", this.result);
          inputData.setAttribute("value", this.result);
          previewImage.setAttribute("src", this.result);
          
        });

        reader.readAsDataURL(file);
       
      }
    });
    
    
    
  </script>

</html>
/**
 * 
 * this java script file is to manage the upload process at story and post
 */
/*
var camera_icon = document.getElementById("camera_icon")
var status_selector = document.getElementById("status_selector");
var data_url = document.getElementById("data_url");
var preview = document.getElementById("preview");

alert("hello");

camera_icon.addEventListener("click", function(){
	status_selector.click();
});

status_selector.addEventListener("change", function(){
	
	var file = this.files[0];
	console.log(file);
	alert(file.name);
	
	if(file) {
		
		alert(file.name);
		var reader = new FileReader();
		alert(reader.result);
		
		reader.readAsDataURL(file);
		
		alert(reader.result);
		
		reader.addEventListener("load", function(){
			alert(reader);
			alert(this.result);
			
			console.log(this.result);
			
			data_url.setAttribute("value", this.result);
			preview.style.backgroundImage = "url("+this.result+")";//"url('img_tree.png')";
			preview.style.height= "300px";
		});
		
	}
	
});*/
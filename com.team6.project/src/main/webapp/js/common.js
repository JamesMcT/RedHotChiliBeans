function loadbar(href) {
		var sidebar = document.getElementById("navigation");
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET", href, false);
		xmlhttp.send();
		sidebar.innerHTML = xmlhttp.responseText;
	}
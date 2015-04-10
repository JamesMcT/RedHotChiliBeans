function loadbar(href) {
		var sidebar = document.getElementById("navigation");
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET", href, false);
		xmlhttp.send();
		sidebar.innerHTML = xmlhttp.responseText;
	}

function showDiv(divId) {
	document.getElementById(divId).style.display = 'block';
}
function showDivInLine(divId) {
	document.getElementById(divId).style.display = 'inline-block';
}
function hideDiv(divId) {
	document.getElementById(divId).style.display = 'none';
}

function createTableHead(tableId, titles) {
	var table = document.getElementById(tableId);
	var thead = document.createElement("thead");
	thead.id = "tableHead";
	var tr = document.createElement("tr");
	for (var i = 0; i < titles.length; i++) {
		var th1 = document.createElement("th");
		th1.appendChild(document.createTextNode(titles[i]));
		tr.appendChild(th1);
	}
	thead.appendChild(tr);
	table.appendChild(thead);
}

function cleanTable() {
	var tableBody = document.getElementById("tableBody");
	var tableHead = document.getElementById("tableHead");
	if (tableHead) {
		console.log("removing head");
		tableHead.parentNode.removeChild(tableHead);
	}
	if (tableBody) {
		console.log("removing body");
		tableBody.parentNode.removeChild(tableBody);
	}
}
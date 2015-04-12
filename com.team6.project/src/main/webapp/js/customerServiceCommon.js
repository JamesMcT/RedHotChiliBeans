function createTableCauseCodeEventIdOccurrenceBody(tableId, response) {
	var table = document.getElementById(tableId);
	var tbody = document.createElement("tbody");
	tbody.id = "tableBody";
	for (var i = 0; i < response.length; i++) {
		var singleResponse = response[i];
		var eventCause = singleResponse[0];
		var occurence = singleResponse[1];
		var tr = document.createElement("tr");
		if (i % 2) {
			tr.className = "even gradeA";
		} else {
			tr.className = "odd gradeA";
		}
		var td1 = document.createElement("td");
		td1.appendChild(document.createTextNode(eventCause.causeCode));
		var td2 = document.createElement("td");
		td2.appendChild(document.createTextNode(eventCause.eventId));
		var td3 = document.createElement("td");
		td3.appendChild(document.createTextNode(eventCause.description));
		var td4 = document.createElement("td");
		td4.appendChild(document.createTextNode(occurence));
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		tbody.appendChild(tr);
	}
	table.appendChild(tbody);
}

function createTableCauseCodeEventIdBody(tableId, response) {
	var table = document.getElementById(tableId);
	var tbody = document.createElement("tbody");
	tbody.id = "tableBody";
	for (var i = 0; i < response.length; i++) {
		var eventCause = response[i];
		var tr = document.createElement("tr");
		if (i % 2) {
			tr.className = "even gradeA";
		} else {
			tr.className = "odd gradeA";
		}
		var td1 = document.createElement("td");
		td1.appendChild(document.createTextNode(eventCause.causeCode));
		var td2 = document.createElement("td");
		td2.appendChild(document.createTextNode(eventCause.eventId));
		var td3 = document.createElement("td");
		td3.appendChild(document.createTextNode(eventCause.description));
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tbody.appendChild(tr);
	}
	table.appendChild(tbody);
}


function validateImsi(imsi) {
	cleanError();
	if (isNotEmpty(imsi) == false) {
		showError("Please insert an imsi value");
		return false;
	} else if (isNaN(imsi) == true) {
		showError("IMSI Field should contain numbers only");
		return false;
	} else {
		return true;
	}
}

function isNotEmpty(imsi) {
	if (imsi == "") {
		return false;
	}
	return true;
}
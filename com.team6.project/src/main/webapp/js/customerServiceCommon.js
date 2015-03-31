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


function validateImsi(imsi) {

	if (isNotEmpty(imsi, "Please Enter an Imsi") == false) {
		return false;
	} else if (isNaN(imsi) == true) {
		alert("IMSI Field should contain numbers only");
		return false;
	} else {
		// alert("True Entered on IMSI...")
		return true;
	}
}

function isNotEmpty(imsi, alertMessage) {
	if (imsi == "") {
		alert(alertMessage);
		return false;
	}
	return true;
}
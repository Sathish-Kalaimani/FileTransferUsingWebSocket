<html>
<body>
<h2>File Transfer Using WebSockets</h2>
<h3 id="connect"></h3>
<form enctype="multipart/form-data" method="post" name="filedata">
<input type="file" id="selectFile" name="selectFile">
<input type="button" value="Upload" onclick ="sendFile()">
</form>
<textarea id="fileContent" cols="50" rows="10"></textarea>

<script type="text/javascript">

var websocket = new WebSocket("ws://localhost:8080/FileUploadWebSocket/fileupload");

websocket.onopen = function(msg){processOpen(msg);}
websocket.onclose = function(msg){processClose(msg);}
websocket.onmessage = function(msg){processMessage(msg);}

function processOpen(msg){
	document.getElementById("connect").innerHTML = "Connected to Server";
}

function processClose(msg){
	websocket.send("client disconnected");
	connect.value +="Disconnected from Server \n";
}

function processMessage(msg){
	fileContent.value = "Response From Server: \n"
	fileContent.value+="\n"+msg.data;
}

function sendFile(){
	var file = document.getElementById("selectFile").files[0];
	var reader = new FileReader();
	var rawData = new ArrayBuffer();
	reader.readAsArrayBuffer(file);
	reader.onload = function(e){
		rawData = e.target.result;
		websocket.send(rawData);
	}
	
	
	document.getElementById("connect").innerHTML = "File Sent";
	
	
}

</script>

</body>
</html>

var uri = "ws://" + document.location.host + document.location.pathname + "/chat";
var socket;
var user;

function $(id) {
    return document.getElementById(id);
}

function showMessage(text) {
    var element = $("message");
    element.innerHTML = element.innerHTML + text + "<br/>";
}

function sendMessage() {
    if ($("userInput").value.trim().length == 0)
        return;

    var content = $("userInput").value;	
    var json = JSON.stringify({
        "action": "SAY",
        "user": user,
        "text": content
    });
    console.log("Send " + json);
    socket.send(json);
    $("userInput").value = "";
}

window.onload = function () {
    if (! "WebSocket" in window) {
        showMessage("The browser does not support WebSockets.");
        return;
    }

    socket = new WebSocket(uri);

    socket.onopen = function () {
        console.log("Session started.");
    };

    socket.onclose = function () {
        console.log("Session closed.");
    };

    socket.onerror = function (error) {
        showMessage(error);
    };

    socket.onmessage = function (event) {
        console.log("Received " + event.data);
        var message = JSON.parse(event.data);
  
        switch (message.action) { 
        case "JOIN":
            user = message.user;
            $("username").innerHTML = "Signed in: " + user;
            break;	
        case "ACTIVE_USERS_CHANGED":
            $("participants").innerHTML = message.text;
            break;
        default: 
          showMessage(message.text);
              break;
        }
    };
}

window.onunload = function () {
    socket.close();
}

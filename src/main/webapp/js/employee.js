
function addToTable(n, data){
    var new_tbody = document.createElement('tbody');

    for(var i = 0; i < data.length; i++){
        var tr = document.createElement("TR");
        var td1 = document.createElement("TD");
        var td2 = document.createElement("TD");
         var td3 = document.createElement("TD");
         var td4 = document.createElement("TD");

         td1.appendChild(document.createTextNode(data[i].requestID));
         td2.appendChild(document.createTextNode(data[i].purpose));
         td3.appendChild(document.createTextNode(data[i].amount));
         td4.appendChild(document.createTextNode(data[i].isApproved));
         tr.appendChild(td1);
         tr.appendChild(td2);
         tr.appendChild(td3);
         tr.appendChild(td4);
        new_tbody.appendChild(tr);
    }

    n.parentNode.replaceChild(new_tbody, n);

}

var tBody = document.getElementById("requests");

 var tr = document.createElement("TR");
 var td1 = document.createElement("TD");
 var td2 = document.createElement("TD");
 var td3 = document.createElement("TD");
 td1.appendChild(document.createTextNode("4"));
 td2.appendChild(document.createTextNode("Bryan"));
 td3.appendChild(document.createTextNode("Villegas"));

 tr.appendChild(td1);
 tr.appendChild(td2);
 tr.appendChild(td3);
 tBody.appendChild(tr);


var req = new XMLHttpRequest();

req.overrideMimeType("application/json");
req.open("GET", "/MasterServlet/login", true);
req.onload = function() {
    var json = JSON.parse(this.responseText);
    document.getElementById("username").innerHTML = "Username: " + json.username;
    document.getElementById("firstname").innerHTML = "Firstname: " + json.firstName;
    document.getElementById("lastname").innerHTML = "Lastname: " + json.lastName;
    console.log("From on load" + this.responseText);
}
req.send();

console.log("From js outside show");

function getRequests(){
var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var json = JSON.parse(this.responseText);
            console.log(json);
            addToTable(tBody, json);
        }
    };
    xhttp.overrideMimeType("application/json");
    xhttp.open("get", "/MasterServlet/home");
    xhttp.send();
}

function insertRequests(){

}
function submitRequests(){

    var reason = document.getElementById("input_purpose").value;
    var amount = document.getElementById("input_amount").value;


    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log("sent the info" );
        }
    };
    xhttp.open("POST", "/MasterServlet/home");
    xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhttp.send("purpose="+reason+"&amount="+amount);

}

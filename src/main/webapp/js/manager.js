/**
 * Created by bryanvillegas on 4/25/18.
 */
var reqs = [{"id":"1", "purpose":"i want money", "amount":"500"},
    {"id":"2", "purpose":"pay me back", "amount":"100"}];
function addToTable(n, data){

    data.forEach((d,i) => {
        var tr = document.createElement("TR");
    Object.keys(d).forEach((k,j) => {
        var td1 = document.createElement("TD");
    td1.appendChild(document.createTextNode(d[k]));
    tr.appendChild(td1);
});
    n.appendChild(tr);
});

}

var tBody = document.getElementById("requests");

addToTable(tBody, reqs);
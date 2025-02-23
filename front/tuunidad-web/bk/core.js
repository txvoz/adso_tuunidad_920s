var departamentos = [
    {id:1, name: "Cauca"},
    {id:2, name: "Valle del Cauca"},
    {id:3, name: "Nariño"},
    {id:4, name: "Putumayo"},
];

var municipios = [
    {id:1, name: "Popayan", id_dep: 1},
    {id:2, name: "Timbio", id_dep: 1},
    {id:3, name: "Cali", id_dep: 2},
    {id:4, name: "Tulua", id_dep: 2},
    {id:2, name: "Palmira", id_dep: 2},
    {id:6, name: "Pasto", id_dep: 3},
    {id:7, name: "Aldana", id_dep: 3},
    {id:8, name: "Ipiales", id_dep: 3},
    {id:9, name: "Mocoa", id_dep: 4},
];

function getUsuarios(){
    var localJsonData = localStorage.getItem("usuarios");
    if(localJsonData) {
        var usuarios = JSON.parse(localJsonData);
        return usuarios;
    } else {
        return [];
    }
}

function saveUsuarios(usuarios) {
    var localJsonData = JSON.stringify(usuarios);
    localStorage.setItem("usuarios", localJsonData);
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
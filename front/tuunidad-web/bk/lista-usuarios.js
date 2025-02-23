$(function(){
    loadUsuarios();
});

function getNombreDepartamento(idDepartamento) {
    for(var i = 0; i < departamentos.length; i++ ){
        var dep = departamentos[i];
        if(parseInt(dep.id) === parseInt(idDepartamento)) {
            return dep.name;
        }
    }

    return "";
}

function getNombreCiudad(idMunicipio) {
    for(var i = 0; i < municipios.length; i++ ){
        var mun = municipios[i];
        if(parseInt(mun.id) === parseInt(idMunicipio)) {
            return mun.name;
        }
    }

    return "";
}

function getEdad(fechaNacimiento) {
    var fecha = new Date(fechaNacimiento);
    var currentDate = new Date();

    var diff = currentDate - fecha;

    console.log(diff);

    var edad = diff / 31557600000;
    edad = Math.floor(edad);

    console.log("edad", edad);
    return edad;
}

function loadUsuarios(){
    
    var usuarios = getUsuarios();
    var html = "";

    for(var i = 0; i < usuarios.length; i++){
        var usuario = usuarios[i];
        html += "<tr>";
        html += "<th scope='col'>" +(i+1)+ "</th>";
        html += "<td>" + usuario.nombre + "</td>";
        html += "<td>" + usuario.apellido + "</td>";
        html += "<td>" + usuario.telefono + "</td>";
        html += "<td>" + usuario.identificacion + "</td>";
        html += "<td>" + getEdad(usuario.fechaNacimiento); + "</td>";
        html += "<td>" + usuario.cantidadVehiculos + "</td>";
        html += "<td>" + getNombreDepartamento(usuario.departamento) + "</td>";
        html += "<td>" + getNombreCiudad(usuario.ciudad) + "</td>";
        html += "<td>" + usuario.direccion + "</td>";
        html += "<td> <div class='color-favorito' style='background-color:"+usuario.color+"'></div> </td>";
        html += "<td>" + usuario.genero + "</td>";
        html += "<td>" + (usuario.esPropietario ? 'Si' : 'No') + "</td>";
        html += "<td><div class='btns editar' data-id='" + i + "' ></div> <div class='btns eliminar' data-id='" + i + "'></div></td>";
        html += "</tr>";
    }

    $("#table-usuarios tbody").html(html);

    $(".btns.eliminar").on('click', function(){
        if(confirm("Desea eliminar el registro?")) {
            var index = $(this).data('id');
            eliminarUsuario(index);
        }
    });

    $(".btns.editar").on('click', function(){
        if(confirm("Desea editar el registro?")) {
            var index = $(this).data('id');
            editarUsuario(index);
        }
    });

}

function editarUsuario(idUsuario) {
    var usuarios = getUsuarios();
    if(idUsuario < 0 || idUsuario >= usuarios.length) {
        alert("No se puede eliminar registro en la posicion " + idUsuario);
        return; 
    }

    window.location.href = "index.html?id_usuario="+idUsuario;
}

function eliminarUsuario(idUsuario) {
    var usuarios = getUsuarios();
    if(idUsuario < 0 || idUsuario >= usuarios.length) {
        alert("No se puede eliminar registro en la posicion " + idUsuario);
        return; 
    }

    var usuariosNuevos = [];
    for(var i = 0; i < usuarios.length; i++) {
        if(idUsuario !== i) {
            usuariosNuevos.push(usuarios[i]);
        }
    }
    alert("Usuario eliminado con exito!");
    saveUsuarios(usuariosNuevos);
    loadUsuarios();
}


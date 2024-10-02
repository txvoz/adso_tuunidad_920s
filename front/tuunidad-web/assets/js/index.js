var current_user = null;

$(function(){

    cargarDepartamentos();
    validateParamUser();

    $("#frm1").submit(function(e){

        $("#frm1 input, #frm1 select").each(function(index){
            if($(this).val()==='') {
                $(this).addClass("error");
            }
        });

        var cantidadErrores = $("#frm1 .error").length;
        if(cantidadErrores > 0) {
            alert("Valide los datos ingresados!");
            return false;
        }

        var usuario = {
            "nombre": $("#txtNombre").val(),
            "apellido": $("#txtApellido").val(),
            "correo": $("#txtCorreo").val(),
            "telefono": $("#txtTelefono").val(),
            "tipoIdentificacion": $("#slcTipoIdentificacion").val(),
            "identificacion": $("#txtIdentificacion").val(),
            "fechaNacimiento": $("#dateFechaNacimiento").val(),
            "cantidadVehiculos": $("#rangeCantidadVehiculos").val(),
            "departamento": $("#slcDepartamento").val(),
            "ciudad": $("#slcCiudad").val(),
            "direccion": $("#txtDireccion").val(),
            "color": $("#colorFavorito").val(),
            "genero": $("input[name=rdGenero]:checked").val(),
            "esPropietario": $("#ckbEsPropietario").is(':checked')
        };

        var userId = $("#usuario-id").val();

        var usuarios = [];
        var localJsonData = localStorage.getItem("usuarios");
        if(localJsonData) {
            usuarios = JSON.parse(localJsonData);
        } 

        if(userId === "") {
            usuarios.push(usuario);
        } else {
            userId = parseInt(userId);
            usuarios[userId] = usuario;
        }

        localJsonData = JSON.stringify(usuarios);
        localStorage.setItem("usuarios", localJsonData);

        alert("El registro se ha agregado exitosamente!");

        $("#frm1 button[type=reset]").click();

        return false;
    });

    $("#frm1 input").on("keyup", function(i) {
        $(this).removeClass("error");
    });

    $("#frm1 select, #frm1 input[type='date']").on("change", function(i) {
        $(this).removeClass("error");
    });

    $("#slcDepartamento").on("change", function(){
        if($(this).val() === "") {
            $("#slcCiudad").html("");
        } else {
            cargarCiudades($(this).val());
        }
    });

});

function validateParamUser() {
    var paramId = getParameterByName('id_usuario');
    if(paramId === "") {
        return;
    }

    paramId = parseInt(paramId);
    
    var usuarios = getUsuarios();
    if(paramId < 0 || paramId >= usuarios.length) {
        alert("El parametro ingresado es incorrecto");
        window.location.href = "lista-usuarios.html";
        return;
    }

    $("#usuario-id").val(paramId);

    var currentUser = usuarios[paramId];
    $("#txtNombre").val(currentUser.nombre);
    $("#txtApellido").val(currentUser.apellido);
    $("#txtCorreo").val(currentUser.correo);
    $("#txtTelefono").val(currentUser.telefono);
    $("#slcTipoIdentificacion").val(currentUser.tipoIdentificacion);
    $("#txtIdentificacion").val(currentUser.identificacion);
    $("#dateFechaNacimiento").val(currentUser.fechaNacimiento);
    $("#rangeCantidadVehiculos").val(currentUser.cantidadVehiculos);
    $("#txtDireccion").val(currentUser.direccion);
    $("#colorFavorito").val(currentUser.color);

    if(currentUser.genero === 'F') {
        $('#rdGenero1').prop('checked',true);
    } else if (currentUser.genero === 'M') {
        $('#rdGenero2').prop('checked',true);
    } else {
        $('#rdGenero3').prop('checked',true);
    }

    if(currentUser.esPropietario) {
        $('#ckbEsPropietario').prop('checked',true);
    }


    window.setTimeout(function(){
        $("#slcDepartamento").val(currentUser.departamento);
        $("#slcDepartamento").change();

        window.setTimeout(function(){
            $("#slcCiudad").val(currentUser.ciudad);
        }, 500);

    }, 500)
    
}

function cargarCiudades(idDepartamento){
    var html = "<option value=''> Seleccione ciudad </option>";

    for(var i = 0; i < municipios.length; i++) {
        var mun = municipios[i];
        if(parseInt(idDepartamento) === parseInt(mun.id_dep)) {
            html += "<option value='" + mun.id + "'>" + mun.name + "</option>";
        }
    }

    $("#slcCiudad").html(html);
}

function cargarDepartamentos(){
    var html = "<option value=''> Seleccione departamento </option>";

    for(var i = 0; i < departamentos.length; i++) {
        var dep = departamentos[i];
        html += "<option value='" + dep.id + "'>" + dep.name + "</option>";
    }

    $("#slcDepartamento").html(html);
} 

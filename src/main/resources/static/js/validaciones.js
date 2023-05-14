
$(document).ready(function () {

    //campos a validar
    const campos = { //si cambia a true es porque está correcto
        nick: false,
        email: false,
        pass1: false,
        nombreActividad: false,
        plazas: false,
        fechaRealizacion: false,
        fechaPeso: false,
        fechaPerimetro: false,
        datoPeso: false,
        datoPerimetro: false,
    }

    if ($("#form-editarActividad").length > 0) {
		for (var campo in campos) {
			campos[campo] = true;
		}
	}

    //expresiones regulares
    const expresiones = {
        nickname: /^.{3,20}/, //de 3 a 20 caracteres
        email: /^\w+([.-_+]?\w+)*@\w+([.-]?\w+)*(\.\w{2,10})+$/,
        password: /^.{8,}$/, //minimo 8 caracteres -> consultar como de segura la quieren
        nombreActividad: /^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\s]{0,25}$/, //de 0 a 25 caracteres, alfanumerico
    }

    //validar campo
    const validarCampo = (expresion, input, campo) => {
        if (expresion.test(input.value)) {
            $(`#error_${campo}`).hide();
            campos[campo] = true;
        } else {
            $(`#error_${campo}`).show();
            campos[campo] = false;
        }
    }

    //validación de formulario
    const validarFormulario = (e) => {

        switch (e.target.name) {
            case "nickname":
                validarCampo(expresiones.nickname, e.target, 'nick');
                break;
            case "email":
                validarCampo(expresiones.email, e.target, 'email');
                break;
            case "contrasenia":
                validarCampo(expresiones.password, e.target, 'pass1');
                //validarPassword();
                break;
            case "contrasenia2":
                validarPassword();
                break;
            case "nombre":
                validarCampo(expresiones.nombreActividad, e.target, 'nombreActividad');
                break;
            case "plazas":
                validarPlazas();
                break;
            case "fechaRealizacion":
                validarFechaRealizacion();
                break;
            case "fecha":
                validarFechaRegistro();
                break;
            case "dato":
                validarDatoProgreso();
                break;
        }
    }

    const validarPassword = () => { //validar si las contraseñas coinciden
        if ($('#pass1').val() !== $('#pass2').val()) {
            $('#error_pass2').show();
            campos['pass1'] = false;

        } else {
            $('#error_pass2').hide();
            campos['pass1'] = true;
        }
    }

    const validarPlazas = () => { //validar si plazas está entre 1 y 150 y si está vacío

        if ($('#plazas').val() === '') {
            $('#error_plazas').hide();
            campos['plazas'] = true;
        }

        else {
            if ($('#plazas').val() <= 0 || $('#plazas').val() > 150) {
                $('#error_plazas').show();
                campos['plazas'] = false;

            } else {
                $('#error_plazas').hide();
                campos['plazas'] = true;
            }
        }
    }

    const validarFechaRealizacion = () => { //validar si la fecha no ha pasado
        var fechaIntroducida = new Date($('#fechaRealizacion').val());
        var fechaActual = Date.now();
        if (fechaIntroducida != null) {
            if (fechaIntroducida < fechaActual) {
                $('#error_fechaRealizacion').show();
                campos['fechaRealizacion'] = false;

            } else {
                $('#error_fechaRealizacion').hide();
                campos['fechaRealizacion'] = true;
            }
        }
    }


    const validarFechaRegistro = () => {
        var fechaPeso = new Date($('#fechaPeso').val());
        var fechaPerimetro = new Date($('#fechaPerimetro').val());
        var fechaActual = Date.now();

        if (fechaPeso != "") {
            if (fechaPeso > fechaActual) {
                $('#error_fechaPeso').show();
                campos['fechaPeso'] = false;

            } else {
                $('#error_fechaPeso').hide();
                campos['fechaPeso'] = true;
            }
        }

        if (fechaPerimetro != "") {
            if (fechaPerimetro > fechaActual) {
                $('#error_fechaPerimetro').show();
                campos['fechaPerimetro'] = false;

            } else {
                $('#error_fechaPerimetro').hide();
                campos['fechaPerimetro'] = true;
            }
        }
    }

    const validarDatoProgreso = () => {
        var datoPeso = $('#datoPeso').val();
        var datoPerimetro = $('#datoPerimetro').val();

        if (datoPeso != "") {
            if (datoPeso <= 0) {
                $('#error_datoPeso').show();
                campos['datoPeso'] = false;

            } else {
                $('#error_datoPeso').hide();
                campos['datoPeso'] = true;
            }
        }

        if (datoPerimetro != "") {
            if (datoPerimetro <= 0) {
                $('#error_datoPerimetro').show();
                campos['datoPerimetro'] = false;

            } else {
                $('#error_datoPerimetro').hide();
                campos['datoPerimetro'] = true;
            }
        }
    }

    //ocultar errores REGISTRO
    $('#error_nick').hide();
    $('#error_email').hide();
    $('#error_pass1').hide();
    $('#error_pass2').hide();

    //ocultar errores NUEVA ACTIVIDAD
    $('#error_fechaRealizacion').hide();
    $('#error_nombreActividad').hide();
    $('#error_plazas').hide();

    //ocultar errores PROGRESO
    $('#error_fechaPeso').hide();
    $('#error_fechaPerimetro').hide();
    $('#error_datoPeso').hide();
    $('#error_datoPerimetro').hide();

    //Validar formulario REGISTRO y formulario Nuevo coordinador
    $("#form-registro #nickname").change(validarFormulario);
    $("#form-registro #email").change(validarFormulario);
    $("#form-registro #pass1").keyup(validarFormulario);
    $("#form-registro #pass2").change(validarFormulario);

    //Validar formulario NUEVA ACTIVIDAD
    $("#form-actividad #fechaRealizacion").change(validarFormulario);
    $("#form-actividad #nombreActividad").keyup(validarFormulario);
    $("#form-actividad #plazas").keyup(validarFormulario);

    //Validar formulario EDITAR ACTIVIDAD
    $("#form-editarActividad #fechaRealizacion").change(validarFormulario);
    $("#form-editarActividad #nombreActividad").on('keyup input', validarFormulario);
    $("#form-editarActividad #plazas").on('keyup input', validarFormulario);
    
    //validar formularios PROGRESO
    $("#form-registroPeso #fechaPeso").change(validarFormulario);
    $("#form-registroPerimetro #fechaPerimetro").change(validarFormulario);
    $("#form-registroPeso #datoPeso").keyup(validarFormulario);
    $("#form-registroPerimetro #datoPerimetro").keyup(validarFormulario);

    $("#form-registro").submit(function (event) {
        //faltaría validar que ese correo no exista

        event.preventDefault();

        if (campos.nick && campos.email && campos.pass1) {  //si está todo bien
            event.currentTarget.submit();
        }

    });

    $("#form-actividad").submit(function (event) {
        event.preventDefault();
        if (campos.nombreActividad && campos.plazas && campos.fechaRealizacion) {  //si está todo bien
            event.currentTarget.submit();
        }

    });

    $("#form-editarActividad").submit(function (event) {
        event.preventDefault();
        if (campos.nombreActividad && campos.plazas && campos.fechaRealizacion) {  //si está todo bien
            event.currentTarget.submit();
        }

    });

    $("#form-registroPeso").submit(function (event) {
        event.preventDefault();

        if (campos.fechaPeso && campos.datoPeso) {  //si está todo bien
            event.currentTarget.submit();
        }
    });

    $("#form-registroPerimetro").submit(function (event) {
        event.preventDefault();

        if (campos.fechaPerimetro && campos.datoPerimetro) {  //si está todo bien
            event.currentTarget.submit();
        }
    });





    /* MOSTRAR Y OCULTAR CONTRASEÑAS */

    const iconoOjo1 = $("#iconoOjo1");
    const iconoOjo2 = $("#iconoOjo2");


    $("#ojo").click(function () {
        if (this.previousElementSibling.type === 'password') {
            this.previousElementSibling.type = "text";
            iconoOjo1.removeClass('fa-eye');
            iconoOjo1.addClass('fa-eye-slash');
        }

        else if (this.previousElementSibling.type === 'text') {
            this.previousElementSibling.type = "password";
            iconoOjo1.removeClass('fa-eye-slash');
            iconoOjo1.addClass('fa-eye');
        }
    });

    $("#ojo2").click(function () {
        if (this.previousElementSibling.type === 'password') {
            this.previousElementSibling.type = "text";
            iconoOjo2.removeClass('fa-eye');
            iconoOjo2.addClass('fa-eye-slash');
        }

        else if (this.previousElementSibling.type === 'text') {
            this.previousElementSibling.type = "password";
            iconoOjo2.removeClass('fa-eye-slash');
            iconoOjo2.addClass('fa-eye');
        }
    });


});
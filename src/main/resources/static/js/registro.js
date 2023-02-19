
$(document).ready(function () {

    //campos a validar
    const campos = { //si cambia a true es porque está correcto
        nick: false,
        email: false,
        pass1: false,
    }

    //expresiones regulares
    const expresiones = {
        nickname: /^.{3,20}/, //de 3 a 20 caracteres
        email: /^\w+([.-_+]?\w+)*@\w+([.-]?\w+)*(\.\w{2,10})+$/,
        password: /^.{8,}$/, //minimo 8 caracteres -> consultar como de segura la quieren
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

    //ocultar errores REGISTRO
    $('#error_nick').hide();
    $('#error_email').hide();
    $('#error_pass1').hide();
    $('#error_pass2').hide();

    //Validar formulario REGISTRO
    $("#form-registro #nickname").change(validarFormulario);
    $("#form-registro #email").change(validarFormulario);
    $("#form-registro #pass1").keyup(validarFormulario);
    $("#form-registro #pass2").change(validarFormulario);


    $("#form-registro").submit(function (event) {

        event.preventDefault();
        event.currentTarget.submit();

        if (campos.nick && campos.email && campos.pass1) {  //si está todo bien
            event.currentTarget.submit();
        }

    });


    /* MOSTRAR Y OCULTAR CONTRASEÑAS */
    $("#ojo").click(function () {
        if (this.previousElementSibling.type === 'password') {
            this.previousElementSibling.type = "text";
        }

        else if (this.previousElementSibling.type === 'text') {
            this.previousElementSibling.type = "password";
        }
    });

    $("#ojo2").click(function () {
        if (this.previousElementSibling.type === 'password') {
            this.previousElementSibling.type = "text";
        }

        else if (this.previousElementSibling.type === 'text') {
            this.previousElementSibling.type = "password";
        }
    });


});
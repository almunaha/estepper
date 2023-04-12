function inscripcion(id) {
    Swal.fire({
        position: 'center',
        title: '<h4>¿Estás seguro de realizar la inscripción?</h4>',
        text: 'Una vez acepte, se añadirá a tu listado de actividades',
        showConfirmButton: true,
        showCancelButton: true,
        cancelButtonText: '<a href="/actividad/' + id + '" id="enlace">Más información</a>',
        confirmButtonText: '<a href="/confirmar/' + id + '" id ="conf">Confirmar</a>',
        confirmButtonColor: "#6ca4c7",

        didRender: function () {
            const cancel = document.querySelector('#enlace');
            const confirm = document.querySelector('#conf');

            if (cancel && confirm) {
                cancel.style.color = 'white';
                confirm.style.color = 'white';
            }
        },
        showClass: {
            popup: 'animate__animated animate__fadeInDown'
        },
        hideClass: {
            popup: 'animate__animated animate__fadeOutUp'
        }

    })
}

$(document).ready(function () {

    //Botones actividad
    $(".descripcion").show();
    $(".detalles").hide();
    $(".inscripcion").hide();
    $(".asistentes").hide();

    $("#descripcion").css("background-color", "rgb(98, 207, 234)");

    $("#descripcion").click(function () {
        $(this).css("background-color", "rgb(98, 207, 234)");
        $("#detalles").css("background-color", "rgb(175, 198, 204)");
        $("#inscripcion").css("background-color", "rgb(175, 198, 204)");
        $("#asistentes").css("background-color", "rgb(175, 198, 204)");

        $(".descripcion").show();
        $(".detalles").hide();
        $(".inscripcion").hide();
        $(".asistentes").hide();

    });

    $("#detalles").click(function () {
        $(this).css("background-color", "rgb(98, 207, 234)");
        $("#inscripcion").css("background-color", "rgb(175, 198, 204)");
        $("#descripcion").css("background-color", "rgb(175, 198, 204)");
        $("#asistentes").css("background-color", "rgb(175, 198, 204)");

        $(".descripcion").hide();
        $(".detalles").show();
        $(".inscripcion").hide();
        $(".asistentes").hide();
    });

    $("#inscripcion").click(function () {
        $(this).css("background-color", "rgb(98, 207, 234)");
        $("#detalles").css("background-color", "rgb(175, 198, 204)");
        $("#descripcion").css("background-color", "rgb(175, 198, 204)");
        $("#asistentes").css("background-color", "rgb(175, 198, 204)");

        $(".descripcion").hide();
        $(".detalles").hide();
        $(".inscripcion").show();
        $(".asistentes").hide();

    });

    $("#asistentes").click(function () {
        $(this).css("background-color", "rgb(98, 207, 234)");
        $("#detalles").css("background-color", "rgb(175, 198, 204)");
        $("#descripcion").css("background-color", "rgb(175, 198, 204)");
        $("#inscripcion").css("background-color", "rgb(175, 198, 204)");

        $(".descripcion").hide();
        $(".detalles").hide();
        $(".inscripcion").hide();
        $(".asistentes").show();

    });

    //INVITACIONES

    // campo grupos
    $(".campo-grupos").show();
    // campo buscador
    $(".campo-buscador").hide();

    $('#codigoP').removeAttr('required');

    $("#tipo").change(function () {

        if ($(this).val() === 'GRUPAL') {
            $(".campo-grupos").show();
            $(".campo-buscador").hide();
            $('#codigoP').removeAttr('required');

        } else {
            $(".campo-grupos").hide();
            $(".campo-buscador").show();
            $('#codigoP').attr('required', true);
        }

    });


    $('.btn-gest-eliminar').click(function () {
        var id = $(this).data('id');

        Swal.fire({
            position: 'center',
            title: '<h4>¿Estás seguro de eliminar la actividad?</h4>',
            showConfirmButton: true,
            showCancelButton: true,
            cancelButtonText: 'Cancelar',
            confirmButtonColor: "rgb(218, 77, 73)",
            confirmButtonText: '<a href="/eliminar_actividad/' + id + '" id ="conf">Eliminar</a>',

            didRender: function () {
                const confirm = document.querySelector('#conf');

                if (confirm) {
                    confirm.style.color = 'white';
                }
            },

            showClass: {
                popup: 'animate__animated animate__fadeInDown'
            },
            hideClass: {
                popup: 'animate__animated animate__fadeOutUp'
            }

        })

    });

    // Filtros
    $('#filtrarActividades').on('keyup', function () {
        var buscado = $('#filtrarActividades').val().toLowerCase();
        var categSeleccionada = $('#categoria').val().toLowerCase();
        const actividades = $('.actividad');

        for (var i = 0; i < actividades.length; i++) {
            var nombre = actividades.eq(i).find('.nombre-act').text().toLowerCase();
            var categoria = actividades.eq(i).find('#categoria-acti').data('categoria').toLowerCase();

            if (buscado === '' && categSeleccionada === 'todas' || buscado === '' && categSeleccionada === categoria) {
                actividades.eq(i).show();
            }

            else if (nombre.includes(buscado) && categSeleccionada === 'todas') {
                actividades.eq(i).show();
            }

            else if (nombre.includes(buscado) && categoria === categSeleccionada) {
                actividades.eq(i).show();
            }
            else {
                actividades.eq(i).hide();
            }
        }
    });

    $('#categoria').on('change', function () { //cuando se cambia la categoria vuelve a hacer el keyup
        $('#filtrarActividades').trigger('keyup');
    });


});
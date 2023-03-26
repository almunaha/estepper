$(document).ready(function () {

    //Botones actividad
    $(".descripcion").show();
    $(".detalles").hide();
    $(".inscripcion").hide();

    $("#descripcion").css("background-color", "rgb(98, 207, 234)");

    $("#descripcion").click(function () {
        $(this).css("background-color", "rgb(98, 207, 234)");
        $("#detalles").css("background-color", "rgb(175, 198, 204)");
        $("#inscripcion").css("background-color", "rgb(175, 198, 204)");

        $(".descripcion").show();
        $(".detalles").hide();
        $(".inscripcion").hide();
    });

    $("#detalles").click(function () {
        $(this).css("background-color", "rgb(98, 207, 234)");
        $("#inscripcion").css("background-color", "rgb(175, 198, 204)");
        $("#descripcion").css("background-color", "rgb(175, 198, 204)");

        $(".descripcion").hide();
        $(".detalles").show();
        $(".inscripcion").hide();
    });

    $("#inscripcion").click(function () {
        $(this).css("background-color", "rgb(98, 207, 234)");
        $("#detalles").css("background-color", "rgb(175, 198, 204)");
        $("#descripcion").css("background-color", "rgb(175, 198, 204)");

        $(".descripcion").hide();
        $(".detalles").hide();
        $(".inscripcion").show();
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

    $('.btn-gest-eliminar').click(function() {
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



});
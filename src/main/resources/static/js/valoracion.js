/*function showAlertWithLink(link) {
    var alertMessage = "El usuario aún no pertenece a ningún grupo";
    var linkText = "Haga click aquí para agregarlo a un grupo";
    var alertHTML = '<div>' + alertMessage + '<br><a style="color: blue;" href="' + link + '">' + linkText + '</a></div>';

    $('<div></div>').html(alertHTML).dialog({
        modal: true,
        title: '¡Alerta!',
        buttons: {
            Ok: function () {
                $(this).dialog('close');
            }
        }
    });
}*/

function abrirIframe(modalInst) {
    const modal = new bootstrap.Modal(document.getElementById(modalInst));
    modal.show();
}


document.addEventListener('DOMContentLoaded', function () {
    const btn_part = document.getElementById('btn-part1');
    btn_part.addEventListener('click', function (e) {

        const grupo = btn_part.dataset.grupo;
        const estado = btn_part.dataset.estado;
        const id = btn_part.dataset.id;
        const formularios = btn_part.dataset.formularios;

        if (!grupo) {
            e.preventDefault();
            var link = '/unirAgrupo/' + id;
            abrirIframe('alertaActivar');

        } else if (estado === 'ALTA') {
            e.preventDefault();
            alert("El usuario ya está dado de alta.");
        } else if (!formularios) {
            e.preventDefault();
            alert("Debe rellenar correctamente los formularios de Exploracion y Findrisc. Para entrar en el proyecto el usuario debe ser mayor de 35 años y su puntuación de Findrisc mayor a 14.");
        }
        else {
            btn_part.disabled = true;
            alert("La cuenta del usuario se ha dado de alta correctamente.");
        }
    });
});
$(document).ready(function () {

    const enlaceGrupo = $('.enlace-grupo');

    enlaceGrupo.on('click', function () {
        // Obtener el id del grupo desde el href del enlace
        const grupoId = $(this).attr('href');
      
        // Cerrar el modal
        const modal = new bootstrap.Modal(document.getElementById('miModal'));
        modal.hide();
      
        // Redirigir al usuario a la página con el id del grupo
        window.location.href = `https://ejemplo.com/unir-a-grupo${grupoId}`;
      });
    

    $('.eliminar').click(function () {
        var id = $(this).data('id');

        Swal.fire({
            position: 'center',
            title: '<h4>¿Estás seguro de eliminar la cuenta?</h4>',
            showConfirmButton: true,
            showCancelButton: true,
            cancelButtonText: 'Cancelar',
            confirmButtonColor: "rgb(218, 77, 73)",
            confirmButtonText: '<a href="/eliminarcuenta/' + id + '" id ="conf">Eliminar</a>',

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

    //FORMULARIO ACTFISICA.HTML
    document.getElementById('minafv').addEventListener('input', calcularmetsafv);
    document.getElementById('horaafv').addEventListener('input', calcularminafv);
    document.getElementById('vecesafv').addEventListener('input', calcularmetsafv);


    document.getElementById('minafm').addEventListener('input', calcularmetsafm);
    document.getElementById('horaafm').addEventListener('input', calcularminafm);
    document.getElementById('vecesafm').addEventListener('input', calcularmetsafm);

    document.getElementById('mincaminar').addEventListener('input', calcularmetscaminar);
    document.getElementById('horacaminar').addEventListener('input', calcularmincaminar);
    document.getElementById('vecescaminar').addEventListener('input', calcularmetscaminar);

    document.getElementById('horasentado').addEventListener('input', calcularminsentado);
    document.getElementById('metstotales').addEventListener('input', calcularclasificacion);
    document.getElementById('metstotales').addEventListener('change', calcularclasificacion);


    function calcularclasificacion() {
        var metstotales = parseInt(document.getElementById('metstotales').value);
        var resultado;
        if (metstotales > 1500) { resultado = "vigorosa"; }
        else { resultado = "moderada"; }
        document.getElementById('clasificacion').value = resultado;

    }

    function calcularmetstotales() {
        var metscaminar = parseInt(document.getElementById('metscaminar').value);
        var metsafv = parseInt(document.getElementById('metsafv').value);
        var metsafm = parseInt(document.getElementById('metsafm').value);
        var resultado = metscaminar + metsafm + metsafv;
        document.getElementById('metstotales').value = resultado.toFixed(0);
        calcularclasificacion();
    }

    function calcularminsentado() {
        var horasentado = parseInt(document.getElementById('horasentado').value);
        document.getElementById('minsentado').value = (horasentado * 60);
    }

    function calcularmincaminar() {
        var horacaminar = parseInt(document.getElementById('horacaminar').value);
        document.getElementById('mincaminar').value = (horacaminar * 60);
        calcularmetscaminar();
    }


    function calcularmetscaminar() {
        var mincaminar = parseInt(document.getElementById('mincaminar').value);
        var vecescaminar = parseInt(document.getElementById('vecescaminar').value);
        var resultado = 3.3 * vecescaminar * mincaminar;
        document.getElementById('metscaminar').value = resultado.toFixed(0);
        calcularmetstotales();

    }


    function calcularminafm() {
        var horaafm = parseInt(document.getElementById('horaafm').value);
        document.getElementById('minafm').value = (horaafm * 60);
        calcularmetsafm();
    }


    function calcularmetsafm() {
        var minafm = parseInt(document.getElementById('minafm').value);
        var vecesafm = parseInt(document.getElementById('vecesafm').value);
        var resultado1 = 4 * vecesafm * minafm;
        document.getElementById('metsafm').value = resultado1.toFixed(0);
        calcularmetstotales();

    }


    function calcularmetsafv() {
        var minafv = parseInt(document.getElementById('minafv').value);
        var vecesafv = parseInt(document.getElementById('vecesafv').value);
        var resultado = 8 * vecesafv * minafv;
        document.getElementById('metsafv').value = resultado.toFixed(0);
        calcularmetstotales();

    }


    function calcularminafv() {
        var horaafv = parseInt(document.getElementById('horaafv').value);
        document.getElementById('minafv').value = (horaafv * 60);
        calcularmetsafv();
    }

    //FIN FORMULARIO ACTFISICA.HTML

});



function myFunction() {
    if (confirm("Desea guardar los datos?")) {
        alert("Datos guardados exitosamente");
    } else {
        alert("Usted cancelo la acción para guardar");
    }
}


document.addEventListener('DOMContentLoaded', function () {
    var btnEstado = document.getElementById('estado');
    const btn_unir = document.getElementById('btn-unir');
    const tablaParticipantes = document.querySelector('.tablePart tbody');

    btnEstado.addEventListener('change', function () {
        if (btnEstado.value == "Todos") {
            for (let i = 0; i < tablaParticipantes.rows.length; i++) {
                tablaParticipantes.rows[i].style.display = "";
            }
        }
        if (btnEstado.value == "ALTA" || btnEstado.value == "BAJA") {
            for (let i = 0; i < tablaParticipantes.rows.length; i++) {
                const estadoTabla = tablaParticipantes.rows[i].cells[5].textContent;
                if (estadoTabla !== btnEstado.value) {
                    tablaParticipantes.rows[i].style.display = "none";
                } else {
                    tablaParticipantes.rows[i].style.display = "";
                }
            }
        }
    });

});

$(document).ready(function () {

    const tablaParticipantes = document.querySelector('.tablePart tbody');

    $('#columnaGrupo').hide();
    for (let i = 0; i < tablaParticipantes.rows.length; i++) {
        tablaParticipantes.rows[i].cells[4].style.display = "none";
    }


    $('.btn-unir').click(function (event) {
        var idGrupo = $(this).closest('tr').find('td:eq(4)').text();

        if (idGrupo !== '0') {
            event.preventDefault();
            $('#alertaModal1').modal('show');
        }
    });

    (function ($) {
        $('#filtrarParticipantes').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarParticipantes tr').hide();
            $('.buscarParticipantes tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        })
    }(jQuery));
});
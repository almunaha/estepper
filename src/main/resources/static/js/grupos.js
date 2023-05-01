$(document).ready(function () {

  $('.eliminarGrupo').click(function () {
    var id = $(this).data('id');

    Swal.fire({
      position: 'center',
      title: '<h4>¿Estás seguro de eliminar el grupo?</h4>',
      showConfirmButton: true,
      showCancelButton: true,
      cancelButtonText: 'Cancelar',
      confirmButtonColor: "rgb(218, 77, 73)",
      confirmButtonText: '<a href="/grupos/eliminar/' + id + '" id ="conf">Eliminar</a>',

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

  //buscador grupos
  (function ($) {
    $('#filtrarGrupos').keyup(function () {
      var rex = new RegExp($(this).val(), 'i');
      $('.buscarGrupos tr').hide();
      $('.buscarGrupos tr').filter(function () {
        return rex.test($(this).text());
      }).show();
    })
  }(jQuery));

  //Botones objetivos recomendados
  $(".chatGrupal").show();
  $(".chatCoordinador").hide();

  $("#chatGrupal").css("background-color", "rgb(98, 216, 110)");

  $("#chatGrupal").click(function () {
    $(this).css("background-color", "rgb(98, 216, 110)");
    $("#chatCoordinador").css("background-color", "rgb(175, 204, 185)");
    $(".chatGrupal").show();
    $(".chatCoordinador").hide();
  });

  $("#chatCoordinador").click(function () {
    $(this).css("background-color", "rgb(98, 216, 110)");
    $("#chatGrupal").css("background-color", "rgb(175, 204, 185)");
    $(".chatGrupal").hide();
    $(".chatCoordinador").show();
  });



  const listadoParticipantesGrupoPeso = document.querySelectorAll('.listaDeParticipantes .perdida-peso-individual');
  const listadoParticipantesGrupoAsistencia = document.querySelectorAll('.listaDeParticipantes .asistencia-individual');
  const listadoParticipantesGrupoCmCintura = document.querySelectorAll('.listaDeParticipantes .perdida-cmcintura-individual');

  var perdidaPesoIndividual = document.getElementsByClassName("perdida-peso-individual");
  var asistenciaIndividual = document.getElementsByClassName("asistencia-individual");
  var perdidaCmCinturaIndividual = document.getElementsByClassName("perdida-cmcintura-individual");
  var totalPerdidaPeso = 0;
  var totalAsistencia = 0;
  var totalPerdidaCmCintura = 0;

  for (var i = 0; i < perdidaPesoIndividual.length; i++) {
    var pesoIndividual = parseFloat(perdidaPesoIndividual[i].innerText);
    var asistIndividual = parseFloat(asistenciaIndividual[i].innerText);
    var cmCinIndividual = parseFloat(perdidaCmCinturaIndividual[i].innerText);

    if (!isNaN(pesoIndividual)) {
      totalPerdidaPeso += pesoIndividual;
      totalAsistencia += asistIndividual;
      totalPerdidaCmCintura += cmCinIndividual;
    }
  }

  const perdidaPesoMedia = (totalPerdidaPeso / listadoParticipantesGrupoPeso.length).toFixed(2);
  const asistenciaMedia = (totalAsistencia / listadoParticipantesGrupoAsistencia.length).toFixed(2);
  const perdidaCmCinturaMedia = (totalPerdidaCmCintura / listadoParticipantesGrupoCmCintura.length).toFixed(2);


  document.querySelector('#perdida-peso-media').textContent = perdidaPesoMedia + ' kg';
  document.querySelector('#asistencia-media').textContent = asistenciaMedia + '%';
  document.querySelector('#perdida-cmcintura-media').textContent = perdidaCmCinturaMedia + ' cm';

});

document.addEventListener('DOMContentLoaded', function () {
  var btnEstado = document.getElementById('estadoGrupo');
  const tablaGrupos = document.querySelector('.tablePart tbody');

  btnEstado.addEventListener('change', function () {
    if (btnEstado.value == "TODOS") {
      for (let i = 0; i < tablaGrupos.rows.length; i++) {
        tablaGrupos.rows[i].style.display = "";
      }
    }
    else if (btnEstado.value == "ACTIVO" || btnEstado.value == "TERMINADO") {
      for (let i = 0; i < tablaGrupos.rows.length; i++) {
        const estadoTabla = tablaGrupos.rows[i].cells[6].textContent;
        if (estadoTabla == btnEstado.value) {
          tablaGrupos.rows[i].style.display = "";
        } else {
          tablaGrupos.rows[i].style.display = "none";
        }
      }
    }
  });

});

function abrirIframe(modalId) {
  const modal = new bootstrap.Modal(document.getElementById(modalId));
  modal.show();
}


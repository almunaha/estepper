var almacenado = localStorage.getItem("chat");

function iniciarChat() {
  if (almacenado == null) {//iniciar variable global chat
    localStorage.setItem("chat", "grupal");
  }
}

function cambiarChat() {
  var almacenado = localStorage.getItem("chat");
  if (almacenado == "grupal") {
    $('#chatGrupal').css("background-color", "rgb(98, 216, 110)");
    $("#chatCoordinador").css("background-color", "rgb(175, 204, 185)");
    $(".chatGrupal").show();
    $(".chatCoordinador").hide();
  }
  else if (almacenado == "coordinador") {
    $('#chatCoordinador').css("background-color", "rgb(98, 216, 110)");
    $("#chatGrupal").css("background-color", "rgb(175, 204, 185)");
    $(".chatGrupal").hide();
    $(".chatCoordinador").show();
  }
}

function abrirIframe(modalId) {
  const modal = new bootstrap.Modal(document.getElementById(modalId));
  modal.show();
}
  

$(document).ready(function () {

  iniciarChat();
  cambiarChat();

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
      var btnEstado = document.getElementById('estadoGrupo');

      $('.buscarGrupos tr').hide();
      $('.buscarGrupos tr').filter(function () {
        var columnaEstado = $(this).find('td:eq(6)').text();

        if (btnEstado.value == columnaEstado || btnEstado.value == "TODOS") {
          return rex.test($(this).text());
        }

      }).show();

    })
  }(jQuery));


  $("#chatGrupal").click(function () {
    localStorage.setItem("chat", "grupal");
    cambiarChat();
  });

  $("#chatCoordinador").click(function () {
    localStorage.setItem("chat", "coordinador");
    cambiarChat();
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


  const deleteButtons = document.getElementsByClassName('note-delete-button');
Array.prototype.forEach.call(deleteButtons, function (button) {
  button.addEventListener('click', function () {
    // Obtener el id del grupo y de la nota
    const notaInfo = this.getAttribute('id').split('-');
    const id = notaInfo[1];
    const idGrupo = notaInfo[2];

    console.log("HOOOOOOLAAAAA");

    Swal.fire({
      position: 'center',
      title: '<h4>¿Estás seguro de eliminar la nota?</h4>',
      showConfirmButton: true,
      showCancelButton: true,
      cancelButtonText: 'Cancelar',
      confirmButtonColor: "rgb(218, 77, 73)",
      confirmButtonText: '<a href="/eliminarNota/' + id + '/' + idGrupo + '" id ="conf">Eliminar</a>',

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

    });

  })
});


// Cargar los eventos de los botones EDIT NOTA
const editButtons = document.getElementsByClassName('note-edit-button');
Array.prototype.forEach.call(editButtons, function (button) {
  button.addEventListener('click', function () {
    // Obtener el id del grupo y de la nota
    const notaInfo = this.getAttribute('id').split('-');
    const id = notaInfo[1];
    const idGrupo = notaInfo[2];
    const mensaje = this.getAttribute('data-message');

    document.getElementById('nota-cargada').value = mensaje;
    document.getElementById('nota-id').value = id;
    abrirIframe('mi-modal2');

    // Obtiene el objeto de la observación como una cadena JSON
    const observacion = this.getAttribute('data-observacion');
    const observacionObj = JSON.parse(observacion);

    // Asigna el objeto completo de la observación al formulario del modal
    $('unaObservacion').data('data-observacion', observacionObj);

  })

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


});


});


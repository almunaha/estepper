$(document).ready(function () {
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
    document.querySelector('#asistencia-media').textContent = asistenciaMedia + '% sesiones';
    document.querySelector('#perdida-cmcintura-media').textContent = perdidaCmCinturaMedia + ' cm';


    
    
});
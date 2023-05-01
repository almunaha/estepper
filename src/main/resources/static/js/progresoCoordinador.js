// Progreso participante: vista coordinador

$(document).ready(function () {
    //URL actual
    var baseUrl = window.location.origin;
    var meses = ["ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic"];
    
    //Id del participante
    var idPart = $("#idPart").val();

    //PESO

    //Botones del PESO
    $(".pesoTabla").hide();
    $("#graficaPesoCoor").show();

    $("#registroPesoC").click(function () {
        $(".pesoTabla").show();
        $("#graficaPesoCoor").hide();
    });

    $("#graficaPesoC").click(function () {
        $("#graficaPesoCoor").show();
        $(".pesoTabla").hide();
    });

    // GRÁFICA PESO
    let graficaPesoCoor = document.getElementById("graficaPesoCoor").getContext("2d");

    var pesoCoor = new Chart(graficaPesoCoor, {
        type: "line",
        data: {
            datasets: [
                {
                    label: "Peso",
                    borderColor: 'rgb(75, 192, 192)',
                }
            ]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                },
                x: {
                    display: false
                }
            }
        }
    })

    let url3 = baseUrl + '/progreso/peso/' + idPart + "/" + 0 + "/" + 0; //petición http
    fetch(url3)
        .then(response => response.json())
        .then(data => {
            for (i in data) {
                var fecha = new Date(data[i].fecha);
                var dia = fecha.getDate();
                var mes = fecha.getMonth();
                var anio = fecha.getFullYear();
                var dato = dia + " " + meses[mes] + " " + anio;

                pesoCoor.data['labels'].push(dato);
                pesoCoor.data['datasets'][0].data.push(data[i].dato);
            }
            pesoCoor.update();
        });

    //PERÍMETRO

    //Botones del PERÍMETRO
    $(".perimetroTabla").hide();
    $("#graficaPerCoor").show();

    $("#registroPerimetroC").click(function () {
        $(".perimetroTabla").show();
        $("#graficaPerCoor").hide();
    });

    $("#graficaPerimetroC").click(function () {
        $("#graficaPerCoor").show();
        $(".perimetroTabla").hide();
    });

    // GRÁFICA PERÍMETRO
    let graficaPerimetroCoor = document.getElementById("graficaPerCoor").getContext("2d");

    var perimetroCoor = new Chart(graficaPerimetroCoor, {
        type: "line",
        data: {
            datasets: [
                {
                    label: "Perimetro",
                    borderColor: 'rgb(75, 192, 192)',
                }
            ]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                },
                x: {
                    display: false
                }
            }
        }
    })

    let url4 = baseUrl + '/progreso/perimetro/' + idPart + "/" + 0 + "/" + 0; //petición http
    fetch(url4)
        .then(response => response.json())
        .then(data => {
            for (i in data) {
                var fecha = new Date(data[i].fecha);
                var dia = fecha.getDate();
                var mes = fecha.getMonth();
                var anio = fecha.getFullYear();
                var dato = dia + " " + meses[mes] + " " + anio;

                perimetroCoor.data['labels'].push(dato);
                perimetroCoor.data['datasets'][0].data.push(data[i].dato);
            }
            perimetroCoor.update();
        });

});
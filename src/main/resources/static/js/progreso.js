$(document).ready(function () {

    //Botones del PESO
    $(".pesoTabla").hide();
    $("#form-peso").hide();
    $("#grafica").show();

    $("#registroPeso").click(function () {
        $(".pesoTabla").show();
        $("#grafica").hide();
        $("#form-peso").hide();
    });

    $("#graficaPeso").click(function () {
        $("#grafica").show();
        $(".pesoTabla").hide();
        $("#form-peso").hide();
    });

    $(".btn-plus").click(function () {
        $("#grafica").hide();
        $(".pesoTabla").hide();
        $("#registroPeso").hide();
        $("#graficaPeso").hide();
        $(".btn-plus").hide();
        $("#form-peso").show();
    });

    //Gráfica PESO
    let graficaPeso = document.getElementById("grafica").getContext("2d");

    var peso = new Chart(graficaPeso, {
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
                }
            }
        }
    })

    let url = 'http://localhost:7070/progreso/peso'; //petición http
    fetch(url)
        .then(response => response.json())
        .then(data => {
            for (i in data) {
                peso.data['labels'].push(data[i].fecha);
                peso.data['datasets'][0].data.push(data[i].dato);
            }
            peso.update();
        });


});
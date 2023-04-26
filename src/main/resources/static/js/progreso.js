function abrirIframe(modalInst) {
    const modal = new bootstrap.Modal(document.getElementById(modalInst));
    modal.show();
}

$(document).ready(function () {

    //Botones del PESO
    $(".pesoTabla").hide();
    $("#grafica").show();

    $("#registroPeso").click(function () {
        $(".pesoTabla").show();
        $("#grafica").hide();
    });

    $("#graficaPeso").click(function () {
        $("#grafica").show();
        $(".pesoTabla").hide();
    });

    $("#cancelar-pes").click(function(){
        $("#grafica").show();
        $(".pesoTabla").hide();
        $("#registroPeso").show();
        $("#graficaPeso").show();
        $(".btn-plus").show();
    });

    //URL actual
    var baseUrl = window.location.origin;
    var meses = ["ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic"];

    //Id del participante
    var idUser = $("#idUser").val();

    //Gráfica PESO
    let graficaPeso = document.getElementById("grafica").getContext("2d");

    var peso = new Chart(graficaPeso, {
        type: "line",
        data: {
            datasets: [
                {
                    label: "Peso",
                    borderColor: '#6ca4c7',
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
    let url = baseUrl + '/progreso/peso/' + idUser; //petición http
    fetch(url)
        .then(response => response.json())
        .then(data => {
            for (i in data) {
                var fecha = new Date(data[i].fecha);
                var dia = fecha.getDate();
                var mes = fecha.getMonth();
                var anio = fecha.getFullYear();
                var dato = dia + " " + meses[mes] + " " + anio;

                peso.data['labels'].push(dato);
                peso.data['datasets'][0].data.push(data[i].dato);
            }
            peso.update();
        });

    //Gráfica PERÍMETRO
    let graficaPerimetro = document.getElementById("grafica2").getContext("2d");

    var perimetro = new Chart(graficaPerimetro, {
        type: "line",
        data: {
            datasets: [
                {
                    label: "Perimetro",
                    borderColor: '#6ca4c7',
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

    let url2 = baseUrl + '/progreso/perimetro/' + idUser; //petición http
    fetch(url2)
        .then(response => response.json())
        .then(data => {
            for (i in data) {
                var fecha = new Date(data[i].fecha);
                var dia = fecha.getDate();
                var mes = fecha.getMonth();
                var anio = fecha.getFullYear();
                var dato = dia + " " + meses[mes] + " " + anio;

                perimetro.data['labels'].push(dato);
                perimetro.data['datasets'][0].data.push(data[i].dato);
            }
            perimetro.update();
        });

    //Botones del PERÍMETRO
    $(".perimetroTabla").hide();
    $("#grafica2").show();

    $("#registroPerimetro").click(function () {
        $(".perimetroTabla").show();
        $("#grafica2").hide();
    });

    $("#graficaPerimetro").click(function () {
        $("#grafica2").show();
        $(".perimetroTabla").hide();
    });

    $("#cancelar-per").click(function () {
        $("#grafica2").show();
        $(".perimetroTabla").hide();
        $("#registroPerimetro").show();
        $("#graficaPerimetro").show();
        $(".btn-plus2").show();
    });

    //IMC
    var imc = parseFloat(document.getElementById("imc").textContent); //pasar el texto a un número float
    var valores = [
        { label: "NORMOPESO", value: 25, color: "#61AF37" },
        { label: "SOBREPESO", value: 29, color: "#F39E4A" },
        { label: "OBESIDAD", value: 40, color: "#DA4F3F" }
    ];

    var resultado = "";
    for (var i = 0; i < valores.length; i++) {
        if (imc <= valores[i].value) {
            resultado = valores[i].label;
            break;
        }
    }

    var myChart = new Chart(document.getElementById("resultadoIMC"), {
        type: 'doughnut',
        data: {
            labels: [resultado],
            datasets: [{
                data: [imc],
                backgroundColor: [valores[i].color],
                borderWidth: 0,
                hoverBorderColor: "rgba(255,255,255,1)",
                hoverBorderWidth: 2
            }]
        },
        options: {
            cutoutPercentage: 70,
            rotation: 270,
            circumference: 180,
            legend: {
                display: false
            },
            tooltips: {
                enabled: false
            }
        }
    });

    // Progreso participante: vista coordinador

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

    let url3 = baseUrl + '/progreso/peso'; //petición http
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

    let url4 = baseUrl + '/progreso/perimetro'; //petición http
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
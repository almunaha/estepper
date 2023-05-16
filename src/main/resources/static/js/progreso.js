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

    $("#cancelar-pes").click(function () {
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


    let url;
    //Coger filtro de fecha de peso, fecha ini y fecha fin
    var iniPeso = 0;
    var finPeso = 0;

    function actualizarPeso() {

        url = baseUrl + '/progreso/peso/' + idUser + '/' + iniPeso + '/' + finPeso; //petición http

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
    }

    actualizarPeso();

    $("#iniPeso").change(function () {
        iniPeso = $(this).val();
        peso.data.labels = [];
        peso.data['datasets'][0].data = [];
        actualizarPeso();
    });

    $("#iniPeso2").change(function () {
        iniPeso = $(this).val();
        peso.data.labels = [];
        peso.data['datasets'][0].data = [];
        actualizarPeso();
    });

    $("#finPeso").change(function () {
        finPeso = $(this).val();
        peso.data.labels = [];
        peso.data['datasets'][0].data = [];
        actualizarPeso();
    });

    $("#finPeso2").change(function () {
        finPeso = $(this).val();
        peso.data.labels = [];
        peso.data['datasets'][0].data = [];
        actualizarPeso();
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

    let url2;
    //Coger filtro de fecha de peso, fecha ini y fecha fin
    var iniPerimetro = 0;
    var finPerimetro = 0;

    function actualizarPerimetro() {
        url2 = baseUrl + '/progreso/perimetro/' + idUser + "/" + iniPerimetro + "/" + finPerimetro; //petición http
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

    }

    actualizarPerimetro();

    $("#iniPerimetro").change(function () {
        iniPerimetro = $(this).val();
        perimetro.data.labels = [];
        perimetro.data['datasets'][0].data = [];
        actualizarPerimetro();
    });

    $("#iniPerimetro2").change(function () {
        iniPerimetro = $(this).val();
        perimetro.data.labels = [];
        perimetro.data['datasets'][0].data = [];
        actualizarPerimetro();
    });

    $("#finPerimetro").change(function () {
        finPerimetro = $(this).val();
        perimetro.data.labels = [];
        perimetro.data['datasets'][0].data = [];
        actualizarPerimetro();
    });

    $("#finPerimetro2").change(function () {
        finPerimetro = $(this).val();
        perimetro.data.labels = [];
        perimetro.data['datasets'][0].data = [];
        actualizarPerimetro();
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

});
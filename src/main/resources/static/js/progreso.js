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

    //URL actual
    var baseUrl = window.location.origin;
    console.log(baseUrl);
    var meses = ["ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic"];

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
                },
                x: {
                    display: false
                }
            }
        }
    })
    let url = baseUrl + '/progreso/peso'; //petición http
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

    let url2 = baseUrl + '/progreso/perimetro'; //petición http
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
     $("#form-perimetro").hide();
     $("#grafica2").show();
 
     $("#registroPerimetro").click(function () {
         $(".perimetroTabla").show();
         $("#grafica2").hide();
         $("#form-perimetro").hide();
     });
 
     $("#graficaPerimetro").click(function () {
         $("#grafica2").show();
         $(".perimetroTabla").hide();
         $("#form-perimetro").hide();
     });
 
     $(".btn-plus2").click(function () {
         $("#grafica2").hide();
         $(".perimetroTabla").hide();
         $("#registroPerimetro").hide();
         $("#graficaPerimetro").hide();
         $(".btn-plus2").hide();
         $("#form-perimetro").show();
     });

});
$(document).ready(function () {

    //grafico de sesiones completadas en inicio
    //URL actual
    var baseUrl = window.location.origin;
  
    let canvas = document.getElementById("graficoSesiones").getContext("2d");
    let url = baseUrl + '/index/sesiones';
  
    fetch(url)
      .then(response => response.json())
      .then(data => {
  
        var graficaSesiones = new Chart(canvas, {
          type: "pie",
  
  
          data: {
            labels: ["Sesiones completadas", "Sesiones pendientes"],
  
            datasets: [
              {
                data: [data.sesionesCompletas, 10 - data.sesionesCompletas],
                backgroundColor: [
                  'rgba(100, 182, 205, 0.915)', // Color para sesiones completadas
                  'rgba(229, 203, 108, 0.915)'    // Color para sesiones pendientes
                ]
              }
            ]
          },
  
        })
      })
  
    //progreso de inicio 
    
    var porcentajeProgreso = document.getElementById('chartPorcentajeProgreso').getAttribute('data-porcentaje-progreso');
    var options = {
      chart: {
        height: 280,
        type: "radialBar",
      },
  
      series: [porcentajeProgreso], //porcentaje
      colors: ["#20E647"],
      plotOptions: {
        radialBar: {
          hollow: {
            margin: 0,
            size: "70%",
            background: "#293450"
          },
          track: {
            dropShadow: {//para el bordecito de fuera la sombrita
              enabled: true,
              top: 3,
              left: 0,
              blur: 4,
              opacity: 0.20
            }
          },
          dataLabels: {
            name: {//Para el nombre de dentro de progreso
              offsetY: -10,
              color: "#fff",
              fontSize: "15px"
            },
            value: { //Para el porcentaje
              color: "#fff",
              fontSize: "35px",
              show: true
            }
          }
        }
      },
      fill: { //para el relleno
        type: "gradient",
        gradient: {
          shade: "dark",
          type: "vertical",
          gradientToColors: ["#87d4f9"], //que vaya hacia ese azulito
          stops: [0, 100]
        }
      },
      stroke: {
        lineCap: "round" //prolongación redondeada de la línea
      },
      labels: ["Progreso"] //el nombre de dentro
    };
  
    var chart = new ApexCharts(document.querySelector("#chart3"), options);
  
    chart.render();
  
  });
$(document).ready(function () {
    $(".resultadosICDR").hide();
});

function abrirIframe(modalId) {
    const modal = new bootstrap.Modal(document.getElementById(modalId));
    modal.show();
}

function validarFormulario() {
    let peso = document.getElementById("inputPeso").value;
    let altura = document.getElementById("inputAltura").value;

    if (peso === "" || altura === "") {
        abrirIframe('mi-modalICDR');
    } else {
        calcular();
    }
}


function calcular() {
    $(".resultadosICDR").show();
    // Obtener los valores ingresados por el usuario
    var peso = document.getElementById("inputPeso").value;
    var altura = document.getElementById("inputAltura").value;
    var actFisica = document.getElementById("inputActFisica").value;
    var edad = document.getElementById("edad").value;  //Obtener el valor de la entrada de edad
    var edad2 = parseFloat(edad);  //Convertir la cadena de texto a un n
    var sexo = document.getElementById("sexo").value;


    // Realizar el cálculo con los valores ingresados

    if (sexo == 'MASCULINO') {
        var MB = 88.362 + (13.397 * peso) + (4.799 * altura) - (5.677 * edad2);
    }
    else {
        var MB = 447.593 + (9.247 * peso) + (3.098 * altura) - (4.330 * edad2);
    }

    var ICDR = 0;

    if (actFisica == 'SEDENTARIO') {
        ICDR = MB * 1.2;
    } else if (actFisica == 'LIGERA') {
        ICDR = MB * 1.375;
    } else if (actFisica == 'MODERADA') {
        ICDR = MB * 1.55;
    } else if (actFisica == 'INTENSA') {
        ICDR = MB * 1.725;
    } else if (actFisica == 'MUYINTENSA') {
        ICDR = MB * 1.9;
    }

    const fibraCon = document.getElementById("fibraCon");
    const valorFibra = fibraCon.innerText;
    var fibra = parseFloat(valorFibra);

    const proteinaCon = document.getElementById("proteinasCon");
    const valorProteina = proteinaCon.innerText;
    var proteina = parseFloat(valorProteina);

    const grasasCon = document.getElementById("grasasCon");
    const valorGrasas = grasasCon.innerText;
    var grasas = parseFloat(valorGrasas);

    const hidratosCon = document.getElementById("carbohidratosCon");
    const valorHidratos = hidratosCon.innerText;
    var hidratos = parseFloat(valorHidratos);

    const salesCon = document.getElementById("salCon");
    const salesRes = document.getElementById("salesRes");
    const valorSales = salesCon.innerText;
    var sales = parseFloat(valorSales);


    if (fibra > 20 && fibra < 30) {
        fibraIcon.innerHTML = '<i class="fas fa-check" style="color:green"></i>';
        fibraIcon.innerText = '¡BIEN! Estás entre los 20 y 30 gramos de fibra recomendados';
        fibraIcon.style.color = 'green';

    } else {
        if (fibra < 20) {
            fibraIcon.innerHTML = '<i class="fas fa-times" style="color:red"></i>';
            fibraText.innerText = 'Estás por debajo de los 20 gramos mínimos de fibra recomendados';
            fibraText.style.color = 'red';
        } else {
            fibraIcon.innerHTML = '<i class="fas fa-times" style="color:red"></i>';
            fibraText.innerText = 'Estás por encima de los 30 gramos máximos de fibra recomendados';
            fibraText.style.color = 'red';

        }
    }

    if (sales < 2.3) {
        salesIcon.innerHTML = '<i class="fas fa-check" style="color:green"></i>';
        salesText.innerText = '¡BIEN! Estás por debajo de los 2,3 gramos máximos de sales recomendados';
        salesText.style.color = 'green';
    } else {
        salesIcon.innerHTML = '<i class="fas fa-times" style="color:red"></i>';
        salesText.innerText = 'Estás por encima de los 2,3 gramos máximos de sales recomendados';
        salesText.style.color = 'red';
    }

    grasas = grasas * 9;
    var grasasMax = ICDR * 0.1;

    if (grasas < grasasMax) {
        grasasIcon.innerHTML = '<i class="fas fa-check" style="color:green"></i>';
        grasasText.innerText = '¡BIEN! Tu consumo de grasas es menor del 10% respecto a tu ICDR';
        grasasText.style.color = 'green';
    } else {
        grasasIcon.innerHTML = '<i class="fas fa-times" style="color:red"></i>';
        grasasText.innerText = 'Vaya, has consumido demasiadas grasas: ' + (grasas / 9).toFixed(2) + ' gramos (' + grasas.toFixed(2) + ' Kcal). Según tu ICDR lo recomendado es ingerir menos de ' + (grasasMax / 9).toFixed(2) + ' gramos (' + grasasMax.toFixed(2) + ' Kcal) de grasas saturadas';
        grasasText.style.color = 'red';
    }

    proteina = proteina * 4;
    var proteinaMin = ICDR * 0.1;
    var proteinaMAx = ICDR * 0.35;

    if (proteina > proteinaMin && proteina < proteinaMAx) {
        proteinasIcon.innerHTML = '<i class="fas fa-check" style="color:green"></i>';
        proteinasText.innerText = '¡BIEN! Tu consumo de proteínas se encuentra entre el 10% y 35% respecto a tu ICDR';
        hidratosText.style.color = 'green';

    } else {
        if (proteina < proteinaMin) {
            var proteinaNecesaria = proteinaMin - proteina;

            proteinasIcon.innerHTML = '<i class="fas fa-times" style="color:orange"></i>';
            proteinasText.innerText = 'Acorde con tu ICDR, necesitas consumir  ' + (proteinaNecesaria / 4).toFixed(2) + ' gramos (' + proteinaNecesaria.toFixed(2) + ' Kcal) más para llegar al mínimo recomendado (10% del ICDR)';
            proteinasText.style.color = 'orange';
        } else {
            proteinasIcon.innerHTML = '<i class="fas fa-times" style="color:red"></i>';
            proteinasText.innerText = 'Vaya, has consumido demasiadas proteínas: ' + (proteina / 4).toFixed(2) + ' gramos (' + proteina.toFixed(2) + ' Kcal). Según tu ICDR lo recomendado es ingerir entre ' + (proteinaMin / 4).toFixed(2) + ' gramos (' + proteinaMin.toFixed(2) + ' Kcal) y ' + (proteinaMAx / 4).toFixed(2) + ' gramos (' + proteinaMAx.toFixed(2) + ' Kcal) de proteínas';
            proteinasText.style.color = 'red';

        }
    }

    hidratos = hidratos * 4;
    var hidratosMin = ICDR * 0.45;
    var hidratosMax = ICDR * 0.65;

    if (hidratos > hidratosMin && hidratos < hidratosMax) {
        hidratosIcon.innerHTML = '<i class="fas fa-check" style="color:green"></i>';
        hidratosText.innerText = '¡BIEN! Tu consumo de hidratos se encuentra entre el 45% y 65% respecto a tu ICDR';
        hidratosText.style.color = 'green';
    } else {
        if (hidratos < hidratosMin) {
            var hidratosNecesarios = ICDR * 0.45 - hidratos;
            hidratosIcon.innerHTML = '<i class="fas fa-times" style="color:orange"></i>';
            hidratosText.innerText = 'Acorde con tu ICDR, necesitas consumir  ' + (hidratosNecesarios / 4).toFixed(2) + ' gramos (' + hidratosNecesarios.toFixed(2) + ' Kcal) más para llegar al mínimo recomendado (45% del ICDR)';
            hidratosText.style.color = 'orange';
        } else {
            hidratosIcon.innerHTML = '<i class="fas fa-times" style="color:red"></i>';
            hidratosText.innerText = 'Vaya, has consumido demasiados hidratos de carbono: ' + (hidratos / 4).toFixed(2) + ' gramos (' + hidratos.toFixed(2) + ' Kcal). Según tu ICDR lo recomendado es ingerir entre ' + (hidratosMin / 4).toFixed(2) + ' gramos (' + hidratosMin.toFixed(2) + ' Kcal) y ' + (hidratosMax / 4).toFixed(2) + ' gramos (' + hidratosMax.toFixed(2) + ' Kcal) de hidratos de carbono';
            hidratosText.style.color = 'red';
        }
    }


    // Mostrar el resultado al usuario
    //alert("El resultado del ICDR es: " + ICDR);
    document.getElementById('resultadoICDR').innerHTML = 'Tu ICDR es: ' + ICDR.toFixed(2) + ' Kcal';
    document.getElementById('resultadoICDR').style.display = "block";


    // Crear una petición XMLHttpRequest
    var xhr = new XMLHttpRequest();
    // Configurar la petición
    xhr.open("GET", "/nutrientes?valor=" + ICDR, true);
    // Enviar la petición
    xhr.send();
}



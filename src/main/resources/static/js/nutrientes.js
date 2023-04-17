$(document).ready(function () {

    //CAMBIAR TODO ESTO

    // Definir los requerimientos nutricionales en gramos
    const reqFibra = 100;
    const reqGrasasSaturadas = 100;
    const reqCarbohidratos = 100;
    const reqProteinas = 100;
    const reqSal = 100;

    // Definir la cantidad de nutrientes consumidos por el usuario en gramos
    const consumoFibra = document.getElementById('fibraCon');
    const consumoGrasasSaturadas = document.getElementById('grasasCon');
    const consumoCarbohidratos = document.getElementById('carbohidratosCon');
    const consumoProteinas = document.getElementById('proteinasCon');
    const consumoSal = document.getElementById('salCon');

    // Calcular la cantidad de nutrientes faltantes en gramos
    const fibraFaltante = reqFibra - consumoFibra;
    const grasasFaltante = reqGrasasSaturadas - consumoGrasasSaturadas;
    const carbohidratosFaltante = reqCarbohidratos - consumoCarbohidratos;
    const proteinasFaltante = reqProteinas - consumoProteinas;
    const salFaltante = reqSal - consumoSal;

    // Imprimir la cantidad de nutrientes faltantes en gramos
    console.log("Fiber missing: " + fibraFaltante);
    console.log("Saturated fat missing: " + grasasFaltante);
    console.log("Carbs missing: " + carbohidratosFaltante);
    console.log("Protein missing: " + proteinasFaltante);
    console.log("Salt missing: " + salFaltante);


});
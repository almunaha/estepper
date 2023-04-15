$(document).ready(function () {
    const importanciaRange = document.getElementById('importancia-range');
    const importanciaValue = document.getElementById('importancia-value');

    const capacidadRange = document.getElementById('capacidad-range');
    const capacidadValue = document.getElementById('capacidad-value');

    importanciaRange.addEventListener('input', function () {
        importanciaValue.textContent = importanciaRange.value;
    });

    capacidadRange.addEventListener('input', function () {
        capacidadValue.textContent = capacidadRange.value;
    });
});
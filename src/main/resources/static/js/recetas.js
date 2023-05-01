$(document).ready(function () {
     // Obtener la lista de opciones del select
     var opciones = $("#lista-alimentos option");

     // Crear un input de búsqueda
     var inputBusqueda = $('<input style="margin-right:15%;" type="text" class="form-control mt-2" placeholder="Buscar...">');

     // Agregar el input de búsqueda antes del select
     $("#lista-alimentos").before(inputBusqueda);

     // Escuchar el evento keyup del input de búsqueda
     inputBusqueda.keyup(function () {
         // Obtener el término de búsqueda
         var term = $(this).val().toLowerCase();

         // Filtrar las opciones que contengan el término de búsqueda
         var opcionesFiltradas = opciones.filter(function () {
             return $(this).text().toLowerCase().indexOf(term) > -1;
         });

         // Actualizar las opciones del select con las opciones filtradas
         $("#lista-alimentos").empty().append(opcionesFiltradas);
     });

     // Restaurar todas las opciones del select cuando se cierre
     $("#lista-alimentos").on("hidden.bs.select", function () {
         $("#lista-alimentos").empty().append(opciones);
     });

     $("#lista-alimentos").keydown("hidden.bs.select", function () {
         $("#lista-alimentos").empty().append(opciones);
     });
     
     // Obtener la lista de opciones del select
     var opciones = $("#lista-alimentos2 option");

     // Crear un input de búsqueda
     var inputBusqueda = $('<input style="margin-right:15%;" type="text" class="form-control mt-2" placeholder="Buscar...">');

     // Agregar el input de búsqueda antes del select
     $("#lista-alimentos2").before(inputBusqueda);

     // Escuchar el evento keyup del input de búsqueda
     inputBusqueda.keyup(function () {
         // Obtener el término de búsqueda
         var term = $(this).val().toLowerCase();

         // Filtrar las opciones que contengan el término de búsqueda
         var opcionesFiltradas = opciones.filter(function () {
             return $(this).text().toLowerCase().indexOf(term) > -1;
         });

         // Actualizar las opciones del select con las opciones filtradas
         $("#lista-alimentos2").empty().append(opcionesFiltradas);
     });

     // Restaurar todas las opciones del select cuando se cierre
     $("#lista-alimentos2").on("hidden.bs.select", function () {
         $("#lista-alimentos2").empty().append(opciones);
     });

     $("#lista-alimentos2").keydown("hidden.bs.select", function () {
         $("#lista-alimentos2").empty().append(opciones);
     });

    // Obtener los elementos de la lista de materiales
    var materiales = $('.material');

    // Manejar la presentación de resultados cuando se envía el formulario
    $('#search-form').keyup(function (event) {
        event.preventDefault();
        var busqueda = $('#search-input').val().toLowerCase();

        // Filtrar la lista de materiales para obtener los resultados
        var resultados = materiales.filter(function () {
            var descripcion = $(this).find('.cover').text().toLowerCase();
            return descripcion.includes(busqueda);
        });

        // Ocultar todos los elementos de la lista de materiales y mostrar solo los resultados
        materiales.hide();
        resultados.show();

    });

    // Mostrar todos los elementos de la lista de materiales al cargar la página
    materiales.show();

    // Obtener la lista de opciones del select
    var opciones = $("#lista-alimentos1 option");

    // Crear un input de búsqueda
    var inputBusqueda = $('<input type="text" class="form-control" placeholder="Buscar...">');

    // Agregar el input de búsqueda antes del select
    $("#lista-alimentos1").before(inputBusqueda);

    // Escuchar el evento keyup del input de búsqueda
    inputBusqueda.keyup(function () {
        // Obtener el término de búsqueda
        var term = $(this).val().toLowerCase();

        // Filtrar las opciones que contengan el término de búsqueda
        var opcionesFiltradas = opciones.filter(function () {
            return $(this).text().toLowerCase().indexOf(term) > -1;
        });

        // Actualizar las opciones del select con las opciones filtradas
        $("#lista-alimentos1").empty().append(opcionesFiltradas);
    });

    // Restaurar todas las opciones del select cuando se cierre
    $("#lista-alimentos1").on("hidden.bs.select", function () {
        $("#lista-alimentos1").empty().append(opciones);
    });

    $("#lista-alimentos1").keydown("hidden.bs.select", function () {
        $("#lista-alimentos1").empty().append(opciones);
    });

    var recetas = $('.material');
    var noResultadosBtn = $('#no-resultados-btn');
    var want = [];
    var dontWant = [];
    var wantI = [];
    var dontWantI = [];

    $('#filter-button').click(function () {
        want = $('#want-select option:selected').map(function () { return $(this).text().toLowerCase(); }).get();
        dontWant = $('#dont-want-select option:selected').map(function () { return $(this).text().toLowerCase(); }).get();
        wantI = $('#want-select').val().map(function (val) { return val.toLowerCase(); });
        dontWantI = $('#dont-want-select').val().map(function (val) { return val.toLowerCase(); });

        var resultados = recetas.filter(function () {
            var ingredients = $(this).find('.ingredientes').text().toLowerCase();
            return want.some(function (want) { return ingredients.includes(want); }) &&
                !dontWant.some(function (dontWant) { return ingredients.includes(dontWant); });

        });

        recetas.hide();
        resultados.show();

        noResultadosBtn.toggle(resultados.length === 0);
    });

    $('#no-resultados-btn').click(function () {
        var url = '/recetasparecidas?want=' + wantI.join() + '&dontWant=' + dontWantI.join();
        window.location.href = url;
    });

    // Mostrar todas las recetas al cargar la página
    recetas.show();
});


function validatePDF(input) {
    if (input.files && input.files[0]) {
        const file = input.files[0];
        const reader = new FileReader();
        reader.onload = function (e) {
            if (e.target.result.startsWith("data:application/pdf")) {
            } else {
                input.value = null; // Limpiar el campo de entrada de archivo
                alert("Por favor, seleccione un archivo PDF");
            }
        }
        reader.readAsDataURL(file);
    }
}


function abrirIframe(modalId) {
    const modal = new bootstrap.Modal(document.getElementById(modalId));
    modal.show();
}
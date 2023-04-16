$(document).ready(function () {
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
  });
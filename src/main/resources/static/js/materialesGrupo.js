function abrirIframe(modalId) {
    const modal = new bootstrap.Modal(document.getElementById(modalId));
    modal.show();
}

$(document).ready(function () {
    (function ($) {
        $('#filtrarMateriales').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarParticipantes tr').hide();
            $('.buscarParticipantes tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        })
    }(jQuery));

    $('.eliminar').click(function () {
        var id = $(this).data('id');

        Swal.fire({
            position: 'center',
            title: '<h4>¿Estás seguro de eliminar el material?</h4>',
            showConfirmButton: true,
            showCancelButton: true,
            cancelButtonText: 'Cancelar',
            confirmButtonColor: "rgb(218, 77, 73)",
            confirmButtonText: '<a href="/eliminarMaterialGrupo/' + id + '" id ="conf">Eliminar</a>',

            didRender: function () {
                const confirm = document.querySelector('#conf');

                if (confirm) {
                    confirm.style.color = 'white';
                }
            },

            showClass: {
                popup: 'animate__animated animate__fadeInDown'
            },
            hideClass: {
                popup: 'animate__animated animate__fadeOutUp'
            }

        })

    });

});
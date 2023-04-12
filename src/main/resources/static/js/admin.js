$(document).ready(function () {

    (function ($) {
        $('#filtrarUsuarios').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarUsuarios tr').hide();
            $('.buscarUsuarios tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        })
    }(jQuery));

    function habilitarEdicion(row) {
        row.find('.edit-button').prop('disabled', true);
        row.find('.cancel-button').show();
        row.find('.fa-user-pen').removeClass('fa-user-pen').addClass('fa-save');
        row.find('.editable').prop('contenteditable', true).addClass('border border-secondary rounded');
        row.find('.editable-state').replaceWith('<select class="form-select editable-state"><option value="ALTA">ALTA</option><option value="BAJA">BAJA</option></select>');
    }

    function guardarEdicion(row) {

        var nickname = row.find('.editable:eq(0)').text();
        var email = row.find('.editable:eq(1)').text();
        var id = row.find('#eliminar').attr('data-id');
        var state = row.find('.editable-state').val();

        Swal.fire({
        position: 'center',
        title: '<h4>¿Estás seguro de editar el usuario?</h4>',
        showConfirmButton: true,
        showCancelButton: true,
        cancelButtonText: 'Cancelar',
        confirmButtonColor: "rgb(218, 77, 73)",
        confirmButtonText: '<a href="/actualizar-usuario/' + id + '?estado=' + state + '&nickname=' + nickname + '&email=' + email + '" id ="conf">Editar</a>',



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
    }

    function cancelarEdicion(row) {
        window.location.reload();
    }

    $(document).on('click', '.edit-button', function () {
        var row = $(this).closest('tr');
        habilitarEdicion(row);
    });

    $(document).on('click', '.fa-save', function () {
        var row = $(this).closest('tr');
        guardarEdicion(row);
    });

    $(document).on('click', '.cancel-button', function () {
        var row = $(this).closest('tr');
        cancelarEdicion(row);
    });

    $('#eliminar').click(function () {
        var id = $(this).data('id');

        Swal.fire({
            position: 'center',
            title: '<h4>¿Estás seguro de eliminar el usuario?</h4>',
            showConfirmButton: true,
            showCancelButton: true,
            cancelButtonText: 'Cancelar',
            confirmButtonColor: "rgb(218, 77, 73)",
            confirmButtonText: '<a href="/eliminarUsuario/' + id + '" id ="conf">Eliminar</a>',

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
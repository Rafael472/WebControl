$('#exclusaoModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var codigoUsuario = button.data('codigo');
	var descricaoUsuario = button.data('usuario');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.attr('action');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + codigoUsuario);
	
	modal.find('.modal-body').html('Tem certeza que deseja excluir o usuário <strong>' + descricaoUsuario + '</strong>?');
});

$(function() {
	$('[rel="tooltip"]').tooltip();
});

$(document).ready(function() {
    $('#table').DataTable();
} );
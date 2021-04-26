$(function() {

	$('#exclusaoModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		
		var codigoProduto = button.data('codigo');
		var descricaoProduto = button.data('produto');
		
		var modal = $(this);
		var form = modal.find('form');
		var action = form.data('url');
		if (!action.endsWith('/')) {
			action += '/';
		}
		form.attr('action', action + codigoProduto);
		
		modal.find('.modal-body').html('Tem certeza que deseja excluir o produto <strong>' + descricaoProduto + '</strong>?');
	});
	
	$(function() {
		$('[rel="tooltip"]').tooltip();
	});
	
	$(document).ready(function() {
	    $('#table').DataTable();
	} );
	
});
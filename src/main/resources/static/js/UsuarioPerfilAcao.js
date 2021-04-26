$(function() {
	
	var url_atual = window.location.href;
	var page = url_atual.includes("/perfis/");
	if(!page)
		return;

	$('.js-atualizar-situacao').on('click', function(event) {
		event.preventDefault();
		
		var botao = $(event.currentTarget);
		var url = botao.attr('href');
		
		var acao = botao.data('acao');
		
		var response = $.ajax({
			url: url,
			type: 'PUT'
		});
	
		var botaoInativo;
		
		response.done(function(e) {
			var perfilCodigo = botao.data('codigo');
			if(acao == 'ATIVAR'){
				$('[data-role=' + perfilCodigo + ']').html('<span class="badge  bg-success text-white">Ativo</span>');
				botaoInativo = $('#inativar_' + perfilCodigo);
			}
			else if(acao == 'INATIVAR') {
				$('[data-role=' + perfilCodigo + ']').html('<span class="badge  bg-danger text-white">Inativo</span>');
				botaoInativo = $('#ativar_' + perfilCodigo);
			}
			
			$("#"+botao[0].id).hide();
			$("#"+botaoInativo[0].id).show();
		});
		
		response.fail(function(e) {
			console.log(e);
			alert('Erro na operação');
		});
	});
});

$(document).ready(function() {
    $('#table').DataTable();
} );

$('#btnConcluir').on('click', function() {
	window.location.pathname = "/usuario";
});
$(function() {
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
				botaoInativo = $('#ativar_' + perfilCodigo);
			}
			else if(acao == 'INATIVAR') {
				$('[data-role=' + perfilCodigo + ']').html('<span class="badge  bg-danger text-white">Inativo</span>');
				botaoInativo = $('#inativar_' + perfilCodigo);
			}
			
			console.log("antes do hide");
			botao.hide();
			console.log("antes do show");
			botaoInativo.show();
			console.log("depois do show");
		});
		
		response.fail(function(e) {
			console.log(e);
			alert('Erro na operação');
		});
	});
});
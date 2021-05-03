$(function() {
	$('#txtQuantidade').change(function() {
		var quantidade = $("#txtQuantidade").val();
		quantidade = quantidade.replace(".","");
		quantidade = quantidade.replace(".","");
		quantidade = quantidade.replace(",", "");
		
		if(/^\d+$/.test(quantidade)){
			$('#txtQuantidade').removeClass();
			$('#txtQuantidade').addClass('form-control form-control-sm');
			return true;
		}else {
			$('#txtQuantidade').addClass('is-invalid');
		}
	});
	
	$('#txtValor').change(function() {
		var valor = $("#txtValor").val();
		valor = valor.replace(".","");
		valor = valor.replace(".","");
		valor = valor.replace(",", "");
		
		if(/^\d+$/.test(valor)){
			$('#txtValor').removeClass();
			$('#txtValor').addClass('form-control form-control-sm');
			return true;
		}else {
			$('#txtValor').addClass('is-invalid');
		}
	});
});

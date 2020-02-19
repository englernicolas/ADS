COLDIGO = new Object();

$(document).ready(function(){
	COLDIGO.PATH = "/coldigo/rest/";
	
	$("header").load("/coldigo/pages/admin/general/header.html");
	$("footer").load("/coldigo/pages/admin/general/footer.html");

	COLDIGO.carregaPagina = function(pagename){
		if ($(".ui-dialog")){
			$(".ui-dialog").remove();
		}

		$("section").empty();
		$("section").load(pagename+"/",function(response, status, info){ 
			if (status == "error") {
				var msg = "Houve um erro ao encontrar a página: " + info.status + " - " + info.statusText;
				$("section").html(msg);
			}
		});
		
	}
	
	COLDIGO.exibirAviso = function(aviso){
		var modal = {
				title: "Mensagem",
				height: 250,
				width: 400,
				modal: true,
				buttons: {
					"OK": function(){
						$(this).dialog("close");
					}
				}
		};
		$("#modalAviso").html(aviso);
		$("#modalAviso").dialog(modal);
	};

	// vai formatar o valor recebido como parametro
	// exemplo: 4000 vai se transformar em 4.000,00
	// toFixed() coloca a quantidado de casas decimais passado como parametro
	// replace() procura o primeiro valor e substitui pelo segundo
	COLDIGO.formatarDinheiro = function (valor) {
		return valor.toFixed(2).replace('.',',').replace(/(\d)(?=(\d{3})+\,)/g, "$1.");
	}
});
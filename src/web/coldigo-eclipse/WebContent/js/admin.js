COLDIGO = new Object();

$(document).ready(function(){
	$("header").load("/coldigo-eclipse/pages/admin/general/header.html");
	$("footer").load("/coldigo-eclipse/pages/admin/general/footer.html");

	COLDIGO.carregaPagina = function(pagename){
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
});
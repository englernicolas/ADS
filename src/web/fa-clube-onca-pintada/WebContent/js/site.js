function validaFormulario() {
    var nome = document.frminscricao.txtnome.value;
    var expRegNome = new RegExp("^[A-zÀ-ü]{3,}([ ]{1}[A-zÀ-ü]{2,})+$");

    if (!expRegNome.test(nome)){
        alert("Preencha o campo Nome corretamente.");
        document.frminscricao.txtnome.focus();
        return false;
    }

    var email = document.frminscricao.email.value;
    var expRegEmail = new RegExp("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");

    if (!expRegEmail.test(email)){
        alert("Preencha o campo E-mail corretamente");
        document.frminscricao.email.focus();
        return false;
    }

    var fone = document.frminscricao.txtfone.value;
    var expRegFone = new RegExp("^[(]{1}[1-9]{2}[)]{1}[0-9]{4,5}[-]{1}[0-9]{4}$");

    if (!expRegFone.test(fone)){
        alert("Preencha o campo Telefone corretamente");
        document.frminscricao.txtfone.focus();
        return false;

    }


    var dataInserida = document.frminscricao.datanasc.value;

    if (dataInserida==""){
        alert("Preencha o campo Data de Nascimento");
        return false;
    }

    var sexo = document.frminscricao.sexo.value;
    if (sexo == ""){
        alert("Preencha o campo Sexo");
        return false;
    }

    if (document.frminscricao.checkboxparticipar.checked==false){
        alert("Marque a caixa 'Desejo participar do fã-clube'");
        return false;
    }

    return true
}

$(document).ready(function() {
    $("header").load("/fa_clube_onca_pintada/WebContent/pages/site/general/cabecalho.html");
    $("footer").load("/fa_clube_onca_pintada/WebContent/pages/site/general/rodape.html");
});
function validarFormulario(){
    var nome = document.getElementById("nome").value;
    var expRegNome = new RegExp("^[A-zÀ-ü]{3,}([ ]{1}[A-zÀ-ü]{2,})+$");

    if (!expRegNome.test(nome)){
        alert("Preencha o campo Nome corretamente.");
        return false;
    }

    var dataInserida = document.getElementById("entrega").value;

    if (dataInserida==""){

        alert("Preencha o campo Entrega");
        return false;
    }

    var tipoAlimento = document.getElementById("tipoAlimento").value;

    if (tipoAlimento == "escolha"){
        alert("Escolha alguma opção");
        return false;
    }

    var quantidade = document.getElementById("quantidade").value;

    if (quantidade == ""){
        alert("Preencha o campo Quantidade");
        return false;
    }

    var fone = document.getElementById("telefone").value;
    var expRegFone = new RegExp("^[(]{1}[1-9]{2}[)]{1}[0-9]{4,5}[-]{1}[0-9]{4}$");

    if (!expRegFone.test(fone)){
        alert("Preencha o campo Telefone corretamente");
        return false;

    }

    return true;
}
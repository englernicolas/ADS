COLDIGO.marca = {};

$(document).ready(function () {
        COLDIGO.marca.cadastrar = function () {
            var marca = {};
            marca.nome = document.frmAddMarca.nome.value;

            if (marca.nome == "") {
                COLDIGO.exibirAviso("Preencha todos os campos!");
            } else {
                console.log(COLDIGO.PATH)
                $.ajax({
                    type: "POST",
                    url: COLDIGO.PATH + "marca/inserir",
                    data: JSON.stringify(marca),
                    success: function (msg) {
                        COLDIGO.exibirAviso(msg);
                        $("#addMarca").trigger("reset");
                        COLDIGO.marca.buscar();
                    },
                    error: function (info) {
                        COLDIGO.exibirAviso("Erro ao cadastar marca: " + info.status + " - " + info.statusText);
                    }
                })

            }
        };

        COLDIGO.marca.exibir = function (listaDeMarcas) {
            var tabela = "<table class='tabelaMarcas'>" +
                "<tr>" +
                "<th>Nome</th>" +
                "<th class='acoes'>Ações</th>" +
                "</tr>";

            if (listaDeMarcas != undefined && listaDeMarcas.length > 0) {
                for (var i = 0; i < listaDeMarcas.length; i++) {
                    tabela += "<tr>" +
                        "<td>" + listaDeMarcas[i].nome + "</td>" +
                        "<td>" +
                        "<a onclick=\"COLDIGO.marca.exibirEdicao('" + listaDeMarcas[i].id + "')\"><img src='../../imgs/edit.png' alt='Editar registro'></a>" +
                        "<a onclick=\"COLDIGO.marca.confirmarExclusao('" + listaDeMarcas[i].id + "')\"><img src='../../imgs/delete.png' alt='Excluir registro'></a>" +
                        "</td>" +
                        "</tr>"
                }
            } else if (listaDeMarcas == "") {
                tabela += "<tr><td colspan='2'>Nenhum registro encontrado</td></tr>"
            }
            tabela += "</table>";

            return tabela;
        };

        COLDIGO.marca.buscar = function () {
            var valorBusca = $("#campoBuscaMarca").val();

            $.ajax({
                type: "GET",
                url: COLDIGO.PATH + "marca/buscar",
                data: "valorBusca=" + valorBusca,
                success: function (dados) {
                    dados = JSON.parse(dados);
                    $("#listaMarcas").html(COLDIGO.marca.exibir(dados));
                },
                error: function (info) {
                    COLDIGO.exibirAviso("Erro ao buscar marca: " + info.status + " - " + info.statusText);
                }
            })
        };

        COLDIGO.marca.buscar();

        COLDIGO.marca.confirmarExclusao = function (id) {
            var modalExcluirMarca = {
                title: "Deseja realmente excluir esta marca?",
                width: 550,
                modal: true,
                buttons: {
                    "Sim": function () {
                        COLDIGO.marca.excluir(id);
                        $(this).dialog("close");
                    },
                    "Não": function () {
                        $(this).dialog("close");
                    }
                },
                close: function () {
                }
            };

            $("#modalExcluirMarca").dialog(modalExcluirMarca);
        };

        COLDIGO.marca.excluir = function (id) {
            $.ajax({
                type: "DELETE",
                url: COLDIGO.PATH + "marca/excluir/" + id,
                success: function (msg) {
                    COLDIGO.exibirAviso(msg);
                    COLDIGO.marca.buscar()
                },
                error: function (info) {
                    COLDIGO.exibirAviso("Erro ao excluir marca: " + info.status + " - " + info.statusText);
                }

            });
        };

        COLDIGO.marca.exibirEdicao = function (id) {
            $.ajax({
                type: "GET",
                url: COLDIGO.PATH + "marca/buscarPorId",
                data: "id=" + id,
                success: function (marca) {
                    document.frmEditarMarca.idMarca.value = marca.id;
                    document.frmEditarMarca.nome.value = marca.nome;

                    var modalEditaMarca = {
                        title: "Editar marca",
                        height: 400,
                        width: 550,
                        modal: true,
                        buttons: {
                            "Salvar": function () {
                                COLDIGO.marca.editar();
                            },
                            "Cancelar": function () {
                                $(this).dialog("close");
                            }
                        },
                        close: function () {
                        }
                    };

                    $("#modalEditaMarca").dialog(modalEditaMarca);
                },
                error: function (info) {
                    COLDIGO.exibirAviso("Erro ao buscar marca para edição: " + info.status + " - " + info.statusText);
                }
            });
        };

        COLDIGO.marca.editar = function () {
            var marca = {};
            marca.id = document.frmEditarMarca.idMarca.value;
            marca.nome = document.frmEditarMarca.nome.value;

            $.ajax({
                type: "PUT",
                url: COLDIGO.PATH + "marca/alterar",
                data: JSON.stringify(marca),
                success: function (msg) {
                    COLDIGO.exibirAviso(msg);
                    COLDIGO.marca.buscar();
                    $("#modalEditaMarca").dialog("close")
                },
                error: function (info) {
                    COLDIGO.exibirAviso("Erro ao editar marca: " + info.status + " - " + info.statusText);
                }
            })
        };
    }
);
COLDIGO.produto = new Object();

$(document)
    .ready(
        function () {
            var listaProdutos = {};

            COLDIGO.produto.carregarMarcas = function (id) {
                if (id != undefined) {
                    select = "#selMarcaEdicao";
                } else {
                    select = "#selMarca";
                }
                $
                    .ajax({
                        type: "GET",
                        url: COLDIGO.PATH + "marca/carregarMarcas",
                        success: function (marcas) {
                            if (marcas != "") {
                                $(select).html("");
                                var option = document
                                    .createElement("option");
                                option.setAttribute("value", "");
                                option.innerHTML = ("Escolha");
                                $(select).append(option);
                                for (var i = 0; i < marcas.length; i++) {
                                    var option = document
                                        .createElement("option");
                                    option.setAttribute("value",
                                        marcas[i].id);

                                    if ((id != undefined) && (id == marcas[i].id))
                                        option.setAttribute("selected", "selected");

                                    option.innerHTML = (marcas[i].nome);
                                    $(select).append(option);
                                }
                            } else {
                                $(select).html("");
                                var option = document
                                    .createElement("option");
                                option.setAttribute("value", "");
                                option.innerHTML = ("Cadastre uma marca primeiro!");
                                $(select).append(option);
                                $(select).addClass("aviso");
                            }
                        },
                        error: function (info) {
                            COLDIGO
                                .exibirAviso("Erro ao buscar as marcas: "
                                    + info.status
                                    + " - "
                                    + info.statusText);

                            $(select).html("");
                            var option = document
                                .createElement("option");
                            option.setAttribute("value", "");
                            option.innerHTML = ("Erro ao carregar marcas!");
                            $(select).append(option);
                            $(select).addClass("aviso");
                        }
                    });
            };

            COLDIGO.produto.carregarMarcas();

            // Cadastra no BD o produto informado

            COLDIGO.produto.cadastrar = function () {
                var produto = new Object();
                produto.categoria = document.frmAddProduto.categoria.value;
                produto.marcaId = document.frmAddProduto.marcaId.value;
                produto.modelo = document.frmAddProduto.modelo.value;
                produto.capacidade = document.frmAddProduto.capacidade.value;
                produto.valor = document.frmAddProduto.valor.value;

                if ((produto.categoria == "")
                    || (produto.marcaId == "")
                    || (produto.modelo == "")
                    || (produto.capacidade == "")
                    || (produto.valor == "")) {
                    COLDIGO.exibirAviso("Preencha todos os campos!")
                } else if (COLDIGO.produto.verificarNome(produto.modelo, produto.marcaId)) {
                    COLDIGO.exibirAviso("Um produto dessa marca com esse nome já foi cadastrada!");
                } else {
                    $.ajax({
                        type: "POST",
                        url: COLDIGO.PATH + "produto/inserir",
                        data: JSON.stringify(produto),
                        success: function (msg) {
                            COLDIGO.exibirAviso(msg);
                            $("#addProduto").trigger("reset");
                            COLDIGO.produto.buscar();
                        },
                        error: function (info) {
                            COLDIGO.exibirAviso("Erro ao cadastrar um novo produto: " + info.status + " - " + info.statusText);
                        }
                    });
                }
            };

            COLDIGO.produto.exibir = function (listaDeProdutos) {
                var tabela = "<table>" +
                    "<tr>" +
                    "<th>Categoria</th>" +
                    "<th>Marca</th>" +
                    "<th>Modelo</th>" +
                    "<th>Cap.(l)</th>" +
                    "<th>Valor</th>" +
                    "<th class='acoes'>Ações</th>" +
                    "</tr>";

                if (listaDeProdutos != undefined && listaDeProdutos.length > 0) {
                    for (var i = 0; i < listaDeProdutos.length; i++) {
                        tabela += "<tr>" +
                            "<td>" + listaDeProdutos[i].categoria + "</td>" +
                            "<td>" + listaDeProdutos[i].marcaNome + "</td>" +
                            "<td>" + listaDeProdutos[i].modelo + "</td>" +
                            "<td>" + listaDeProdutos[i].capacidade + "</td>" +
                            "<td>R$ " + COLDIGO.formatarDinheiro(listaDeProdutos[i].valor) + "</td>" +
                            "<td>" +
                            "<a onclick=\"COLDIGO.produto.exibirEdicao('" + listaDeProdutos[i].id + "')\"><img src='../../imgs/edit.png' alt='Editar registro'></a>" +
                            "<a onclick=\"COLDIGO.produto.confirmarExclusao('" + listaDeProdutos[i].id + "')\"><img src='../../imgs/delete.png' alt='Excluir registro'></a>" +
                            "</td>" +
                            "</tr>"
                    }
                } else if (listaDeProdutos == "") {
                    tabela += "<tr><td colspan='6'>Nenhum registro encontrado</td></tr>"
                }
                tabela += "</table>";

                return tabela;
            };

            COLDIGO.produto.buscar = function () {
                var valorBusca = $("#campoBuscaProduto").val();

                $.ajax({
                    type: "GET",
                    url: COLDIGO.PATH + "produto/buscar",
                    data: "valorBusca=" + valorBusca,
                    success: function (dados) {
                        dados = JSON.parse(dados)
                        $("#listaProdutos").html(COLDIGO.produto.exibir(dados));
                    },
                    error: function (info) {
                        COLDIGO.exibirAviso("Erro ao consultar os contatos: " + info.status + " = " + info.statusText);
                    }
                });
            };

            COLDIGO.produto.buscar();

            COLDIGO.produto.confirmarExclusao = function (id) {
                var modalExcluirProduto = {
                    title: "Deseja realmente excluir este produto?",
                    width: 550,
                    modal: true,
                    buttons: {
                        "Sim": function () {
                            COLDIGO.produto.excluir(id);
                            $(this).dialog("close");
                        },
                        "Não": function () {
                            $(this).dialog("close");
                        }
                    },
                    close: function () {
                    }
                };

                $("#modalExcluirProduto").dialog(modalExcluirProduto);
            };

            COLDIGO.produto.excluir = function (id) {
                $.ajax({
                    type: "DELETE",
                    url: COLDIGO.PATH + "produto/excluir/" + id,
                    success: function (msg) {
                        COLDIGO.exibirAviso(msg);
                        COLDIGO.produto.buscar()
                    },
                    error: function (info) {
                        COLDIGO.exibirAviso("Erro ao excluir produto: " + info.status + " - " + info.statusText);
                    }

                });
            };

            COLDIGO.produto.exibirEdicao = function (id) {
                $.ajax({
                    type: "GET",
                    url: COLDIGO.PATH + "produto/buscarPorId",
                    data: "id=" + id,
                    success: function (produto) {
                        document.frmEditarProduto.idProduto.value = produto.id;
                        document.frmEditarProduto.modelo.value = produto.modelo;
                        document.frmEditarProduto.capacidade.value = produto.capacidade;
                        document.frmEditarProduto.valor.value = produto.valor;

                        var selCategoria = document.getElementById('selCategoriaEdicao');
                        for (var i = 0; i < selCategoria.length; i++) {
                            if (selCategoria.options[i].value == produto.categoria) {
                                selCategoria.options[i].setAttribute("selected", "selected");
                            } else {
                                selCategoria.options[i].removeAttribute("selected");
                            }
                        }

                        COLDIGO.produto.carregarMarcas(produto.marcaId);

                        var modalEditaProduto = {
                            title: "Editar Produto",
                            height: 400,
                            width: 550,
                            modal: true,
                            buttons: {
                                "Salvar": function () {
                                    COLDIGO.produto.editar();
                                },
                                "Cancelar": function () {
                                    $(this).dialog("close");
                                }
                            },
                            close: function () {
                            }
                        };

                        $("#modalEditaProduto").dialog(modalEditaProduto);
                    },
                    error: function (info) {
                        COLDIGO.exibirAviso("Erro ao buscar produto para edição: " + info.status + " - " + info.statusText);
                    }
                });
            };

            COLDIGO.produto.editar = function () {
                var produto = new Object();
                produto.id = document.frmEditarProduto.idProduto.value;
                produto.categoria = document.frmEditarProduto.categoria.value;
                produto.marcaId = document.frmEditarProduto.marcaId.value;
                produto.modelo = document.frmEditarProduto.modelo.value;
                produto.capacidade = document.frmEditarProduto.capacidade.value;
                produto.valor = document.frmEditarProduto.valor.value;

                $.ajax({
                    type: "PUT",
                    url: COLDIGO.PATH + "produto/alterar",
                    data: JSON.stringify(produto),
                    success: function (msg) {
                        COLDIGO.exibirAviso(msg);
                        COLDIGO.produto.buscar();
                        $("#modalEditaProduto").dialog("close")
                    },
                    error: function (info) {
                        COLDIGO.exibirAviso("Erro ao editar produto: " + info.status + " - " + info.statusText);
                    }
                })
            };
            COLDIGO.produto.listarProdutos = function () {
                $.ajax({
                    type: "GET",
                    url: COLDIGO.PATH + "produto/listarProdutos",
                    success: function (dados) {
                        dados = JSON.parse(dados);
                        listaProdutos = dados;
                    },
                    error: function (info) {
                        console.log("Erro ao criar lista de produtos: " + info.status + " - " + info.statusText);
                    }
                });
            };

            COLDIGO.produto.verificarNome = function (modeloProduto, marcaId) {
                COLDIGO.produto.listarProdutos();
                for (var i = 0; i < listaProdutos.length; i++){
                    debugger;
                    if ((marcaId == listaProdutos[i].marcaId) && modeloProduto.toLowerCase() == listaProdutos[i].modelo.toLowerCase()){
                        return true;
                    }
                }
                return false;
            };
        });
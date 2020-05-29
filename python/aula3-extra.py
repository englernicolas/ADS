def pesquisar(nome) :
    if(nome in listaNome) :
        posicao = listaNome.index(nome)
        print("------------------------------")
        print("Nome: ", listaNome[posicao], listaSobrenome[posicao])
        print("Teefone: ",listaFone[posicao])
        print("-------------------------")
    else:
        print("-------------------------")
        print("Pessoa não encontrada")
        print("-------------------------")

def excluir(nome):
    if(nome in listaNome) :
        posicao = listaNome.index(nome)
        listaNome.pop(posicao)
        listaSobrenome.pop(posicao)
        listaFone.pop(posicao)
        print("Exluido com sucesso!")
    else:
        print("-----------------------")
        print("Pessoa não encontrada")
        print("-----------------------")

def listar():
    for item in range(0, len(listaNome)):
        print("-----------------------")
        print(  "Nome: ", listaNome[item, listaSobrenome[item]])
        print("-----------------------")

listaNome = []
listaSobrenome = []
listaFone = []
while True:
    print(" 1 - Cadastrar")
    print(" 2 - Pesquisar")
    print(" 3 - Excluir")
    print(" 4 - Listar todos")
    op = int(input("Digide a opção desejada: "))
    if(op == 1):
        nome = input("Informe o Nome: ")
        sobrenome = input("Informe o Sobrenome: ")
        fone = input("Informe o Telefone: ")
        listaNome.append(nome)
        listaSobrenome.append(sobrenome)
        listaFone.append(fone)
        print("-----------------------")
        print("Cadastrado com Sucesso!")
        print("-----------------------")
    else:
        if(op == 2):
            pesquisa = input("Informe o nome a pesquisar: ")
            pesquisar(pesquisa)
        else:
            if(op == 3):
                pesquisa = input("Informe o nome a excluir: ")
                excluir(pesquisa)
            else:
                if(op == 4):
                    listar()
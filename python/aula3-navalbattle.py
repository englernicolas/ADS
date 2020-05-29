'''
Utilizando o código da batalha naval anterior, faça as seguintes alterações:

Aumente o mapa para 10 x 10!

Faça o jogo com 7 navios ao invés de 5!

Aumente o número de jogadas para 15!

Teste se ele digitou um número de linha ou coluna válido!

Teste utilizando função, se ele já jogou aquela posição e peça nova linha e coluna!

Se ele vencer, no final mostre para ele quantas jogadas foram necessárias!
'''

import random

def validarJogada(mapa, coluna, linha):
    if mapa[linha][coluna] != "~":
        return False
    
    return True

mapa = [["~","~","~","~","~","~","~","~","~","~"],
        ["~","~","~","~","~","~","~","~","~","~"],
        ["~","~","~","~","~","~","~","~","~","~"],
        ["~","~","~","~","~","~","~","~","~","~"],
        ["~","~","~","~","~","~","~","~","~","~"],
        ["~","~","~","~","~","~","~","~","~","~"],
        ["~","~","~","~","~","~","~","~","~","~"],
        ["~","~","~","~","~","~","~","~","~","~"],
        ["~","~","~","~","~","~","~","~","~","~"],
        ["~","~","~","~","~","~","~","~","~","~"]
        ]

gabarito = [[0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0]
            ]

contadorNavios = 1
while contadorNavios <= 7 :
    linhaNavio = random.randint(0,9)
    colunaNavio = random.randint(0,9)
    if gabarito[linhaNavio][colunaNavio] == 0 :
        gabarito[linhaNavio][colunaNavio] = 1
        contadorNavios += 1
        print(linhaNavio+1,", ",colunaNavio+1)

contadorJogadas = 1
contadorAcertos = 0
while contadorJogadas <= 15 and contadorAcertos < 7:

    print("jogada: ",str(contadorJogadas),"/ 15 - acertos",str(contadorAcertos),"/ 7")
    print("")
    print("          1 2 3 4 5 6 7 8 9 10")
    print("       1 ",mapa[0][0],mapa[0][1],mapa[0][2],mapa[0][3],mapa[0][4],mapa[0][5],mapa[0][6],mapa[0][7],mapa[0][8],mapa[0][9])
    print("       2 ",mapa[1][0],mapa[1][1],mapa[1][2],mapa[1][3],mapa[1][4],mapa[1][5],mapa[1][6],mapa[1][7],mapa[1][8],mapa[1][9])
    print("       3 ",mapa[2][0],mapa[2][1],mapa[2][2],mapa[2][3],mapa[2][4],mapa[2][5],mapa[2][6],mapa[2][7],mapa[2][8],mapa[2][9])
    print("       4 ",mapa[3][0],mapa[3][1],mapa[3][2],mapa[3][3],mapa[3][4],mapa[3][5],mapa[3][6],mapa[3][7],mapa[3][8],mapa[3][9])
    print("       5 ",mapa[4][0],mapa[4][1],mapa[4][2],mapa[4][3],mapa[4][4],mapa[4][5],mapa[4][6],mapa[4][7],mapa[4][8],mapa[4][9])
    print("       6 ",mapa[5][0],mapa[5][1],mapa[5][2],mapa[5][3],mapa[5][4],mapa[5][5],mapa[5][6],mapa[5][7],mapa[5][8],mapa[5][9])
    print("       7 ",mapa[6][0],mapa[6][1],mapa[6][2],mapa[6][3],mapa[6][4],mapa[6][5],mapa[6][6],mapa[6][7],mapa[6][8],mapa[6][9])
    print("       8 ",mapa[7][0],mapa[7][1],mapa[7][2],mapa[7][3],mapa[7][4],mapa[7][5],mapa[7][6],mapa[7][7],mapa[7][8],mapa[7][9])
    print("       9 ",mapa[8][0],mapa[8][1],mapa[8][2],mapa[8][3],mapa[8][4],mapa[8][5],mapa[8][6],mapa[8][7],mapa[8][8],mapa[8][9])
    print("       10",mapa[9][0],mapa[9][1],mapa[9][2],mapa[9][3],mapa[9][4],mapa[9][5],mapa[9][6],mapa[9][7],mapa[9][8],mapa[9][9])
    print("")

    linha = int(input("Digite a linha (1 a 10): "))-1
    while linha > 9 or linha < 0:
        linha = int(input("Linha inválida, digite novamente (1 a 10): "))-1
    coluna = int(input("Digite a coluna (1 a 10): "))-1
    while coluna > 9 or coluna < 0:
        coluna = int(input("Coluna inválida, digite novamente (1 a 10): "))-1
    
    jogadaNaoRepetida = validarJogada(mapa, coluna, linha)

    if not jogadaNaoRepetida:
        print("Você já fez uma jogada nessa posição, tente outra")
    else:
        if gabarito[linha][coluna] == 1:
            mapa[linha][coluna] = "x"
            contadorAcertos += 1
            print("Acertou miserávi!")
        else:
            mapa[linha][coluna] = "o"
            print("Errrroooooooooooooooooouuuuuuuuuuuuu")

        if(contadorAcertos == 7):
            print("Parabéns, foram necessárias ",contadorJogadas," jogadas para ganhar o jogo!")
        
        
        contadorJogadas += 1

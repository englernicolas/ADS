'''
Desenvolva um programa que:
Apresente um menu para o usuário com as opções:
Realizar um cálculo
- Solicite 2 números
- Solicite uma operação
- +, -, *, /, **, %
Usar Funções Math
- Solicite 1 número
- Solicite uma operação
- ceil, floor, sqrt, abs
Sair
'''
import math

loop = True
while(loop):
    calcType = input('Calculadora \n1 - Realizar um cálculo\n2 - Usar funções Math\n')

    if calcType == '1':
        firstNumber = float(input('Insira o primeiro número: '))
        secondNumber = float(input('Insira o segundo número: '))
        operator = input('Escolha entre os operadores (1 para +, 2 para -, 3 para *, 4 para /, 5 para %): ')
        if operator == '1':
            result = firstNumber + secondNumber
        elif operator == '2':
            result = firstNumber - secondNumber
        elif operator == '3':
            result = firstNumber * secondNumber
        elif operator == '4':
            if secondNumber != 0:
                result = firstNumber / secondNumber
            else:
                print('segundo número não pode ser 0')
        elif operator == '5':
            result = firstNumber % secondNumber
        else:
            print('Operador inválido')

        print('Resultado: ' + str(result))
    elif calcType == '2':
        number = float(input('Insira um número: '))
        operator = input('Escolha entre os operadores (1 - ceil, 2 - floor, 3 - sqrt, 4 - abs): ')
        if operator == '1':
            result = math.ceil(number)
        elif operator == '2':
            result = math.floor(number)
        elif operator == '3':
            result = math.sqrt(number)
        elif operator == '4':
            result = abs(number)
        else:
            print('Operador inválido')

        print('Resultado: ' + str(result))
    else:
        print('Opção inválida')
    
    wantContinue = input('Deseja continuar? (s/n) ')
    if wantContinue == 'n':
        loop = False
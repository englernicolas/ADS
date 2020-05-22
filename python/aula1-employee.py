employees = []

def registerEmployee():
    employee = {}
    
    employee['lastName'] = input('Insira o sobrenome do empregado: ')
    employee['age'] = int(input('Insira a idade do empregado: '))
    employee['salary'] = float(input('Insira o salário do empregado: R$ '))

    employees.append(employee)

def showEmployees():
    print(employees)

def getEmployeesAverageAge(employeesSumAge, employeesAverageAge):
    for employee in employees:
        employeesSumAge += employee['age']
    employeesAverageAge = employeesSumAge/len(employees)
    showEmployeesAverageAge(employeesAverageAge)

def getEmployeesAverageSalary(employeesSumSalary, employeesAverageSalary):
    for employee in employees:
        employeesSumSalary += employee['salary']
    employeesAverageSalary = employeesSumSalary/len(employees)
    showEmployeesAverageSalary(employeesAverageSalary)
    
def showEmployeesAverageAge(employeesAverageAge):
    print("A média da idade entre os empregados é: " + str(employeesAverageAge))

def showEmployeesAverageSalary(employeesAverageSalary):
    print("A média do salário entre os empregados é: R$ " + str(employeesAverageSalary))

def run():
    employeesAverageAge = 0
    employeesAverageSalary = 0
    employeesSumAge = 0
    employeesSumSalary = 0
    loop = True
    while(loop):
        registerEmployee()
        showEmployees()
        getEmployeesAverageAge(employeesSumAge, employeesAverageAge)
        getEmployeesAverageSalary(employeesSumSalary, employeesAverageSalary)
        wantContinue = input('Deseja continuar? (s/n) ')
        if wantContinue == 'n':
            loop = False

run()
num1 = float(input("Enter first number: "));
num2 = float(input("Enter second number: "));
op = input("Enter operator: ");

match op:
    case "+": 
        print(f"{num1} + {num2} = {num1 + num2}")
    case "-": 
        print(f"{num1} - {num2} = {num1 - num2}")
    case "*": 
        print(f"{num1} * {num2} = {num1 * num2}")
    case "/": 
        print(f"{num1} / {num2} = {num1 / num2}")
    case _:
        print("Invalid operator")
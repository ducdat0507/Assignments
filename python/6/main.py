from datetime import date
import random

class Product():
    def __init__(self, id, name, price, source):
        self.id = random.randint(1, 2 ** 32 - 1)
        self.name = name
        self.price = price
        self.source = source

    def print(self):
        print((self.id, self.name, self.price, self.source))
 
products = []

while True:
    print("Select action:")
    print("1: Add product")
    print("2: List all products")
    print("3: Filter by price")
    print("4: Update product")
    print("5: Delete product")
    print("6: Exit")
    option = input("Select action: ")
    match option: 
        case "1":
            products.append(Product(
                random.randint(1, 2 ** 32 - 1),
                input("Enter name: "),
                float(input("Enter price: ")),
                input("Enter source: ")
            ))
        case "2":
            [p.print() for p in products]
        case "3":
            price = float(input("Enter price: "))
            [p.print() for p in products if p.price >= price]
        case "4":
            pid = float(input("Enter product id: "))
            for p in products:
                if p.id != pid: 
                    continue
                p.name = input("Enter name: ")
                p.price = float(input("Enter price: "))
                p.source = input("Enter source: ")
                break
        case "5":
            pid = float(input("Enter product id: "))
            for p in products:
                if p.id != pid: 
                    continue
                products.remove(p)
                break
        case "6":
            exit()
        case _:
            print("Invalid option")
    print("")


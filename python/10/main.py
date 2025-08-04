
import asyncio
import random


async def add_task(a: int, b: int):
    await asyncio.sleep(random.random() * 2) # simulate heavy task
    print(f"{a} + {b} = {a + b}")

async def subtract_task(a: int, b: int):
    await asyncio.sleep(random.random() * 2) # simulate heavy task
    print(f"{a} - {b} = {a - b}")

async def mutliply_task(a: int, b: int):
    await asyncio.sleep(random.random() * 2) # simulate heavy task
    print(f"{a} * {b} = {a * b}")

async def divide_task(a: int, b: int):
    await asyncio.sleep(random.random() * 2) # simulate heavy task
    if b == 0:
        raise ZeroDivisionError("Can't divide by zero")
    print(f"{a} / {b} = {a / b}")

async def main():
    num1, num2 = 10, 5

    tasks = [
            add_task(num1, num2),
            subtract_task(num1, num2),
            mutliply_task(num1, num2),
            divide_task(num1, num2),
        ]
    tasks = [asyncio.create_task(task) for task in tasks]

    await asyncio.gather(*tasks)
    print("All tasks completed 🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🥳🥳🥳🥳🥳🥳🥳🥳🥳🥳🥳🥳🥳🥳🥳")

asyncio.run(main())
    

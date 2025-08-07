
import asyncio
import random


async def add_task(a: int, b: int):
    await asyncio.sleep(random.random() * 2) # simulate heavy task
    print(f"{a} + {b} = {a + b}")
    return a + b

async def subtract_task(a: int, b: int):
    await asyncio.sleep(random.random() * 2) # simulate heavy task
    print(f"{a} - {b} = {a - b}")
    return a - b

async def mutliply_task(a: int, b: int):
    await asyncio.sleep(random.random() * 2) # simulate heavy task
    print(f"{a} * {b} = {a * b}")
    return a * b

async def divide_task(a: int, b: int):
    await asyncio.sleep(random.random() * 2) # simulate heavy task
    if b == 0:
        print(f"{a} / {b} = Can't divide by zero grrrrr")
        raise ZeroDivisionError("Can't divide by zero")
    print(f"{a} / {b} = {a / b}")
    return a / b

async def main():
    num1, num2 = 10, 0

    tasks = [
            add_task(num1, num2),
            subtract_task(num1, num2),
            mutliply_task(num1, num2),
            divide_task(num1, num2),
        ]
    result = await asyncio.gather(*tasks, return_exceptions=True)
    print(f"Results = {result}")

    print("All tasks completed 🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🥳🥳🥳🥳🥳🥳🥳🥳🥳🥳🥳🥳🥳🥳🥳")

asyncio.run(main())
    

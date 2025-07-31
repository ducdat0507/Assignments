
import os
import random
import uuid


goal_lines = 10_000_000

dir_path = os.path.dirname(os.path.realpath(__file__))
path = os.path.join(dir_path, "lol.txt")
with open(path, "w") as file:
    for i in range(goal_lines):
        file.write(f"{i} {uuid.uuid4()} {'aptech' if random.randint(0, 100) == 0 else ''}\n")
        if i % (goal_lines // 100) == 0:
            print(f"{i} / {goal_lines}")

import os

dir_path = os.path.dirname(os.path.realpath(__file__))
path = os.path.join(dir_path, "lol.txt")

def line_generator():
    with open(path, "r") as file:
        for line in file:
            yield line

for line in line_generator():
    if "aptech" in line:
        print(line, end=None)

from typing import List, Tuple
import curses as c

from entities.student import Student

def draw_table(stdscr: c.window, students: List[Student], offset=0):
    height, width = stdscr.getmaxyx()
    stdscr.addstr(0, 0, 
            f" {'#'.rjust(3)} {'ID'.rjust(10)} {'Name'.ljust(20)} {'Birthday'.ljust(10)} {'Phone No.'.ljust(10)} {'Address'.ljust(20)} Scores".ljust(width),
            c.color_pair(0 * 16 + 8)
        )
    for y in range(0, height - 2):
        index = y + offset
        if len(students) <= index:            
            stdscr.addstr(y + 1, 0, 
                    f"".ljust(width),
                    c.color_pair(15 * 16 + 1)
                )
            continue
        student = students[index]
        scores = " ".join(["{score:3}" for score in student.scores])
        stdscr.addstr(y + 1, 0, 
                f" {index:-6} {student.id} {student.full_name.ljust(20)} {student.birthday.ljust(20)} {student.phone.ljust(20)} {student.address.ljust(20)} {scores}".ljust(width),
                c.color_pair(0 * 16 + 8)
            )
    
def draw_controls(stdscr: c.window, controls: Tuple[str, str]):
    height, width = stdscr.getmaxyx()
    stdscr.addstr(height - 1, 0, "")
    for ctr in controls:
        stdscr.addstr(" " + ctr[0], c.color_pair(6 * 16 + 2))
        stdscr.addstr(" " + ctr[1] + " ", c.color_pair(15 * 16 + 2))
    _, x = stdscr.getyx()
    stdscr.addstr(" " * (width - x - 1), c.color_pair(2))
    stdscr.insch(height - 1, width - 1, " ", c.color_pair(2))
    
def draw_window(stdscr: c.window, top, left, h, w):
    for y in range(top, top + h):
        stdscr.addstr(y, left, " " * w, c.color_pair(8))

def do_choice_window(stdscr: c.window, top, left, *,choices: List[str]):
    h = len(choices) + 2
    w = max(len(choice) for choice in choices) + 4
    draw_window(stdscr, top, left, h, w)
    selected = 0
    while True:
        for idx, choice in enumerate(choices):
            attr = c.color_pair(7 * 16 + 1 if idx == selected else 8)
            stdscr.addstr(top + 1 + idx, left + 1, f" {choice} ".ljust(w - 2), attr)
        key = stdscr.getch()
        if key in (c.KEY_UP, ord('k')):
            selected = (selected - 1) % len(choices)
        elif key in (c.KEY_DOWN, ord('j')):
            selected = (selected + 1) % len(choices)
        elif key in (c.KEY_ENTER, 10, 13):
            return selected
        elif key == 27:  # ESC
            return None
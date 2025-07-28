
from typing import List, Tuple
import curses as c

from entities.student import Student

def draw_table(stdscr: c.window, students: List[Student]):
    height, width = stdscr.getmaxyx()
    stdscr.addstr(0, 0, 
            f" {'ID'.rjust(6)} {'Name'.ljust(20)} {'Birthday'.ljust(10)} {'Phone No.'.ljust(10)} {'Address'.ljust(20)} Scores".ljust(width),
            c.color_pair(c.COLOR_BLUE * 8)
        )
    
def draw_controls(stdscr: c.window, controls: Tuple[str, str]):
    height, width = stdscr.getmaxyx()
    stdscr.addstr(height - 1, 0, "")
    for ctr in controls:
        stdscr.addstr(" " + ctr[0], c.color_pair(2))
        stdscr.addstr(" " + ctr[1] + " ", c.color_pair(3))
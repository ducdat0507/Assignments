
from typing import Dict, List, Tuple
import curses as c
import curses.panel as p

from entities.student import Student
from forms.base import FormItem

def draw_table(stdscr: c.window, students: List[Student], offset=0):
    height, width = stdscr.getmaxyx()
    stdscr.addstr(0, 0, 
            f" {'#'.rjust(3)} {'ID'.rjust(10)} {'Name'.ljust(20)} {'Birthday'.ljust(12)} {'Phone No.'.ljust(12)} {'Address'.ljust(20)} Scores".ljust(width),
            get_color(7, 0)
        )
    for y in range(0, height - 2):
        index = y + offset
        if len(students) <= index:            
            stdscr.addstr(y + 1, 0, 
                    f"".ljust(width),
                    get_color(0, 15)
                )
            continue
        student = students[index]
        scores = " ".join([f"{score:3}" for score in student.scores])
        stdscr.addstr(y + 1, 0, 
                f" {index:-3} {student.student_id:-10} {student.full_name.ljust(20)} {str(student.birthday)[:10].ljust(12)} {student.phone.ljust(12)} {student.address.ljust(20)} {scores}".ljust(width),
                get_color(0, 15)
            )
    
def draw_controls(stdscr: c.window, controls: List[Tuple[str, str]]):
    height, width = stdscr.getmaxyx()
    stdscr.addstr(height - 1, 0, "")
    for ctr in controls:
        stdscr.addstr(" " + ctr[0], get_color(1, 6))
        stdscr.addstr(" " + ctr[1] + " ", get_color(1, 15))
    _, x = stdscr.getyx()
    stdscr.addstr(" " * (width - x - 1), get_color(1, 0))
    stdscr.insch(height - 1, width - 1, " ", get_color(1, 0))

def do_choice_window(stdscr: c.window, top, left, *, choices: List[str]):
    h = len(choices) + 2
    w = max(len(choice) for choice in choices) + 8
    win = stdscr.subwin(h, w, top, left)
    win.box()
    win.refresh()
    selected = 0
    while True:
        for idx, choice in enumerate(choices):
            attr = get_color(0, 7) if idx == selected else get_color(7, 0)
            cursor = "> " if idx == selected else "  "
            win.addstr(1 + idx, 2, f" {cursor}{choice} ".ljust(w - 4), attr)
        win.refresh()
        key = stdscr.getch()
        if key == c.KEY_UP:
            selected = (selected - 1) % len(choices)
        elif key == c.KEY_DOWN:
            selected = (selected + 1) % len(choices)
        elif key in (c.KEY_ENTER, 10, 13):
            del win
            return selected
        elif key == 27: # ESC
            del win
            return None

def do_form(stdscr: c.window, top, left, w=40, *, items: Dict[str, FormItem]):
    h = len(items) * 2 + 1
    win = stdscr.subwin(h, w, top, left)
    win.box()
    win.refresh()
    item_list = list(items.values())
    selected = 0
    def draw_item(idx):
        item_list[idx].draw(win, 1 + idx * 2, 2, 1, w - 4, selected == idx)
    for idx in range(len(items)):
        draw_item(idx)
    win.refresh()
    while True:
        key = stdscr.getch()
        if key == c.KEY_UP:
            last_sel = selected
            selected = (selected - 1) % len(items)
            draw_item(last_sel)
            draw_item(selected)
            win.refresh()
        elif key == c.KEY_DOWN:
            last_sel = selected
            selected = (selected + 1) % len(items)
            draw_item(last_sel)
            draw_item(selected)
            win.refresh()
        elif key in (c.KEY_ENTER, 10, 13):
            result = item_list[selected].on_key(stdscr, key)
            if (result):
                del win
                return result
            else:
                draw_item(selected)
                win.refresh()
        elif key == 27: # ESC
            del win
            return False
        else:
            result = item_list[selected].on_key(stdscr, key)
            draw_item(selected)
        
def get_color(bg, fg):
    return c.color_pair(fg * 16 + bg + 1)
from abc import ABC, abstractmethod
import curses as c
from curses import window
from datetime import date
import unicodedata

from forms.base import FormItem
from utils import get_color

class DateFormItem(FormItem):

    def __init__(self, label="", value=date.today()):
        self.label = label
        self.value = f"{value.year}-{value.month}-{value.day}"

    def draw(self, stdscr: window, y, x, h, w, selected):
        cursor = "_" if selected else ""
        color = get_color(0, 7) if selected else get_color(15, 0)
        stdscr.addstr(y, x, self.label.rjust(13), get_color(7, 0))
        stdscr.addstr(y, x + 15, (self.value[-w+16:] + cursor).ljust(w - 15), color)

    def on_key(self, stdscr: window, key) -> bool:
        if key in (c.KEY_BACKSPACE, ord('\b'), ord('\x7f')):
            self.value = self.value[:-1]
        elif chr(key) in [*[str(i) for i in range(0, 10)], "-"]:
            self.value += chr(key)
        return False
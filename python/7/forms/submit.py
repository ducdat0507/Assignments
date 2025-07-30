from abc import ABC, abstractmethod
import curses as c
from curses import window
import unicodedata

from forms.base import FormItem
from utils import get_color

class SubmitFormItem(FormItem):

    def __init__(self, label="", value="submit"):
        self.label = label
        self.value = value

    def draw(self, stdscr: window, y, x, h, w, selected):
        cursor = "> " if selected else "  "
        color = get_color(0, 7) if selected else get_color(7, 0)
        stdscr.addstr(y, x + 13, cursor + self.label.ljust(w - 15), color)

    def on_key(self, stdscr: window, key):
        return self.value
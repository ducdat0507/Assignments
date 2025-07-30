from abc import ABC, abstractmethod
from curses import window

class FormItem(ABC):

    @abstractmethod
    def draw(self, stdscr: window, y, x, h, w, selected):
        pass

    @abstractmethod
    def on_key(self, stdscr: window, key) -> bool:
        pass
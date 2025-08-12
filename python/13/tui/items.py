from abc import ABC, abstractmethod
import curses as c
from typing import List

from .data import FlexVec2, Rect, Vec2

class GUIItem(ABC): 
    position: FlexVec2
    size: FlexVec2
    anchor: Vec2

    def __init__(self, *, position=FlexVec2(0, 0, 0, 0), size=FlexVec2(0, 0, 1, 1), anchor=Vec2(.5, .5)):
        self.position = position
        self.size = size
        self.anchor = anchor
        super().__init__()
    
    @abstractmethod
    def paint(self, window: c.window, rect: Rect):
        pass

    @abstractmethod
    def on_key(self, key: str):
        pass

    @abstractmethod
    def on_mouse(self, pos_x: int, pos_y: int):
        pass


class Box(GUIItem): 
    children: List[GUIItem]
    
    def paint(self, window: c.window, rect: Rect):
        for child in self.children:
            child_rect = rect.derive(child.position, child.size, child.anchor)
            child.paint(window, child_rect)

    def on_key(self, key: str):
        pass

    def on_mouse(self, pos_x: int, pos_y: int):
        pass

class Window():
    window: c.window
    root: GUIItem

    def __init__(self, root: GUIItem):
        self.root = root

    def paint(self):
        height, width = self.window.getmaxyx()
        self.root.paint(self.window, Rect(0, 0, width, height))

    def start(self):
        # Initialize cursor
        self.window = c.initscr()
        c.noecho()
        c.cbreak()
        self.window.keypad(True)

        # Enable mouse events
        c.mousemask(c.ALL_MOUSE_EVENTS | c.REPORT_MOUSE_POSITION)
        print('\033[?1003h')
        
        # Handle events
        while True:
            char = self.window.getch()
            


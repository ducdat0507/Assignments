import curses as c
from typing import List

from entities.student import Student
from utils import do_choice_window, draw_controls, draw_table, draw_window


class StudentController:
    students: List[Student] = []

    def __init__(self, stdscr: c.window):
        self.stdscr = stdscr

    def start(self):
        def repaint():
            draw_controls(self.stdscr, [
                    ("[`]", "Controls"),
                    ("[↑][↓]", "Move cursor"),
                    ("[Enter]", "Edit row"),
                ])
            draw_table(self.stdscr, self.students)
        repaint()
        while True:
            key = self.stdscr.getch()
            if key == ord('`'):
                self.do_menu()
                repaint()
            else:
                repaint()

    def do_menu(self):
        draw_controls(self.stdscr, [
                ("[↑][↓]", "Move cursor"),
                ("[Enter]", "Select"),
                ("[Esc]", "Close"),
            ])
        option = do_choice_window(self.stdscr, 3, 6, 
                choices=[
                        "Add new student",
                        "Quit",
                    ]
            )
        match option:
            case 1:
                exit(0)
            
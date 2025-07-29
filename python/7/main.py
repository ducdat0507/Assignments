import curses as c

from controllers.student import StudentController


students = []

stdscr = c.initscr()
c.start_color()
c.noecho()
stdscr.keypad(True)
c.cbreak()
c.curs_set(0)
stdscr.clear()

i = 1
c.use_default_colors()
try:
    for a in range(0, 16):
        for b in range(0, 16):
            c.init_pair(i, a, b)
            i += 1
except:
    pass

controller = StudentController(stdscr)
controller.start()


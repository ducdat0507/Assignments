import curses as c

from utils import draw_controls, draw_table


students = []

stdscr = c.initscr()
c.start_color()
c.noecho()
c.cbreak()
stdscr.clear()

i = 1
c.use_default_colors()
try:
    for a in range(0, c.COLORS):
        c.init_pair(i, i - 1, -1)
        i += 1
except:
    pass

def repaint():
    draw_controls(stdscr, [
            ("[`]", "Menu")
        ])
    draw_table(stdscr, students)
    for a in range(1, i):
        stdscr.addstr(str(a), c.color_pair(a))
repaint()
while True:
    key = stdscr.getch()
    match key:
        case _:
            repaint()
            
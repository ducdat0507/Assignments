import curses as c

from typing import Dict, List

from tui.data import FlexVec2
from tui.items import Box, Window
from .entities import Employee, EmployeeRegistration


employees: Dict[int, Employee] = {}
registrations: List[EmployeeRegistration] = []

root = Box(
        position=FlexVec2(0, 0, 0, 0),
        size=FlexVec2(0, 0, 1, 1)
    )
window = Window(root)
c.wrapper(window.start)


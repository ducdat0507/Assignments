import curses as c
from datetime import date, datetime
from typing import List
import matplotlib.pyplot as plot

from entities.student import Student
from forms.date import DateFormItem
from forms.input import InputFormItem
from forms.submit import SubmitFormItem
from utils import do_choice_window, do_form, draw_controls, draw_table


class StudentController:
    students: List[Student] = []
    pointer = 1000001

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
                        "Filter students",
                        "Save to file",
                        "Plot GPA",
                        "Quit",
                    ]
            )
        match option:
            case 0:
                self.do_student_form()
                return
            case 3:
                self.do_gpa_plot()
                return
            case 4:
                exit(0)

    def do_student_form(self, item:Student | None=None):
        is_new = item == None
        if (is_new):
            item = Student(
                    student_id=0,
                    scores=[],
                    first_name="",
                    middle_name="",
                    last_name="",
                    birthday=date.today(),
                    address="",
                    phone="",
                )
        form_items = {
            "first_name": InputFormItem("First name", value=item.first_name),
            "middle_name": InputFormItem("Middle name", value=item.middle_name),
            "last_name": InputFormItem("Last name", value=item.last_name),
            "birthday": DateFormItem("Birthday", value=item.birthday),
            "address": InputFormItem("Address", value=item.address),
            "phone": InputFormItem("Phone number", value=item.phone),
            "scores": InputFormItem("Scores", value=" ".join(item.scores)),
            "submit": SubmitFormItem("Submit"),
        }
        while True:
            option = do_form(self.stdscr, 5, 9, 50, items=form_items)
            self.stdscr.addstr(str(option))
            if not option:
                return
            elif option == "submit":
                try:
                    item.first_name = form_items["first_name"].value
                    item.middle_name = form_items["middle_name"].value
                    item.last_name = form_items["last_name"].value
                    item.birthday = datetime.strptime(form_items["birthday"].value, "%Y-%m-%d")
                    item.address = form_items["address"].value
                    item.phone = form_items["phone"].value
                    item.scores = [int(i) for i in form_items["scores"].value.split(" ")]
                    if is_new:
                        item.student_id = self.pointer
                        self.pointer += 1
                        self.students.append(item)
                    return
                except ValueError as e:
                    self.stdscr.addstr(str(e))

    def do_gpa_plot(self):
        plot.bar([s.full_name for s in self.students], [s.average_score for s in self.students])
        plot.xlabel("Student")
        plot.ylabel("GPA")
        plot.show()
            
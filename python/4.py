from datetime import date

class Student():

    def __init__(self, id, first_name, middle_name, last_name, birthday):
        self.id = id
        self.first_name = first_name
        self.middle_name = middle_name
        self.last_name = last_name
        self.birthday = birthday


    def display_info(self):
        print("id:", self.id)
        print("first_name:", self.first_name)
        print("middle_name:", self.middle_name)
        print("last_name:", self.last_name)
        print("birthday:", self.birthday)

student = Student(1, "A", "A", "A", date(2004, 10, 7))
student.display_info()
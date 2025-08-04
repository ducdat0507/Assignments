from datetime import date

class Student():

    def __init__(self, id, first_name, middle_name, last_name, birthday, scores):
        self.id = id
        self.first_name = first_name
        self.middle_name = middle_name
        self.last_name = last_name
        self.birthday = birthday
        self.scores = scores


    def display_info(self):
        print("id:", self.id)
        print("first_name:", self.first_name)
        print("middle_name:", self.middle_name)
        print("last_name:", self.last_name)
        print("birthday:", self.birthday)
        print("scores:", self.scores)

    @property
    def average_score(self):
        return sum(self.scores) / len(self.scores)

students = [
    Student(1, "A", "A", "C" date(2004, 10, 7), [8.0, 10.0, 7.5]),
    Student(2, "B", "A", "C" date(2004, 10, 7), [7.0, 8.0, 9.5]),
    Student(3, "C", "A", "C" date(2004, 10, 7), [4.0, 5.5, 4.5]),
    Student(4, "D", "A", "C" date(2004, 10, 7), [6.0, 9.0, 7.5]),
    Student(5, "E", "A", "C" date(2004, 10, 7), [5.5, 5.5, 8.0]),
    Student(6, "F", "A", "C" date(2004, 10, 7), [7.0, 9.5, 8.0]),
]

max(students, key = lambda s: s.average_score).display_info()
print("----------")
[x.display_info() for x in students if x.average_score > 8]

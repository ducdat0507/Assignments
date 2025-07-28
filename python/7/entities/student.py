from entities.person import Person

class Student(Person):
    def __init__(self, *, points, **kwargs):
        self.points = points
        self.__init__(**kwargs)

    @property
    def points(self):
        return self._points
    @points.setter
    def set_points(self, value):
        self._points = value
from entities.person import Person

class Student(Person):
    def __init__(self, *, student_id, scores, **kwargs):
        self.student_id = student_id
        self.scores = scores
        super().__init__(**kwargs)

    @property
    def student_id(self):
        return self._student_id
    @student_id.setter
    def student_id(self, value):
        self._student_id = value
    
    @property
    def scores(self):
        return self._scores
    @scores.setter
    def scores(self, value):
        self._scores = value
        
    @property
    def gpa(self):
        return sum(self._scores) / len(self._scores) if self._scores else 0
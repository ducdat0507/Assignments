from entities.person import Person

class Student(Person):
    def __init__(self, *, id, scores, **kwargs):
        self.id = id
        self.scores = scores
        self.__init__(**kwargs)

    @property
    def id(self):
        return self._id
    
    @property
    def scores(self):
        return self._scores
    @scores.setter
    def set_scores(self, value):
        self._scores = value
from datetime import datetime

class Employee:
    def __init__(self, *, id: int, name: str):
        self.id = id
        self.name = name
        pass

class EmployeeRegistration:
    def __init__(self, *, employee_id: int, start_time: datetime, end_time: datetime):
        self.employee_id = employee_id
        self.start_time = start_time
        self.end_time = end_time
        pass

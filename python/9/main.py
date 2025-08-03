from typing import List, Optional
from fastapi import FastAPI, Response
from pydantic import BaseModel, Field


server = FastAPI()

class Student(BaseModel):
    id: int = Field(frozen=True)
    name: str
    scores: List[float]

class PartialStudent(BaseModel):
    name: Optional[str]
    scores: Optional[List[float]]

class NewStudent(BaseModel):
    name: str
    scores: List[float]

students: List[Student] = []
id_counter: int = 1

@server.get("/api/student/")
def get_students():
    return {"students": students}

@server.get("/api/student/{id}")
def get_one_student(id: int, response: Response):
    try:
        student = next(s for s in students if s.id == id)
    except StopIteration:
        response.status_code = 404
        return {"error": "Student not found"}
    
    return {"student": student}

@server.post("/api/student/")
def new_student(student: NewStudent):
    global id_counter
    new_student = Student(
            id=id_counter,
            name=student.name,
            scores=student.scores
        )
    students.append(new_student)
    id_counter += 1
    return {"ok": 1, "student": new_student}

@server.patch("/api/student/{id}")
def edit_student(id: int, new_values: PartialStudent, response: Response):
    try:
        student = next(s for s in students if s.id == id)
    except StopIteration:
        response.status_code = 404
        return {"error": "Student not found"}
    
    if (new_values.name != None): student.name = new_values.name
    if (new_values.scores != None): student.scores = new_values.scores

    return {"ok": 1, "student": student}

@server.delete("/api/student/{id}")
def delete_student(id: int, new_values: PartialStudent, response: Response):
    global students

    old_length = len(students)
    students = [s for s in students if s.id != id]
    new_length = len(students)

    if old_length == new_length:
        response.status_code = 404
        return {"error": "Student not found"}
    return {"ok": 1}

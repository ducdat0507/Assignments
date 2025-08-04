import os
from typing import List, Optional
import dotenv
from fastapi import FastAPI, Response
from pydantic import BaseModel
from sqlmodel import SQLModel, Field, Session, create_engine, select

dotenv.load_dotenv()

class Student(SQLModel, table=True):
    id: int | None = Field(primary_key=True)
    name: str

class PublicStudent(BaseModel):
    id: int | None = Field(primary_key=True)
    name: str

class PartialStudent(BaseModel):
    name: Optional[str]

class NewStudent(BaseModel):
    name: str

students: List[Student] = []
id_counter: int = 1



db_url = os.getenv("DB_URL")

engine = create_engine(db_url, echo=True)

SQLModel.metadata.create_all(engine)


server = FastAPI()

@server.get("/api/student/")
def get_students():
    with Session(engine) as session:
        query = select(Student)
        students = session.exec(query)
        return {"students": [PublicStudent(**student.model_dump()) for student in students]}

@server.get("/api/student/{id}")
def get_one_student(id: int, response: Response):
    with Session(engine) as session:
        query = select(Student).where(Student.id == id)
        student = session.exec(query).one_or_none()

        if (not student):
            response.status_code = 404
            return {"error": "Student not found"}
        
        return {"student": PublicStudent(**student.model_dump())}

@server.post("/api/student/")
def new_student(student: NewStudent):
    with Session(engine) as session:
        global id_counter
        new_student = Student(
                **student.model_dump()
            )
        session.add(new_student)
        session.commit()
        session.refresh(new_student)
        return {"ok": 1, "student": PublicStudent(**new_student.model_dump())}

@server.patch("/api/student/{id}")
def edit_student(id: int, new_values: PartialStudent, response: Response):
    with Session(engine) as session:
        query = select(Student).where(Student.id == id)
        student = session.exec(query).one_or_none()

        if (not student):
            response.status_code = 404
            return {"error": "Student not found"}
        
        if (new_values.name != None): student.name = new_values.name
        
        session.add(student)
        session.commit()
        session.refresh(student)
        
        return {"ok": 1, "student": PublicStudent(**student.model_dump())}

@server.delete("/api/student/{id}")
def delete_student(id: int, new_values: PartialStudent, response: Response):
    with Session(engine) as session:
        query = select(Student).where(Student.id == id)
        student = session.exec(query).one_or_none()

        if (not student):
            response.status_code = 404
            return {"error": "Student not found"}

        session.delete(student)
        session.commit()

        return {"ok": 1}

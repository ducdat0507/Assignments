from django.db import models

class Student(models.Model):
    id = models.PositiveBigIntegerField(primary_key=True)
    name = models.TextField(null=False)
    age = models.PositiveSmallIntegerField(null=False)
    gpa = models.FloatField(null=False)
    
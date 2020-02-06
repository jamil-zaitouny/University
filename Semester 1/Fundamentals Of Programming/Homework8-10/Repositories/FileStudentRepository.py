from Domain.Student import Student
from Repositories.Repository import Repository
import re

class FileStudentRepository(Repository):
    def __init__(self, studentFile = "Students.txt"):
        # constructor for FileStudentRepository
        super().__init__()
        self.__studentFile = studentFile
        self.load_from_text_file()
    def add(self, objectToAdd):
        """
        Description: Adds student object to the textfile
        input: ObjectToAdd
        output: adds a student to the textfile
        """
        super().add(objectToAdd)
        self.add_to_text_file()
    def remove(self, objectToRemove):
        """
        Description:
        input:
        output:
        """
        super().remove(objectToRemove)
        self.add_to_text_file()
    def update(self, oldObject, newObject):
        """
        Description:
        input:
        output:
        """
        super().update(oldObject, newObject)
        self.add_to_text_file()
    def add_to_text_file(self):
        """
        Description:

        """
        with open(self.__studentFile, "w+") as file:
            printer = ""
            for students in super().get_list():
                printer += 'student id: "' + str(
                    students.get_ID()) + '" student name: "' + students.get_name() + '"\n'

            file.write(printer)

    def load_from_text_file(self):
        """
        Description: loads any existing items inside the text_file into the list
        """

        with open(self.__studentFile, "r+") as openStudentFile:
            for line in openStudentFile:
                student = re.findall('"([^"]*)"', line)
                super().add(Student(int(student[0]), student[1]))

from unittest import TestCase

from Controllers.DisciplineController import DisciplineController
from Controllers.GradeController import GradeController
from Controllers.StudentController import StudentController
from Domain.Discipline import Discipline
from Domain.Grade import Grade
from Domain.Student import Student

from Repositories.Repository import Repository
from Validator import Validator


class Test(TestCase):
    def setUp(self):
        #TODO: finish testing
        disciplineRepository = Repository()
        studentRepository = Repository()
        gradeReposiotry = Repository()

        self.validator = Validator()

        self.disciplineController = DisciplineController(disciplineRepository)
        self.studentController = StudentController(studentRepository)
        self.gradeController = GradeController(gradeReposiotry)

        self.disciplineController.add(12, "Mathematics")
        self.disciplineController.add(13, "Mathematics")
        self.studentController.add(13,"Williams")
        self.studentController.add(12,"Williams")
        self.gradeController.add(12, 13, 9)

    def test_discipline__add(self):
        disicplineToTest = Discipline(1, "Chemistry")

        self.disciplineController.add(1, "Chemistry")
        self.assertEqual(self.disciplineController.get_repository().get_list()[2].get_ID(), disicplineToTest.get_ID())
        self.assertEqual(self.disciplineController.get_repository().get_list()[2].get_name(), disicplineToTest.get_name())

    def test_discipline_remove(self):
        self.disciplineController.remove(12)
        self.assertEqual(len(self.disciplineController.get_repository().get_list()), 1)

    def test_discipline_update(self):
        self.disciplineController.update(12, "Chemistry")
        for items in self.disciplineController.get_repository().get_list():
            if 12 == items.get_ID() and items.get_name() != "Chemistry":
                self.assertFalse(False)

    def test_student_add(self):
        studentToTest = Student(1, "Michael")

        self.studentController.add(1, "Michael")
        self.assertEqual(self.studentController.get_repository().get_list()[2].get_ID(), studentToTest.get_ID())
        self.assertEqual(self.studentController.get_repository().get_list()[2].get_name(), studentToTest.get_name())

    def test_student_remove(self):
        self.studentController.remove(12)
        self.assertEqual(len(self.studentController.get_repository().get_list()), 1)

    def test_student_update(self):
        self.studentController.update(12, "Johny")
        for items in self.studentController.get_repository().get_list():
            if 12 == items.get_ID() and items.get_name() != "Johny":
                self.assertFalse(False)
    def test_grade_add(self):
        gradeToAdd = Grade(12, 12 ,13)
        try:
            self.validator.gradeInStudentAndDisciplineRepository(gradeToAdd, self.studentController.get_repository(), self.disciplineController.get_repository())
            self.assertTrue(True)
        except:
            self.assertFalse(True)

        gradeToAdd = Grade(16, 12, 13)
        try:
            self.validator.gradeInStudentAndDisciplineRepository(gradeToAdd, self.studentController.get_repository(), self.disciplineController.get_repository())
            self.assertFalse(True)
        except:
            self.assertTrue(True)
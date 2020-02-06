from Controllers.DisciplineController import DisciplineController
from Controllers.GradeController import GradeController
from Controllers.StudentController import StudentController
from Domain.Grade import Grade
from Statistics import Statistics
from Validator import Validator


class OperationController(object):
    def __init__(self, studentController: StudentController, disciplineController: DisciplineController,
                 gradeController: GradeController):
        # constructor for UndoController
        self.__studentController = studentController
        self.__disciplineController = disciplineController
        self.__gradeController = gradeController
        self.__statistics = Statistics()
        self.dictioanryOfOperations = {
            "addStudent": self.add_student,
            "addDiscipline": self.add_discipline,
            "addGrade": self.add_grade,
            "removeStudent": self.remove_student,
            "removeDiscipline": self.remove_discipline,
            "removeGrade": self.remove_grade,
            "getDisciplines": self.get_disciplines,
            "getStudents": self.get_students,
            "getGrades": self.get_grades,
            "updateStudent": self.update_student,
            "updateDiscipline": self.update_discipline,
            "searchStudent": self.search_student,
            "searchDiscipline": self.search_discipline,
            "studentsAlphabeticallyByDiscipline": self.students_enrolled_sorted_alphabaticlaly_by_discipline,
            "studentsGradesByDiscipline": self.students_enrolled_sorted_by_grade_for_disciplines,
            "studentsFailing": self.students_failing_at_a_discipline,
            "studentBestSituation": self.students_with_best_school_situation,
            "disciplinesSortedByGrade": self.disciplines_sorted_by_average_grade,
            "studentRepository": self.get_student_repostiory,
            "disciplineRepository": self.get_discipline_repostiory,
            "gradeRepository": self.get_grade_repostiory
        }
        self.dictionaryOfReversableFunctions = {
            "addStudent": self.add_student,
            "addDiscipline": self.add_discipline,
            "addGrade": self.add_grade,
            "removeStudent": self.remove_student,
            "removeDiscipline": self.remove_discipline,
            "removeGrade": self.remove_grade,
            "updateStudent": self.update_student,
            "updateDiscipline": self.update_discipline,
        }
        self.history = []
        self.validator = Validator()

    def add_student(self, studentID, studentName):
        """
        Description: adds a student to the controller and adds the inverse function to the list
        """
        self.__studentController.add(studentID, studentName)
        self.history.append(["addStudent", [studentID, studentName]])

    def add_discipline(self, disciplineID, disciplineName):
        """
        Description: adds a discipline to the controller and adds the inverse function to the list
        """
        self.__disciplineController.add(disciplineID, disciplineName)
        self.history.append(["addDiscipline", [disciplineID, disciplineName]])

    def remove_student(self, studentID, studentName = None):
        """
        Description: removes student from controller and adds the inverse functino to the list
        """
        studentName = self.__statistics.get_name_from_id(self.get_student_repostiory(), studentID)
        self.__studentController.remove(studentID)
        gradesRemoved = self.__gradeController.remove(studentID)
        self.history.append(["removeStudent", [studentID, studentName], gradesRemoved])

    def remove_discipline(self, disciplineID, disciplineName = None):
        """
        Description: removes discipline from controller and adds the inverse function to the list
        """
        disciplineName = self.__statistics.get_name_from_id(self.get_discipline_repostiory(), disciplineID)
        self.__disciplineController.remove(disciplineID)
        gradesRemoved = self.__gradeController.remove(None, disciplineID)
        self.history.append(["removeDiscipline", [disciplineID, disciplineName], gradesRemoved])

    def get_disciplines(self):
        """
        Description: gets disciplines from disciplineController
        """
        return self.__disciplineController.return_printable_list()
    def get_students(self):
        """
        Description: gets students from studnetController
        """
        return self.__studentController.return_printable_list()
    def add_grade(self, studentID, disciplineID, gradeValue):
        """
        Description:adds a grade to the gradeRepository
        """
        self.history.append(["addGrade", [studentID, disciplineID,  gradeValue]])
        gradeToAdd = Grade(studentID, disciplineID, gradeValue)
        self.validator.gradeInStudentAndDisciplineRepository(gradeToAdd, self.get_student_repostiory(),
                                                             self.get_discipline_repostiory())
        self.__gradeController.add(studentID,disciplineID,  gradeValue)

    def remove_grade(self, studentID, disciplineID, gradeValue):
        """
        Description: removes a grade from the gradeRepository
        """
        self.history.append(["removeGrade", [studentID, disciplineID, gradeValue]])
        self.__gradeController.remove(studentID, disciplineID, gradeValue)

    def get_grades(self):
        """
        Description: gets all grades from gradeController
        """
        return self.__gradeController.return_printable_list()
    def update_student(self, studentID, studentName):
        """
        Description: updates a student on the studentRepository
        """
        self.history.append(["updateStudent",
                             [studentID, self.__statistics.get_name_from_id(self.get_student_repostiory(), studentID), studentName]])
        self.__studentController.update(studentID, studentName)

    def update_discipline(self, disciplineID, disciplineName):
        """
        Description: updates a discipline from the dispciplineRepository
        """
        self.history.append(["updateDiscipline",
                             [disciplineID,
                              self.__statistics.get_name_from_id(self.get_discipline_repostiory(), disciplineID), disciplineName]])
        self.__disciplineController.update(disciplineID, disciplineName)

    def students_enrolled_sorted_alphabaticlaly_by_discipline(self, disciplineID):
        """
        Description: at Statistics
        """
        return self.__statistics.students_entrolled_at_a_discipline_sorted_alphabatically(disciplineID,
                                                                                          self.get_student_repostiory(),
                                                                                          self.get_discipline_repostiory(),
                                                                                          self.get_grade_repostiory())

    def students_enrolled_sorted_by_grade_for_disciplines(self, disciplineID):
        """
        Description:  at Statistics
        """
        return self.__statistics.students_enrolled_at_a_discipline_sorted_by_grade(disciplineID,
                                                                                   self.get_student_repostiory(),
                                                                                   self.get_discipline_repostiory(),
                                                                                   self.get_grade_repostiory())

    def students_failing_at_a_discipline(self):
        """
        Description:at Statistics
        """
        return self.__statistics.students_failing_at_one_or_more_disciplines(self.get_student_repostiory(),
                                                                             self.get_grade_repostiory())

    def students_with_best_school_situation(self):
        """
        Description:at Statistics
        """
        return self.__statistics.students_with_best_school_situation(self.get_student_repostiory(),
                                                                     self.get_grade_repostiory())

    def disciplines_sorted_by_average_grade(self):
        """
        Description:at Statistics
        """
        return self.__statistics.disciplines_sorted_in_descending_order_of_grades(self.get_grade_repostiory(),
                                                                                  self.get_discipline_repostiory(),
                                                                                  self.get_student_repostiory())

    def search_student(self, studentID=None, studentName=None):
        """
        Description: searches the student in the studentRepository and returns him
        """
        return self.__studentController.search(studentID, studentName)

    def search_discipline(self, disciplineID=None, disciplineName=None):
        """
        Description: searches the disciplines in the discipline repository and returns him
        """
        return self.__disciplineController.search(disciplineID, disciplineName)

    def get_student_repostiory(self):
        """
        Description: gets studentRepository from controller
        """
        return self.__studentController.get_repository()

    def get_discipline_repostiory(self):
        """
        Description: gets discipline repository from controller
        """
        return self.__disciplineController.get_repository()

    def get_grade_repostiory(self):
        """
        Description: get grade repository from contorller
        """
        return self.__gradeController.get_repository()


class UndoController(object):
    def __init__(self, operationController: OperationController):
        # constructor for UndoController
        self.operationController = operationController
        self.historyOfUndos = []

    def undo(self):
        """
        Description: Reverses the affect of a function
        """
        print("Undoing.. \n\n")
        if self.operationController.history == []:
            raise IndexError("No more undos!")

        self.historyOfUndos.append(self.operationController.history[-1])
        listToUndo = self.historyOfUndos[-1]
        if listToUndo[0][0:3] == "add":
            self.operationController.dictioanryOfOperations["remove" + listToUndo[0][3:]](*listToUndo[1])
        elif listToUndo[0][0:6] == "remove":
            self.operationController.dictioanryOfOperations["add" + listToUndo[0][6:]](*listToUndo[1])
            if len(listToUndo) == 3:
                for grades in listToUndo[2]:
                    self.operationController.add_grade(
                                                       grades.get_studentID(),
                                                        grades.get_disciplineID(),
                                                       grades.get_gradeValue())

        elif listToUndo[0][0:6] == "update":
            self.operationController.dictioanryOfOperations["update" + listToUndo[0][6:]](listToUndo[1][0], listToUndo[1][1])

        self.operationController.history.pop()
        self.operationController.history.pop()

    def redo(self):
        """
        Description:
        """
        print("Redoing.. \n\n")
        if self.historyOfUndos == []:
            raise IndexError("No more redos!")
            return

        operationToRedo = self.historyOfUndos.pop()
        if "update" in operationToRedo[0]:
            self.operationController.dictioanryOfOperations[operationToRedo[0]](operationToRedo[1][0], operationToRedo[1][2])
        else:
            self.operationController.dictioanryOfOperations[operationToRedo[0]](*operationToRedo[1])

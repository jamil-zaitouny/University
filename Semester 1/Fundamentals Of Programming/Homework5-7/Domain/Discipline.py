class Discipline(object):
    def __init__(self, ID, name):
        #constructor for Discipline
        self.__ID = ID
        self.__name = name
    def get_ID(self):
        return self.__ID
        
    def get_name(self):
        return self.__name
       
    def set_name(self, value):
        self.__name = value
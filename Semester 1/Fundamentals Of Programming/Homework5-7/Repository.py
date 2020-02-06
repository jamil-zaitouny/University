class   Repository(object):
    def __init__(self):
        # constructor for Repository
        self.__list = []

    def get_list(self):
        return self.__list

    def add(self, objectToAdd):
        """
        Description: Adds an object to the repository
        input:  Object
        output: None (list has the new object)
        """
        self.__list.append(objectToAdd)

    def remove(self, objectToRemove):
        """
        Description: removes an object from the list
        input:  object
        output: None(the object is removed from list)
        """
        self.__list = [objects for objects in self.__list if objects != objectToRemove]

    def update(self, oldObject, newObject):
        """
        Description: updates the list with a new object by removing the old object and adding a new object
        input:  oldObject, newObject
        output: None(The list is updated with new objecy)
        """
        self.remove(oldObject)
        self.add(newObject)

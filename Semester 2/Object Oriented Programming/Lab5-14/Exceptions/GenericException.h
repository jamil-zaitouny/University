//
// Created by Jamil on 5/15/2019.
//

#ifndef LAB5_6_GENERICEXCEPTION_H
#define LAB5_6_GENERICEXCEPTION_H


#include <exception>
#include <iostream>
class GenericException: public std::exception {
private:
    std::string error_message;

public:
    GenericException() = default;
    GenericException(std::string message);
    virtual const char* what();
};


#endif //LAB5_6_GENERICEXCEPTION_H

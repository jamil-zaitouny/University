//
// Created by Jamil on 5/15/2019.
//

#include "GenericException.h"
#include <iostream>

GenericException::GenericException(std::string message) {
    this->error_message = message;
}

const char *GenericException::what() {
    return error_message.c_str();
}


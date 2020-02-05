//
// Created by Jamil on 5/15/2019.
//

#ifndef LAB5_6_HTMLREPOSITORY_H
#define LAB5_6_HTMLREPOSITORY_H


#include "Repository.h"

class HTMLRepository: public Repository {
public:
    HTMLRepository() = default;
    void writeToFile();
};


#endif //LAB5_6_HTMLREPOSITORY_H

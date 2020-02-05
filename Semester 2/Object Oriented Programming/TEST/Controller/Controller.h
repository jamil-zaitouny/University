//
// Created by Jamil on 4/10/2019.
//

#ifndef TEST_CONTROLLER_H
#define TEST_CONTROLLER_H


#include "../Repository/Repository.h"

class Controller {
private:
    Repository repository;
public:
    void add(std::string type, int number, bool isImmune);
    void showSorted();
};


#endif //TEST_CONTROLLER_H

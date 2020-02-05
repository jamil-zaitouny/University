//
// Created by Jamil on 4/10/2019.
//

#ifndef TEST_REPOSITORY_H
#define TEST_REPOSITORY_H


#include "../Domain/Population.h"
#include "../DynamicArray.h"

class Repository {
private:
    DynamicArray<Population> dynamicArray;
public:
    void add(Population population);
    DynamicArray<Population> returnDynamicArray(){ return dynamicArray;};
};


#endif //TEST_REPOSITORY_H

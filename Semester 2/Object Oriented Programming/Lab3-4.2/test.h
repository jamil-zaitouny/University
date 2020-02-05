//
// Created by Jamil on 3/27/2019.
//

#ifndef LAB3_4_2_TEST_H
#define LAB3_4_2_TEST_H

#include "DynamicArray.h"

//checks if all tests are valid
void test_all();
//    checks if add works by checking if the capacity doubles when increased and if the array expands
void test_add(DynamicArray dynamicArray);
//checks if removing object actually removes something
void test_remove(DynamicArray dynamicArray);
//  checks if the item at the specific index is updated
void test_update(DynamicArray dynamicArray);
//    tests if it's sorting in ascending order
void test_sort(DynamicArray dynamicArray);
void test_undo(DynamicArray dynamicArray);
void test_redo(DynamicArray dynamicArray);
#endif //LAB3_4_2_TEST_H

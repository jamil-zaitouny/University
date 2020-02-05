//
// Created by Jamil on 3/27/2019.
//
#include <stdio.h>
#include <cstdlib>
#include <string.h>
#include <assert.h>
#include "test.h"
#include "Controller.h"

void test_add(DynamicArray* dynamicArray){
//    checks if add works by checking if the capacity doubles when increased and if the array expands
    char cmp []="rnd";
    assert(strcmp(dynamicArray->array[0].name, cmp) == 0);
    char cmp2 []="drd";
    assert(strcmp(dynamicArray->array[0].name, cmp2) != 0);

    add_material(dynamicArray, Material{cmp2, cmp2, 12, {1,2,2019}});
    add_material(dynamicArray, Material{cmp2, cmp2, 12, {1,4,2019}});

    assert(dynamicArray->capacity == 4);
    assert(dynamicArray->currentLength== 3);
}
void test_remove(DynamicArray *dynamicArray){
    //checks if removing object actually removes something
    remove_element(dynamicArray, 1);
    assert(dynamicArray->currentLength == 2);
}
void test_update(DynamicArray *dynamicArray){
//  checks if the item at the specific index is updated
    char rnd[] = "random";
    update_element(dynamicArray, 0, Material{rnd, rnd, 22, {14,12, 2019}});

    assert(dynamicArray->array[0].quantity == 22);

}
void test_sort(DynamicArray *dynamicArray){
//    tests if it's sorting in ascending order
    char rnd [] = "superRandom";
    add_material(dynamicArray, {rnd, rnd, 12000, {12, 12, 2019}});
    sort_ascending(dynamicArray);
    assert(dynamicArray->array[0].quantity == 12000);
}
void test_undo(DynamicArray *dynamicArray){

}
void test_redo(DynamicArray *dynamicArray){

}

void test_all(){
    DynamicArray *dynamicArray = (DynamicArray*)malloc(sizeof(DynamicArray));
    initialise_array(dynamicArray, 2);

    char rndName []= "rnd";
    add_material(dynamicArray, {rndName, rndName, 12, {1,2,2019}});

    test_add(dynamicArray);
    test_remove( dynamicArray);
    test_update(dynamicArray);
    test_sort( dynamicArray);
    test_undo( dynamicArray);
    test_redo(dynamicArray);
    free_dynamic_array(&dynamicArray);
}

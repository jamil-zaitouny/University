#include <iostream>
#include "DynamicArray.h"
#include "Controller.h"
#include "UI.h"
#include "test.h"

int main() {
//    tests all functions
    test_all();


    DynamicArray* repository = (DynamicArray*)malloc(sizeof(DynamicArray));

    initialise_array(repository, 10);
    char supplier1[] = "Michael";
    char supplier2[] = "Johnny";
    char name1[] = "Milk";
    char name2[] = "Choclate";
    char name3[] = "Wine";
    int quantity1 = 12;
    int quantity2= 15;
    Date expirationDate1 = {5,5,2019};
    Date expirationDate2 = {2,2,2019};

    add_material(repository, {name1, supplier1, quantity1,expirationDate1});
    add_material(repository, {name2, supplier1, quantity1, expirationDate1});
    add_material(repository, {name3, supplier1, quantity1, expirationDate1});
    add_material(repository, {name1, supplier2, quantity1, expirationDate1});
    add_material(repository, {name2, supplier2, quantity2, expirationDate1});
    add_material(repository, {name3, supplier2, quantity2, expirationDate1});
    add_material(repository, {name1, supplier1, quantity2, expirationDate2});
    add_material(repository, {name2, supplier1, quantity2, expirationDate2});
    add_material(repository, {name3, supplier1, quantity1, expirationDate2});
    add_material(repository, {name2, supplier2, quantity1, expirationDate2});
    add_material(repository, {name2, supplier2, quantity1, expirationDate2});
    add_material(repository, {name2, supplier2, quantity1, expirationDate2});
    add_material(repository, {name2, supplier2, quantity1, expirationDate2});
    add_material(repository, {name2, supplier2, quantity1, expirationDate2});

    main_options(repository);

    free_dynamic_array(&repository);
}
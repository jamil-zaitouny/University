//
// Created by Jamil on 3/27/2019.
//

#include <cstring>
#include <cstdio>
#include <cstdlib>
#include "Material.h"
Material createMaterial(char name[], char supplier[], int quantity, int day, int month, int year){
    Material returnedMaterial;
    if(strlen(name) > 30 || strlen(supplier) > 30){
        char* value= nullptr;
        Material material{ value, value, NULL, {NULL,NULL,NULL}};
        printf("The maximum size allowed is 30!");
        return material;
    }
    returnedMaterial.name = name;
    returnedMaterial.suplier= supplier;
    returnedMaterial.quantity= quantity;
    returnedMaterial.expirationDate= {day, month, year};
    return returnedMaterial;
}
//void createMaterial(Material *material, char name[], char supplier[], int quantity, int day, int month, int year){
//    if(material == NULL){
//        printf("The material was freed");
//        return;
//    }
//    if(strlen(name) > 30 || strlen(supplier) > 30){
//        printf("The maximum size allowed is 30!");
//        return;
//    }
//    material->name = name;
//    material->suplier= supplier;
//    material->quantity = quantity;
//    material->expirationDate = {day, month, year};
//}
//void freeMaterial(Material **material){
//    free(*material);
//    *material = NULL;
//}

void print_material(Material material){
    printf("The material %s with the supplier %s has the following stats:\n", material.name, material.suplier);
    printf("Quantity: %d expirationDate: %d/%d/%d\n", material.quantity, material.expirationDate.day,material.expirationDate.month,material.expirationDate.year);
}

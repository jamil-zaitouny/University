//
// Created by Jamil on 3/27/2019.
//

#ifndef LAB3_4_2_MATERIAL_H
#define LAB3_4_2_MATERIAL_H

#include "ExpirationDate.h"

typedef struct {
    char *name;
    char *suplier;
    int quantity;
    Date expirationDate;
} Material;

//returns a material from the expected inputs
Material createMaterial(char name[], char supplier[], int quantity, int day, int month, int year);
//void createMaterial(Material *material, char name[], char supplier[], int quantity, int day, int month, int year);
//void freeMaterial(Material **material);
//prints a material to the screen
void print_material(Material material);
#endif //LAB3_4_2_MATERIAL_H

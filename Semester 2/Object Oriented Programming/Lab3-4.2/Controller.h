//
// Created by Jamil on 3/27/2019.
//

#ifndef LAB3_4_2_CONTROLLER_H
#define LAB3_4_2_CONTROLLER_H

#include "DynamicArray.h"
//adds a material to the dynamic array after checking if the material doesn't already exist in teh array
void add_material(DynamicArray *repository, Material material);
//checks if the material already exists in array
int check_if_material_exists(DynamicArray* localRepository, Material material);
//prints all materials that passed their expiration date
void print_all_materials_passet_expiration_date(int currentDay, int currentMonth, int currentYear, DynamicArray* localRepository);
//prints all materials from a specific supplier that have less than a specified quantity
void print_all_materials_from_supplier(DynamicArray* localRepository, char supplier[], int maxQuantity);
//sorts all materials into ascending order of quantity
void sort_ascending(DynamicArray* dynamicArray);
#endif //LAB3_4_2_CONTROLLER_H

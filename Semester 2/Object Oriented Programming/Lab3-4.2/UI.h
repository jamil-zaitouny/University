//
// Created by Jamil on 3/27/2019.
//

#ifndef LAB3_4_2_UI_H
#define LAB3_4_2_UI_H

#include "DynamicArray.h"

void main_options(DynamicArray* localRepository);
void add_material_ui(DynamicArray* localRepository);
void remove_material(DynamicArray* localRepository);
void update_material_ui(DynamicArray* localRepository);
void material_sort_with_suplier_ui(DynamicArray* localRepository);
void material_passed_expiration_date(DynamicArray* localRepository);
void display_all_materials(DynamicArray* localRepository);
void undo(DynamicArray* localRepository);
void redo(DynamicArray* localRepository);

#endif //LAB3_4_2_UI_H

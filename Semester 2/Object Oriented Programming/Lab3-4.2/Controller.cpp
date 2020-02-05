//
// Created by Jamil on 3/27/2019.
//

#include <cstdio>
#include <cstring>
#include <cstdlib>
#include "Controller.h"
int check_if_material_exists(DynamicArray *localRepository, Material material){
//Returns index if material exists otherwise returns -1
    for(int i = 0; i <= localRepository->currentLength; i++){
        if(localRepository->array[i].name == material.name && localRepository ->array[i].suplier == material.suplier){
            if(localRepository->array[i].expirationDate.day == material.expirationDate.day &&
                    localRepository->array[i].expirationDate.month== material.expirationDate.month &&
                    localRepository->array[i].expirationDate.year== material.expirationDate.year){
                return i;
            }
        }
    }
    return -1;
}


void add_material(DynamicArray *localRepository, Material material){
    int value = check_if_material_exists(localRepository,material);
    if(value == -1){
        add_element(localRepository, material);
    }else{
        localRepository->array[value].quantity += material.quantity;
    }
}
void print_all_materials_passet_expiration_date(int currentDay, int currentMonth, int currentYear, DynamicArray* localRepository){
    if(currentMonth > 12 || currentMonth < 1){
        printf("Invalid Month\n");
        return;
    }else if((currentMonth == 2 && currentDay > 28) || (currentMonth == 2 && currentDay < 1)){
        printf("Invalid day!\n");
        return;
    }else if(currentDay > 31 || currentDay < 1){
        printf("Invalid day!\n");
        return;
    }


    printf("Here's the list of all expired Materials: \n");
    for (int i = 0; i < localRepository->currentLength; ++i) {
        int sumDays = localRepository->array[i].expirationDate.year * 365 +
                      localRepository->array[i].expirationDate.month *30 +
                      localRepository->array[i].expirationDate.day;
        if(currentYear*365 + currentMonth *30 + currentDay  >= sumDays){
            print_material(localRepository->array[i]);
        }
    }
}

void print_all_materials_from_supplier(DynamicArray* localRepository, char supplier[], int maxQuantity){
    //if supplier not found supplier checker stays at 0
    DynamicArray *arrayOfSuppliers = (DynamicArray*)malloc(sizeof(DynamicArray));

    initialise_array(arrayOfSuppliers, 5);
    for(int i = 0; i < localRepository->currentLength; i++){
        if(strcmp(localRepository->array[i].suplier, supplier) == 0){
            add_material(arrayOfSuppliers,localRepository->array[i]);
        }
    }
    sort_ascending(arrayOfSuppliers);

    for(int i = 0; i < arrayOfSuppliers->currentLength; i++){
        print_material(arrayOfSuppliers->array[i]);
    }
    free_dynamic_array(&arrayOfSuppliers);
}
void sort_ascending(DynamicArray* localRepository){
    int swapped;

    do{
        swapped = 0;
        for(int i = 0; i < localRepository->currentLength - 1; i++){
            if(localRepository->array[i].quantity < localRepository->array[i + 1].quantity){
                Material aux = localRepository->array[i];
                localRepository->array[i] = localRepository->array[i + 1];
                localRepository->array[i + 1] = aux;
                swapped = 1;
            }
        }
    }while(swapped == 1);
}


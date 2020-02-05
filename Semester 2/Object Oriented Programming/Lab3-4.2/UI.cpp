//
// Created by Jamil on 3/27/2019.
//

#include <cstdio>
#include <cstdlib>
#include "UI.h"
#include "Controller.h"

void main_options(DynamicArray* localRepository){
    int userOption;
    printf("1. add new material\n");
    printf("2. remove material\n");
    printf("3. update existing material\n");
    printf("4. display all materials\n");
    printf("5. display all materials depending on suppliers and quantity\n");
    printf("6. display all expired materials\n");
    printf("Please pick the desired option");
    scanf("%d", &userOption);

    switch(userOption){
        case 1:
            add_material_ui(localRepository);
            break;
        case 2:
            remove_material(localRepository);
            break;
        case 3:
            update_material_ui(localRepository);
            break;
        case 4:
            display_all_materials(localRepository);
            break;
        case 5:
            material_sort_with_suplier_ui(localRepository);
            break;
        case 6:
            material_passed_expiration_date(localRepository);
            break;
        default:
            printf("option not in list!\n\n");
            main_options(localRepository);
    }
}
void add_material_ui(DynamicArray* localRepository){
    char* name = (char*)malloc(sizeof(char) * 30);
    char* supplier = (char*)malloc(sizeof(char) * 30);;
    int quantity;
    int day;
    int month;
    int year;

    printf("Please enter the name of the material");
    scanf("%s", name);
    printf("Please enter the name of the supplier");
    scanf("%s", supplier);
    printf("please enter the quantity of the material");
    scanf("%d", &quantity);
    printf("please enter the date in the following format: day month year");
    scanf("%d %d %d",&day, &month, &year);
    Date expirationDate = {day, month, year};
    add_material(localRepository, Material{name, supplier, quantity, expirationDate});

    free(name);
    free(supplier);

    printf("New material added successfully\n");
    main_options(localRepository);
}
void display_all_materials(DynamicArray* localRepository){
    printf("Here's a list of all the materials\n");
    for(int i = 0; i < localRepository->currentLength; i++){
        print_material(localRepository->array[i]);
    }
    main_options(localRepository);
}
void remove_material(DynamicArray* localRepository){
    int index;
    printf("What's the index of the item that you want to remove? ");
    scanf("%d", &index);
    remove_element(localRepository, index);
    printf("Item removed\n");
    main_options(localRepository);
}
void update_material_ui(DynamicArray* localRepository){
    int index;
    printf("What is the index of the material that you want to update?\n");
    scanf("%d", &index);
    printf("Create the updated material:\n");
    char* name = (char*)malloc(sizeof(char) * 30);
    char* supplier = (char*)malloc(sizeof(char) * 30);;
    int quantity;
    int day;
    int month;
    int year;

    printf("Please enter the name of the material");
    scanf("%s", name);
    printf("Please enter the name of the supplier");
    scanf("%s", supplier);
    printf("please enter the quantity of the material");
    scanf("%d", &quantity);
    printf("please enter the date in the following format: day month year");
    scanf("%d %d %d",&day, &month, &year);
    Date expirationDate = {day, month, year};
    update_element(localRepository,index, Material{name, supplier, quantity, expirationDate});

    free(name);
    free(supplier);

    printf("Material updated successfully\n");
    main_options(localRepository);
}
void material_sort_with_suplier_ui(DynamicArray* localRepository){
    char* supplier = (char*)malloc(sizeof(char)*30);
    int maxQuantity;

    printf("Enter the name of the supplier: ");
    scanf("%s", supplier);
    printf("Enter the maximum quantity: ");
    scanf("%d", &maxQuantity);

    print_all_materials_from_supplier(localRepository, supplier, maxQuantity);
    main_options(localRepository);
}
void material_passed_expiration_date(DynamicArray* localRepository){
    int currentDay;
    int currentMonth;
    int currentYear;

    printf("Enter the current date with the format: day month year");
    scanf("%d %d %d", &currentDay, &currentMonth, &currentYear);
    print_all_materials_passet_expiration_date(currentDay,currentMonth, currentYear, localRepository);
    main_options(localRepository);
}
void undo(DynamicArray* localRepository);
void redo(DynamicArray* localRepository);

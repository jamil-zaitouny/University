#include <stdio.h>
#include <stdlib.h>
#include "DynamicArray.h"

//
// Created by Jamil on 3/27/2019.
//
void initialise_array(DynamicArray* array, int capacity){
    array->array= (Telem*)malloc(sizeof(Telem)*capacity);
    array->capacity = capacity;
    array->currentLength = 0;
}

void add_element(DynamicArray* dynamicArray, Telem telem){
    if(dynamicArray == NULL){
        printf("The array has been freed!");
        return;
    }
    if(dynamicArray->currentLength == dynamicArray->capacity){
        resize(dynamicArray);
    }
    dynamicArray->array[dynamicArray->currentLength] = telem;
    dynamicArray->currentLength += 1;

}
void free_dynamic_array(DynamicArray **dynamicArray){
    free((*dynamicArray)->array);
    free(*dynamicArray);
    *dynamicArray = NULL;
}
void remove_element(DynamicArray* dynamicArray, int index){
    if(index > dynamicArray->currentLength){
        printf("The element does not exist");
        return;
    }
    for(int i = index; i < dynamicArray->currentLength - 1; i++){
        dynamicArray->array[i] = dynamicArray->array[i + 1];
    }
    dynamicArray->currentLength--;

}
void update_element(DynamicArray* dynamicArray, int index, Telem newElem){
    if(index > dynamicArray->currentLength){
        printf("The index you entered does not exist!");
        return;
    }
    dynamicArray->array[index] = newElem;
}
void resize(DynamicArray *dynamicArray){
    Telem* newArray;
    dynamicArray->capacity *= 2;
    newArray = (Telem *)realloc(dynamicArray->array, sizeof(Telem)*dynamicArray->capacity);
    dynamicArray->array = newArray;
}

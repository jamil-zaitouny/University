//
// Created by Jamil on 4/10/2019.
//

#include "Repository.h"

void Repository::add(Population population) {
    for(int i = 0; i < dynamicArray.get_current_size();i++){
        if(dynamicArray[i].getType() == population.getType()){
            dynamicArray.replaceAtPosition(i, population);
        }
    }
    dynamicArray.addToEnd(population);
}

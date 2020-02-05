//
// Created by Jamil on 4/10/2019.
//

#include "../Controller/Controller.h"

void mainMenu();
void addOrganism();

Controller controller;

void sortByType() {

}


void mainMenu() {
    int userOption;
    std::cout<<"1. add a new population\n";
    std::cout<<"2. add a new population\n";

    std::cout<<"what do you want to do? ";
    std::cin>>userOption;
    if(userOption == 1){
        addOrganism();
    }
    mainMenu();
}

void addOrganism() {
    std::string type;
    int nrOrganisms;
    std::string areImmune;
    bool isImmune;
    bool add;

    std::cout<<"What is the type?";
    std::cin>>type;
    std::cout<<"Enter the nr of organisms: ";
    std::cin>>nrOrganisms;
    std::cout<<"are they immune y/n";
    std::cin>>areImmune;

    if(areImmune == "y"){
        isImmune = true;
    }else{
        isImmune = false;
    }
    controller.add(type, nrOrganisms,isImmune);
    return;
}

//
// Created by Jamil on 4/10/2019.
//

#include "Population.h"

const std::string &Population::getType() const {
    return type;
}

void Population::setType(const std::string &type) {
    Population::type = type;
}

int Population::getNumberOfOrganisms() const {
    return numberOfOrganisms;
}

void Population::setNumberOfOrganisms(int numberOfOrganisms) {
    Population::numberOfOrganisms = numberOfOrganisms;
}

bool Population::isIsImmune() const {
    return isImmune;
}

void Population::setIsImmune(bool isImmune) {
    Population::isImmune = isImmune;
}

Population::Population(std::string type, int numberOfOrganisms, bool isImmune):
type(type), numberOfOrganisms(numberOfOrganisms), isImmune(isImmune)
{

}

Population::Population() {

}

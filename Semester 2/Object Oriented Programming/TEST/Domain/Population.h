//
// Created by Jamil on 4/10/2019.
//

#ifndef TEST_POPULATION_H
#define TEST_POPULATION_H


#include <string>

class Population {
private:
    std::string type;
    int numberOfOrganisms;
    bool isImmune;
public:
    Population(std::string type, int numberOfOrganisms, bool isImmune);

    Population();
    const std::string &getType() const;

    void setType(const std::string &type);

    int getNumberOfOrganisms() const;

    void setNumberOfOrganisms(int numberOfOrganisms);

    bool isIsImmune() const;

    void setIsImmune(bool isImmune);
};


#endif //TEST_POPULATION_H

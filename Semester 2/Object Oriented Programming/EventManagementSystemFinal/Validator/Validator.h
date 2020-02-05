//
// Created by Jamil on 4/7/2019.
//

#ifndef LAB5_6_VALIDATOR_H
#define LAB5_6_VALIDATOR_H


#include "../Repository/Repository.h"

class Validator {
public:
    static void validate_month(int month);
    static void  validate_day(int day);
    static void validate_year(int year);
    static void validate_hour(int hour);
    static void validate_minute(int minute);
    static void validate_date_and_time(int day, int month, int year, int hour, int minute);
    static void validate_number_of_people_coming(int peopleComing);

    static void valid_URL_exists(std::string basic_string, std::vector<Event> events);
};


#endif //LAB5_6_VALIDATOR_H

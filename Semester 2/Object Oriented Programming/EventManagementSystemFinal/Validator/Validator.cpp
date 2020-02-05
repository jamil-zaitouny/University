//
// Created by Jamil on 4/7/2019.
//

#include <string>
#include "Validator.h"
#include "../Exceptions/GenericException.h"

void Validator::validate_month(int month) {
    if(month > 12|| month < 1){
        throw GenericException("The month you entered is not between 1 and 12");
    }
}

void Validator::validate_day(int day) {
    if(day > 31 || day < 1){
        throw GenericException("The day you entered is not between 1 and 31");
    }
}

void Validator::validate_year(int year) {
    if(year < 2019 || year > 2029){
        throw GenericException("The year can only be between 2019 and 2029");
    }
}

void Validator::validate_hour(int hour) {
    if(hour > 23 || hour < 0){
        throw GenericException("The hour you entered is not between 0 and 23");
    }
}

void Validator::validate_minute(int minute) {
    if(minute > 59 || minute < 0){
        throw GenericException("The minute you entered is not between 0 and 59");
    }
}

void Validator::validate_date_and_time(int day, int month, int year, int hour, int minute) {
    validate_day(day);
    validate_month(month);
    validate_year(year);
    validate_hour(hour);
    validate_minute(minute);
}

void Validator::validate_number_of_people_coming(int peopleComing) {
    if(peopleComing < 0){
        throw GenericException("Number of people coming cannot be less than 0!");
    }
}

void Validator::valid_URL_exists(std::string URL, std::vector<Event> events) {
    for(auto &i:events){
        if(i.getURL() == URL){
            throw GenericException("The URL is already in the list!");
        }
    }
}

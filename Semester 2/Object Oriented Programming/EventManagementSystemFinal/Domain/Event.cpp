//
// Created by Jamil on 4/7/2019.
//

#include <sstream>
#include "Event.h"

const std::string &Event::getTitle() const {
    return title;
}

void Event::setTitle(const std::string &title) {
    Event::title = title;
}

const std::string &Event::getDescription() const {
    return description;
}

void Event::setDescription(const std::string &description) {
    Event::description = description;
}

const DateAndTime &Event::getDateAndTime() const {
    return dateAndTime;
}

void Event::setDateAndTime(const DateAndTime &dateAndTime) {
    Event::dateAndTime = dateAndTime;
}

int Event::getNumberOfPeopleGoing() const {
    return numberOfPeopleGoing;
}

void Event::setNumberOfPeopleGoing(int numberOfPeopleGoing) {
    Event::numberOfPeopleGoing = numberOfPeopleGoing;
}

const std::string &Event::getURL() const {
    return URL;
}

void Event::setURL(const std::string &URL) {
    Event::URL = URL;
}

std::string Event::toString() {
    std::stringstream eventString;
    eventString<<"Title: "<<getTitle()<<"\n";
    eventString<<"Description: "<<getDescription()<<"\n";
    eventString<<"Date and Time: "<<dateAndTime.day<<"/"<<dateAndTime.month<<"/"<<dateAndTime.year<<"  ";
    if(dateAndTime.hour <= 10 && dateAndTime.minute <= 10){
        eventString<<0<<dateAndTime.hour<<":"<<0<<dateAndTime.minute<<"\n";
    }else if(dateAndTime.hour >= 10 && dateAndTime.minute <= 10){
        eventString<<dateAndTime.hour<<":"<<0<<dateAndTime.minute<<"\n";
    }else if(dateAndTime.hour >= 10 && dateAndTime.minute >= 10) {
        eventString<<dateAndTime.hour<<":"<<dateAndTime.minute<<"\n";
    }
    else if(dateAndTime.hour <= 10 && dateAndTime.minute >= 10){
        eventString<<dateAndTime.hour<<":"<<dateAndTime.minute<<"\n";
    }
    eventString<<"Number of people going: "<<getNumberOfPeopleGoing()<<"\n";
    eventString<<"URL: "<<getURL()<<"\n";
    return eventString.str();
}

Event::Event(std::string title, std::string description, int day, int month, int year,int hour, int minute, int numberOfPeople, std::string URL) {
    this->setTitle(title);
    this->setDescription(description);
    this->setDateAndTime({day,month,year,hour,minute});
    this->setNumberOfPeopleGoing(numberOfPeople);
    this->setURL(URL);
}

Event::Event() {

}

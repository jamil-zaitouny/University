//
// Created by Jamil on 4/7/2019.
//
#include "Controller.h"
#include "../Validator/Validator.h"
#include "../Exceptions/GenericException.h"
#include "../Repository/HTMLRepository.h"
#include "../Repository/CSVRepository.h"

void Controller::add_event(std::string title, std::string description, int day, int month, int year, int hour, int minute,
                      int numberOfPeople, std::string URL) {
    this->undos.push_back(repository->get_copy_of_array());
    Validator::validate_date_and_time(day,month,year,hour,minute);
    Validator::validate_number_of_people_coming(numberOfPeople);
    Validator::valid_URL_exists(URL, repository->get_copy_of_array());
    repository->add_item(Event{title,description,day,month,year,hour,minute,numberOfPeople,URL});
}

void
Controller::update_date_and_time(std::string URL, int newDay, int newMonth, int newYear, int newHour, int newMinute) {
    Validator::validate_date_and_time(newDay, newMonth, newYear, newHour, newMinute);
    int currentPosition = 1;
    for(auto &i : repository->get_copy_of_array()){
        if(i.getURL() == URL){
            Event newEvent = i;
            newEvent.setDateAndTime({newDay, newMonth, newYear, newHour, newMinute});
            repository->update_repository(currentPosition, newEvent);
            return;
        }
        currentPosition++;
    }
    throw GenericException("URL doesn't exists!");
}

void Controller::update_title(std::string URL, std::string newTitle) {
    int currentPosition = 1;
    for(auto &i : repository->get_copy_of_array()){
        if(i.getURL() == URL){
            Event newEvent = i;
            newEvent.setTitle(newTitle);
            repository->update_repository(currentPosition++, newEvent);
            return;
        }
    }
    throw GenericException("URL doesn't exists!");
}

void Controller::update_description(std::string URL, std::string newDescription) {
    int currentPosition = 1;
    for(auto &i : repository->get_copy_of_array()){
        if(i.getURL() == URL){
            Event newEvent = i;
            newEvent.setDescription(newDescription);
            repository->update_repository(currentPosition, newEvent);
            return;
        }
        currentPosition++;
    }
    throw GenericException("URL doesn't exists!");
}

void Controller::update_number_of_people(std::string URL, int nrOfPeopleComing) {
    int currentPosition = 1;
    for(auto &i : repository->get_copy_of_array()){
        if(i.getURL() == URL){
            Event newEvent = i;
            newEvent.setNumberOfPeopleGoing(nrOfPeopleComing);
            repository->update_repository(currentPosition, newEvent);
            return;
        }
        currentPosition++;
    }
    throw GenericException("URL doesn't exists!");
}

void Controller::update_URL(std::string URL, std::string newURL) {
    int currentPosition = 1;
    for(auto &i : repository->get_copy_of_array()){
        if(i.getURL() == URL){
            Event newEvent = i;
            newEvent.setURL(newURL);
            repository->update_repository(currentPosition, newEvent);
            return;
        }
        currentPosition++;
    }
    throw GenericException("URL doesn't exists!");
}

void Controller::remove_event(std::string URL) {
    this->undos.push_back(repository->get_copy_of_array());
    int currentPosition = 1;
    for(auto &i : repository->get_copy_of_array()){
        if(i.getURL() == URL){
            repository->delete_item(currentPosition);
        }
        currentPosition++;
    }
}

std::string Controller::get_printable() {
    return repository->printable_list_of_database();
}

void Controller::join_event(std::string URL) {
    this->undos.push_back(repository->get_copy_of_array());
    int currentPosition = 1;
    for(auto &i : repository->get_copy_of_array()){
        if(i.getURL() == URL){
            Event updatedEvent = i;
            updatedEvent.setNumberOfPeopleGoing(updatedEvent.getNumberOfPeopleGoing() + 1);
            repository->update_repository(currentPosition, updatedEvent);
        }
        currentPosition++;
    }
}

void Controller::undo()
{
    redos.push_back(repository->get_copy_of_array());
    repository->setVector(undos.back());
    undos.pop_back();
}

void Controller::redo()
{
    undos.push_back(repository->get_copy_of_array());
    repository->setVector(redos.back());
    redos.pop_back();
}

std::vector<Event> Controller::get_vector() {
    return repository->get_copy_of_array();
}

Controller::Controller(int fileType) {
    this->fileType = fileType;
    repoType(fileType);
    add_event("6ix9ine","@ Untold Dacă dați going sigur vine!",12, 12, 2019,10,0, 50,"https://www.facebook.com/events/1339401266194295/");
    add_event("Miley Cyrus","@ Untold Dacă dați going sigur vine!",8,2,2019,10, 0,50,"https://www.facebook.com/events/2339401266194295/");
    add_event("James Oliver","@ Untold Dacă dați going sigur vine!",8,2,2019,10,0,50,"https://www.facebook.com/events/3339401266194295/");
    add_event("Michael Shane","@ Untold Dacă dați going sigur vine!",8,2,2019,15,0,50,"https://www.facebook.com/events/4339401266194295/");
    add_event("Bob the builder","@ Untold Dacă dați going sigur vine!",8,5,2019,10,0,30,"https://www.facebook.com/events/5339401266194295/");
    add_event("John the menance","@ Untold Dacă dați going sigur vine!",8,6,2019,10,0,45,"https://www.facebook.com/events/6339401266194295/");
    add_event("Boby martin","@ Untold Dacă dați going sigur vine!",8,2,2019,10,2,50,"https://www.facebook.com/events/7339401266194295/");
    add_event("Jonathan Mechale","@ Untold Dacă dați going sigur vine!",8,2,2019,10,2,50,"https://www.facebook.com/events/8339401266194295/");
    add_event("Abdulah","@ Untold Dacă dați going sigur vine!",8,2,2019,10,2,50,"https://www.facebook.com/events/9339401266194295/");
    add_event("Mahmud","@ Untold Dacă dați going sigur vine!",8,2,2019,10,2,50,"https://www.facebook.com/events/739401266194295/");
}

void Controller::update_event(Event event)
{
    this->undos.push_back(repository->get_copy_of_array());
    for(auto &i: get_vector()){
        if(event.getURL().compare(i.getURL()) == 0){
            if(event.getTitle().compare(i.getTitle()) != 0) update_title(i.getURL(), event.getTitle());
            if(event.getDescription().compare(i.getDescription())) update_description(i.getURL(), event.getDescription());
            if(event.getNumberOfPeopleGoing() != i.getNumberOfPeopleGoing()) update_number_of_people(i.getURL(), event.getNumberOfPeopleGoing());
            if(event.getDateAndTime().day != i.getDateAndTime().day) update_date_and_time(i.getURL(), event.getDateAndTime().day, event.getDateAndTime().month, event.getDateAndTime().year, event.getDateAndTime().hour, event.getDateAndTime().minute);
            if(event.getDateAndTime().month != i.getDateAndTime().month) update_date_and_time(i.getURL(), event.getDateAndTime().day, event.getDateAndTime().month, event.getDateAndTime().year, event.getDateAndTime().hour, event.getDateAndTime().minute);
            if(event.getDateAndTime().year!= i.getDateAndTime().year) update_date_and_time(i.getURL(), event.getDateAndTime().day, event.getDateAndTime().month, event.getDateAndTime().year, event.getDateAndTime().hour, event.getDateAndTime().minute);
            if(event.getDateAndTime().minute!= i.getDateAndTime().minute) update_date_and_time(i.getURL(), event.getDateAndTime().day, event.getDateAndTime().month, event.getDateAndTime().year, event.getDateAndTime().hour, event.getDateAndTime().minute);
            if(event.getDateAndTime().hour!= i.getDateAndTime().hour) update_date_and_time(i.getURL(), event.getDateAndTime().day, event.getDateAndTime().month, event.getDateAndTime().year, event.getDateAndTime().hour, event.getDateAndTime().minute);
            break;
        }
    }
}

void Controller::add_event(Event event)
{
    this->undos.push_back(repository->get_copy_of_array());
    this->repository->add_item(event);
}

void Controller::repoType(int i) {
    if(i == 1){
        repository = new HTMLRepository{};
    }else if (i == 2){
        repository = new CSVRepository{};
    }
}

void Controller::openFile() {
    repository->writeToFile();
}

int Controller::getFileType() const {
    return fileType;
}

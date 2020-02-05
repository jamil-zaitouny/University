//
// Created by Jamil on 4/7/2019.
//

#ifndef LAB5_6_CONTROLLER_H
#define LAB5_6_CONTROLLER_H


#include "../Repository/Repository.h"

class Controller {
private:
    Repository* repository;
    int fileType;
public:
    int getFileType() const;

public:

    Controller();
//    /*
//    Description: Takes a fileName from the user and reads the Events from it putting them in the Repository
//    input: a fileName
//    preconditions:the file exists
//    output: puts the events in the repository
//    posconditions: the events are valid
//    */
//    void read_from_file(std::string fileName);
//
//
//
//    /*
//    Description: Puts all the new Events in the file specified in the parameter
//    input:a fileName
//    preconditions: the file exists
//    output: puts the events in the repository in the given file
//    posconditions: the file exists
//    */
//    void write_to_file(std::string fileName);



    /*
    Description: adds an event to the repository by creating an event item from the given values
    input: event parameters
    preconditions:they're valid
    output: nothing
    posconditions: it puts the new event in the repository
    */
    void add_event(std::string title,std::string description, int day, int month, int year, int hour, int minute, int numberOfPeople, std::string URL);
    /*
     Description: adds an event to the repository by creating an event item from the given values
     input: event parameters
     preconditions:they're valid
     output: nothing
     posconditions: it puts the new event in the repository
     */
    void update_date_and_time(std::string URL, int newDay, int newMonth, int newYear, int newHour, int newMinute);
    void update_title(std::string URL, std::string newTitle);
    void update_description(std::string URL, std::string newDescription);
    void update_number_of_people(std::string URL, int nrOfPeopleComing);
    void update_URL(std::string URL, std::string newURL);
    void remove_event(std::string URL);
    void join_event(std::string URL);
    std::string get_printable();
    std::vector<Event> get_vector();

    void repoType(int i);

    void openFile();
};


#endif //LAB5_6_CONTROLLER_H

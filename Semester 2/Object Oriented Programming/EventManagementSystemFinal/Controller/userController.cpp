//
// Created by Jamil on 4/8/2019.
//

#include "userController.h"
#include "../Exceptions/GenericException.h"
#include "../Repository/HTMLRepository.h"
#include "../Repository/CSVRepository.h"

userController::userController(Controller controller): controller(controller){
    if(controller.getFileType() == 1){
        repository = new HTMLRepository;
    }else{
        repository = new CSVRepository;
    }
}

std::vector<Event> userController::events_from_given_month(int month) {
    std::vector<Event> listWithMonths;
    for(auto &i: controller.get_vector()){
        if(i.getDateAndTime().month == month){
            listWithMonths.push_back(i);
        }
    }
    if(listWithMonths.empty()){
        throw GenericException("No events in the specified month");
    }
    return listWithMonths;
}

void userController::add_event(Event event) {
        repository->add_item(event);
}

std::string userController::get_items_from_repository() {
    return repository->printable_list_of_database();
}

std::vector<Event> userController::get_vector()
{
    return repository->get_copy_of_array();
}

void userController::delete_event(Event event) {
    int position = 1;
    for(auto &i: repository->get_copy_of_array()){
        if(event.getURL() == i.getURL()){
            repository->delete_item(position);
        }
        position++;
    }
}


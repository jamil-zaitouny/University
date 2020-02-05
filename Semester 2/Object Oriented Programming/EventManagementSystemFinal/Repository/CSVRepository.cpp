//
// Created by Jamil on 5/15/2019.
//

#include <fstream>
#include <afxres.h>
#include "CSVRepository.h"

void CSVRepository::writeToFile() {
    std::ofstream file{"repository.csv"};
    file<<"Title"<<",";
    file<<"Description"<<",";
    file<<"Day"<<",";
    file<<"Month"<<",";
    file<<"Year"<<",";
    file<<"Hour"<<",";
    file<<"Minute"<<",";
    file<<"People going"<<",";
    file<<"URL"<<"";
    file<<"\n";
    for(auto &items: this->get_copy_of_array()){
        file<<items.getTitle()<<",";
        file<<items.getDescription()<<",";
        file<<items.getDateAndTime().day<<",";
        file<<items.getDateAndTime().month<<",";
        file<<items.getDateAndTime().year<<",";
        file<<items.getDateAndTime().hour<<",";
        file<<items.getDateAndTime().minute<<",";
        file<<items.getNumberOfPeopleGoing()<<",";
        file<<items.getURL()<<"";
        file<<"\n";
    }
    std::string fileName = "C:\\Users\\Jamil\\Desktop\\University\\Homeworks\\OOP\\Lab5-6\\cmake-build-debug\\repository.csv";
    //ShellExecuteA(NULL, "open",fileName.c_str(),NULL,NULL,NULL);
}

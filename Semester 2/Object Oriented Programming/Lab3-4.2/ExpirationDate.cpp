#include "ExpirationDate.h"

//
// Created by Jamil on 3/27/2019.
//
int compareDates(Date date1, Date date2){
/*Details: compares date1 and date2
 * pre: date1 and date2 are valid
 *output:1 if date 1 is bigger and 2 if date2 is bigger and 0 if they're equal
 */
//    Checks if the two dates are equal
    if(date1.day == date2.day || date1.month== date2.month || date1.year== date2.year){
        return 0;
    }

//    checks if date1 is bigger than date 2
    if (date1.year > date2.year){
        return 1;
    }else if (date1.month > date2.month){
        return 1;
    }else if(date1.day > date2.day){
        return 1;
    }
//  If date1 is not bigger than or equal to date2 them date 1 is naturally bigger
    return -1;
}

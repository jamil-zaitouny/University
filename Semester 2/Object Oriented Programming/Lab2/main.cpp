#include <stdio.h>
#define ARRAYLENGTH 100


void sort();

int main() {

    sort();

    return 0;
}

void sort() {
    /*
    Description: Asks the user to fill up an array, and then the array is sorted
    input:  arrayLength, numbers to be added
    preconditions:arrayLength, numbers to be added are both integers and arrayLength is not negative or bigger than
     ARRAYLENGTH(max array length size)
    output:the array is sorted
    */
    int arrayLength;
    int arrayToSort[ARRAYLENGTH];
    printf("Enter the size of the array: ");
    scanf("%d", &arrayLength);

    if (arrayLength> ARRAYLENGTH) {
        printf("Wrong length");
    }

    for (int i = 0; i < arrayLength; i++) {
        printf("add a number: ");
        scanf("%d", &arrayToSort[i]);
    }

    for (int i = 0; i < arrayLength; i++) {
        for (int j = 0; j < arrayLength; j++) {
            if (arrayToSort[i] < arrayToSort[j]) {
                int aux = 0;
                aux = arrayToSort[i];
                arrayToSort[i] = arrayToSort[j];
                arrayToSort[j] = aux;
            }
        }
    }
}
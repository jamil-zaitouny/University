#include <iostream>
#include "Tests/ExtendedTest.h"
#include "Tests/ShortTest.h"

int main() {
    std::cout<<"short test\n";
    testAll();
    std::cout<<"long test\n";
    testAllExtended();
    return 0;
}
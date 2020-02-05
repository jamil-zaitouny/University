cmake_minimum_required(VERSION 3.10.2)
project(Lab4)

set(CMAKE_CXX_STANDARD 14)

add_executable(Lab4 main.cpp SLL.h Map/Map.cpp Map/Map.h Tests/ShortTest.cpp Tests/ShortTest.h Tests/ExtendedTest.cpp Tests/ExtendedTest.h)
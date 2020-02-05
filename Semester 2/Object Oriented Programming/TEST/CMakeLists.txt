cmake_minimum_required(VERSION 3.13)
project(TEST)

set(CMAKE_CXX_STANDARD 14)

add_executable(TEST main.cpp DynamicArray.h Domain/Population.h Domain/Population.cpp Repository/Repository.h Repository/Repository.cpp Controller/Controller.cpp Controller/Controller.h UI/UI.cpp UI/UI.h)
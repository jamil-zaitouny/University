//
// Created by Jamil on 4/7/2019.
//

#ifndef LAB5_6_DYNAMICARRAY_H
#define LAB5_6_DYNAMICARRAY_H
#include <iostream>

template <class T>
class DynamicArray {
private:
    T* elems;
    int capacity;
    int currentSize;
public:
    explicit DynamicArray(int capacity = 10);

    //returns the current capacity of the array
    int get_current_capacity(){ return this->capacity;}

    //returns the current length of the array
    int get_current_size(){ return this->currentSize;}


    //Resizes the array by a multiplier
    void resize(int multiplier = 2);


    //adds a new element to the end of the DynamicArray

    void addToEnd(T newElem);



    //adds a new element to a given position in a DynamicArray

    //throws exception if pos is not a valid position

    void addToPosition(int pos, T newElem);

    //Replaces item at position with another item
    void replaceAtPosition(int pos, T newElem);

    //removes an element from a given position

    //returns the removed element

    //throws exception if pos is not a valid position

    T remove(int pos);



    //returns an iterator for the DynamicArray

    T operator [] (int i);

    ~DynamicArray();
};

template<class T>
DynamicArray<T>::DynamicArray(int capacity): capacity(capacity) {
    this->currentSize = 0;
    this->elems = new T[this->capacity];
}



template<class T>
void DynamicArray<T>::addToEnd(T newElem) {
        if(this->elems == nullptr){throw std::string("The array has been freed! you can no longer add elements");}
    if(this->currentSize == this->capacity){
        resize();
    }
    this->elems[currentSize] = newElem;
    this->currentSize++;
}

template<class T>
void DynamicArray<T>::addToPosition(int pos, T newElem) {
    if(this->elems == nullptr){throw std::string("The array has been freed! you can no longer add elements");}
    if(this->currentSize == this->capacity){
        resize();
    }
    for(int i = currentSize - 1; i >= pos; i--){
        this->elems[i+1] = this->elems[i];
    }

    this->elems[pos] = newElem;

    this->currentSize++;
}

template<class T>
T DynamicArray<T>::remove(int pos) {
    //The couts print every single the array after every single step
    if(this->elems == nullptr){throw std::string("The array has been freed! you can no longer remove elements");}
//    for(int i = 0; i < this->currentSize; i++){
//        std::cout<<elems[i]<<" ";
//    }
//    std::cout<<std::endl;
    for(int i = pos; i <  currentSize - 1; i++){
        this->elems[i] = this->elems[i+1];
//        for(int i = 0; i < this->currentSize; i++){
//            std::cout<<elems[i]<<" ";
//        }
//        std::cout<< std::endl;
    }
//    std::cout<< std::endl;
    currentSize--;
}

template<class T>
void DynamicArray<T>::resize(int multiplier) {
    if(this->elems == nullptr){throw std::string("The array has been freed! you can no longer resize array");}

    this->capacity *= multiplier;
    this->elems = (T*)realloc(this->elems, sizeof(T)*capacity);
}

template<class T>
T DynamicArray<T>::operator[](int i) {
    if(this->elems == nullptr){throw std::string("The array has been freed! you can no longer perform array operations");}
    if(i < currentSize){
        return this->elems[i];
    }else{
        throw std::string("Index out of bounds error!");
    }
}

template<class T>
DynamicArray<T>::~DynamicArray() {
    delete [] this->elems;
    this->elems = nullptr;
}

template<class T>
void DynamicArray<T>::replaceAtPosition(int pos, T newElem) {
    this->elems[pos] = newElem;
}


#endif //LAB5_6_DYNAMICARRAY_H



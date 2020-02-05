#include <cstdlib>
#include <iostream>
#include "Bag.h"
#include <exception>

using namespace std;

BagIterator::BagIterator(const Container &c) :c(c) {
    currnetIndex = 0;
    currentSubIndex = 0;
}

Bag::Bag() {
    //    Best case: Theta(1)
    //    Worst case: Theta(1)
    //    Average case: Theta(1)
    this->capacity = -1;
    this->minimum = 0;
    this->maximum = 0;
    this->elems = nullptr;
}

void Bag::add(TElem e) {
    //    Best case: Theta(1)
    //    Worst case: Theta(N)
    //    Average case: O(N)
    if (this->capacity == -1) {
        this->capacity = 1;
        this->elems = (TElem*)malloc(sizeof(TElem)* capacity);
        this->minimum = e;
        this->maximum = e;
        this->elems[0] = 0;
    }

    if (e < minimum) {
        int oldCapacity = capacity;
        int *oldElems = elems;
        this->capacity = abs(maximum - e) + 1;
        this->elems = (TElem *)malloc(sizeof(TElem) * capacity);
        for (int i = 0; i < capacity; i++) {
            this->elems[i] = 0;
        }
        for (int i = 0; i < oldCapacity; i++) {
            elems[i + capacity - oldCapacity] = oldElems[i];
        }
        minimum = e;
    }
    else if (e > maximum) {
        int oldCapacity = this->capacity;
        int *oldElems = elems;
        this->capacity = abs(minimum - e) + 1;
        this->elems = (TElem *)malloc(sizeof(TElem) * capacity);
        for (int i = 0; i < capacity; i++) {
            this->elems[i] = 0;
        }for (int i = 0; i < oldCapacity; i++) {
            elems[i] = oldElems[i];
        }

        maximum = e;
    }

    this->elems[std::abs(minimum - e)]++;


    //std::cout << e << " value added\n";
//    std::cout << minimum << " minimum\n";
/*	for (int i = 0; i < capacity; i++) {
		std::cout << this->elems[i] << " ";
	}
	std::cout << "\n";
	*/
    //    for(int i = 0; i < capacity; i++){
    //        std::cout<<this->elems[i]<<" ";
    //    }
    //    std::cout<<"\n";
}

bool Bag::remove(TElem e) {
    //    Best case: Theta(1)
    //    Worst case: Theta(1)
    //    Average case: Theta(1)
    if (e >= minimum && e <= maximum && capacity != -1) {
        if (this->elems[std::abs(minimum - e)] != 0) {
            this->elems[std::abs(minimum - e)]--;
            return true;
        }
    }
    return false;
}

bool Bag::search(TElem e) const {
    //    Best case: Theta(1)
    //    Worst case: Theta(1)
    //    Average case: Theta(1)
    if (e >= minimum && e <= maximum && capacity != -1) {
        if (this->elems[abs(minimum - e)] != 0) {
            return true;
        }
    }
    return false;
}

int Bag::nrOccurrences(TElem e) const {
    //    Best case: Theta(1)
    //    Worst case: Theta(1)
    //    Average case: O(1)
    if (e >= minimum && e <= maximum && capacity != -1) {
        return this->elems[std::abs(minimum - e)];
    }
    return 0;
}

int Bag::size() const {
    //    Best case: Theta(1)
    //    Worst case: Theta(N)
    //    Average case: O(N)

    int sizeCounter = 0;

    if (capacity == -1) {
        return sizeCounter;
    }
    for (int i = minimum; i <= maximum; i++) {
        sizeCounter += elems[abs(minimum - i)];
    }

    return sizeCounter;
}

BagIterator Bag::iterator() const {
    //    Best case: Theta(1)
    //    Worst case: Theta(1)
    //    Average case: Theta(1)
    return BagIterator(*this);
}

bool Bag::isEmpty() const {
    //    Best case: Theta(1)
    //    Worst case: Theta(1)
    //    Average case: Theta(1)
    if (minimum == maximum) {
        return true;
    }
    return false;
}

Bag::~Bag() {
    //O(1)
    free(this->elems);
    this->elems = nullptr;
}

bool Bag::removeNrOfOccurences(TElem elem, int nrToRemove) {
    //    Best case: Theta(N+M)
    //    Worst case: Theta(N+M)
    //    Average case: Theta(N+M)

    if(nrOccurrences(elem) <= nrToRemove){
        nrToRemove = nrOccurrences(elem);
    }
    for(int i = 0; i < nrToRemove; i++){
        remove(elem);
    }
}


void BagIterator::first() {
    //    Best case: Theta(1)
    //    Worst case: Theta(N)
    //    Average case: O(N)
    int i = 0;
    while (i < c.capacity && c.elems[i] == 0) {
        i++;
    }
    currnetIndex = i;
}

void BagIterator::next() {
    //    Best case: Theta(1)
    //    Worst case: Theta(N)
    //    Average case: O(N)
    if (!valid()) {
        throw exception();
    }
    if (c.elems[currnetIndex] > currentSubIndex + 1) {
        currentSubIndex++;
        return;
    }
    currentSubIndex = 0;

    for (int i = currnetIndex+1; i < c.capacity; i++) {
        if (c.elems[i] != 0) {
            currnetIndex = i;
            return;
        }
        //i++;
    }
    currnetIndex = c.capacity;
}

bool BagIterator::valid() const {
    //    Best case: Theta(1)
    //    Worst case: Theta(1)
    //    Average case: Theta(1)
    if (c.elems == nullptr) {
        return false;
    }if (currnetIndex >= c.capacity) {
        return false;
    }
    return true;
}

TElem BagIterator::getCurrent() const {

    if (!valid()) {
        throw exception();
    }
    return currnetIndex + c.minimum;
}
//
// Created by Jamil on 6/2/2019.
//
#include <iostream>
#include <vector>
#ifndef LAB7_MAP_H
#define LAB7_MAP_H
#include <utility>
#include <stack>

#define NULL_TVALUE 349999999



class SortedMap;

typedef int TKey;
typedef int TValue;
typedef std::pair<TKey, TValue> TElem;
typedef bool(*Relation)(TKey, TKey);
typedef bool(*Condition)(TKey);


class Node {
private:
    int left = -1;
    int right = -1;
    int parent = -1;
private:
    TElem value{NULL_TVALUE, NULL_TVALUE};
public:
    int getParent() const {
        return parent;
    }

    void setParent(int parent) {
        Node::parent = parent;
    }

    Node() = default;

    Node(TKey key, TValue value) {
        this->value.first = key;
        this->value.second = value;
    }

    int getLeft() const {
        return left;
    }

    void setLeft(int left) {
        Node::left = left;
    }

    const TElem &getValue() const {
        return value;
    }

    void setValue(const TElem &value) {
        Node::value = value;
    }

    int getRight() const {
        return right;
    }

    void setRight(int right) {
        Node::right = right;
    }
};
class SMIterator{
friend class SortedMap;
private:

    //Constructor receives a reference of the container.
    const SortedMap& c;
    //after creation the iterator will refer to the first element of the container, or it will be invalid if the container is empty

    SMIterator(const SortedMap& c): c(c){}



    //contains a reference of the container it iterates over




    /* representation specific for the iterator*/
    std::stack<TElem> values;

public:

    SMIterator();

    //sets the iterator to the first element of the container

    void first();

    //moves the iterator to the next element

    //throws exception if the iterator is not valid

    void next();

    void traverse();

    //checks if the iterator is valid

    bool valid() const;



    //returns the value of the current element from the iterator

    // throws exception if the iterator is not valid

    TElem getCurrent() const;
};


class   SortedMap {
friend class SMIterator;
protected:

    //representation of SortedMap
    Relation r;
    Node* nodes;
    int capacity;
    int numberOfElements;
public:

    // implicit constructor

    SortedMap(Relation r){
        this->r = r;
        capacity = 20;
        numberOfElements = 0;
        nodes = new Node[capacity];
        for(int i = 0; i < capacity - 1; i++){
            auto newNode = Node{};
            newNode.setRight(i + 1);
            nodes[i] = newNode;
        }
        auto newNode = Node{};
        newNode.setRight(-1);
    }

    void resize();

    // adds a pair (key,value) to the map

    //if the key already exists in the map, then the value associated to the key is replaced by the new value and the old value is returned

    //if the key does not exist, a new pair is added and the value null is returned

    TValue add(TKey c, TValue v);

    void filter(Condition c);

    //searches for the key and returns the value associated with the key if the map contains the key or null: NULL_TVALUE otherwise

    TValue search(TKey c) const;





    //removes a key from the map and returns the value associated with the key if the key existed or null: NULL_TVALUE otherwise

    TValue remove(TKey c);



    //returns the number of pairs (key,value) from the map

    int size() const;



    //checks whether the map is empty or not

    bool isEmpty() const;



    // return the iterator for the map

    // the iterator will return the keys following the order given by the relation

    SMIterator iterator() const;



    // destructor

    ~SortedMap();

};




#endif //LAB7_MAP_H

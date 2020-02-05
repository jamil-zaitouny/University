// Hello World.cpp : Defines the entry point for the console application.

#include "stdio.h"
#include <iostream>
#include <cmath>

void menu();
void allPrimeNumbersLessThanN();
void longestIncreasingContiguousSubsequence();
bool checkIfPrime(int n);

void allNPrimeNumbers();

#define ARRAYLENGTH 1000

int main()
{
	menu();
    return 0;
}

void menu() {
	// Asks the user for the functino that they want to use
	int userOption;
	printf("1. Generate all the prime numbers smaller n \n");
	printf("2. Given a vector of numbers, find the longest increasing contiguous subsequence, such the sum ");
	printf("of that any 2 consecutive elements is a prime number\n");
    printf("3. Generate the first n prime numbers:\n");
    printf("Choose the required action: ");
	scanf("%d", &userOption);
	if (userOption == 1) {
		allPrimeNumbersLessThanN();
		}
	else if (userOption == 2) {
		longestIncreasingContiguousSubsequence();
	}
	else if (userOption == 3){
	    allNPrimeNumbers();
	}
	else {
		printf("The value you entered is not one of the valid options\n");
		menu();
	}
}

void allNPrimeNumbers() {
    //gets the number of prime numbers that the user wants
    int numberOfPrimeNumbers = 0;
    printf("Choose the number of prime numbers that you want to get: ");
    scanf("%d", &numberOfPrimeNumbers);
    if (ARRAYLENGTH < numberOfPrimeNumbers){
        printf("The max number of primes is 1000");
        return;
    }

    int arrayOfNums[ARRAYLENGTH];
    arrayOfNums[0] = 2;
    int primeNumCounter = 1;

    //gets the prime number
    for(int i = 3; primeNumCounter <= numberOfPrimeNumbers; i++){
        int isPrime = 0;
        for(int j = 2; j <= i / 2; j++) {
            if(i % j == 0){
                isPrime = 1;
            }
        }
        if (isPrime == 0){
            arrayOfNums[primeNumCounter] = i;
            primeNumCounter++;

        }
    }

    for(int i = 0; i < numberOfPrimeNumbers; i++){
        printf("%d ",arrayOfNums[i]);
    }

}

void longestIncreasingContiguousSubsequence() {
	//gets the array from the user
	int arrayLength = 0;
	printf("What is the size of the array?");
	scanf("%d", &arrayLength);
	int arrayOfNums[ARRAYLENGTH];
	for (int i = 0; i < arrayLength; i++) {
		printf("Enter the number to add to the array: ");
		scanf("%d", &arrayOfNums[i]);
	}

	//find the longest sequennce
	int counter = 0;
	int maxCounter = 0;
	int maxFirstIndex = 0;
	int firstIndex = 0;
	int previousNum = 0;
	for (int i = 0; i < arrayLength; i++) {
		if (checkIfPrime(arrayOfNums[i] + previousNum)) {
			counter++;
			previousNum = arrayOfNums[i];
			if (counter > maxCounter) {
				maxCounter = counter;
				maxFirstIndex = firstIndex;
			}
		}
		else {
			counter = 0;
			previousNum = arrayOfNums[i];
			firstIndex = i;
		}
	}

	//checks if the sequence was found
	if (maxCounter == 0) {
		printf("No Such sequence exists\n");
	}
	else {
		for (int i = 0; i < maxCounter; i++) {
			printf("%d  (index [%d])\n", arrayOfNums[i + maxFirstIndex], i + maxFirstIndex);
		}
	}

}
bool checkIfPrime(int number) {
	//checks if a number is prime
	for (int i = 2; i <= number / 2; i++) {
		if (number % i == 0) {
			return false;
		}
	}
	return true;

}
void allPrimeNumbersLessThanN() {
	//prints all the prime numbers less than N
	int number;
	printf("Enter the number n: ");
	scanf("%d", &number);
	int isPrime = 0;
	for (int i = 1; i <= number; i++) {
		isPrime = 1;
		for (int j = 2; j < i; j++) {
			if (i % j== 0) {
				isPrime = 0;
			}
		}
		if(isPrime == 1) printf("%d ", i);

	}
	printf("\n");
}
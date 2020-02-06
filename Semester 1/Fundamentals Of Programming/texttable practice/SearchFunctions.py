from random import choice

array = []
for index in range(0, 100):
    array.append(choice(range(0, 1000)))


def BubbleSort(array):
    """
    Description:
    input:
    output:
    """
    done = False
    while not done:
        done = True
        for index in range(0, len(array) - 1):
            if array[index] > array[index + 1]:
                aux = array[index]
                array[index] = array[index + 1]
                array[index + 1] = aux
                done = False
    print(array)


BubbleSort(array)
for index in range(0, 100):
    array.append(choice(range(0, 1000)))

def InsertionSort(arrayToSort):
    for index in range(1, len(arrayToSort)):  # since we want to swap an item with previous one, we start from 1
        nonIterableIndex = index  # becoz reducing i directly will mess our for loop, so we reduce its copy
        # nonIterableIndex instead
        temp = arrayToSort[nonIterableIndex]  # temp will be used for comparison with previous items, and
        #  it belongs sent to the place
        while nonIterableIndex > 0 and temp < arrayToSort[nonIterableIndex - 1]:
            # j>0 becano point going till arrayToSort[0] since there is no seat available on its left, for temp
            arrayToSort[nonIterableIndex] = arrayToSort[nonIterableIndex - 1]  # Move the bigger item 1 step right to
            #  make room for temp
            nonIterableIndex = nonIterableIndex - 1  # take arrayToSort[j] all the way left to the place
            # where it has a smaller/no value to its left.
        arrayToSort[nonIterableIndex] = temp
    print(arrayToSort)


InsertionSort(array)
for index in range(0, 100):
    array.append(choice(range(0, 1000)))
# Python program for implementation of MergeSort
# Python program for implementation of MergeSort

# Merges two subarrays of arr[].
# First subarray is arr[l..m]
# Second subarray is arr[m+1..r]
def merge(arr, l, m, r):
    n1 = m - l + 1 #
    n2 = r - m

    # create temp arrays
    L = [0] * n1
    R = [0] * n2

    # Copy data to temp arrays L[] and R[]
    for i in range(0, len(L)):
        L[i] = arr[l + i]

    for j in range(0, len(R)):
        R[j] = arr[m + 1 + j]

        # Merge the temp arrays back into arr[l..r]
    i = 0  # Initial index of first subarray
    j = 0  # Initial index of second subarray
    k = l  # Initial index of merged subarray

    while i < len(L) and j < len(R):
        if L[i] <= R[j]:
            arr[k] = L[i]
            i += 1
        else:
            arr[k] = R[j]
            j += 1
        k += 1

    # Copy the remaining elements of L[], if there
    # are any
    while i < len(L):
        arr[k] = L[i]
        i += 1
        k += 1

    # Copy the remaining elements of R[], if there
    # are any
    while j < len(R):
        arr[k] = R[j]
        j += 1
        k += 1


# l is for left index and r is right index of the
# sub-array of arr to be sorted
def mergeSort(arr, l, r):
    if l < r:
        # Same as (l+r)/2, but avoids overflow for
        # large l and h
        m = (l + (r - 1)) // 2

        # Sort first and second halves
        mergeSort(arr, l, m)
        mergeSort(arr, m + 1, r)
        merge(arr, l, m, r)


mergeSort(array, 0, len(array) - 1)
print(array)




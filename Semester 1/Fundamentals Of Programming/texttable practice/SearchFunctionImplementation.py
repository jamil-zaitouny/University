def sequentialSort(arrayToSearch, item):
    """
    Description:
    input:
    output:
    """
    for index in range(0, len(arrayToSearch)):
        if arrayToSearch[index] == item:
            return "The item " + str(item ) + " is at the index: " + str(index + 1)
def BinarySearch(arrayToSearch, item, start = 0, end = None):
    if end == None:
        end = len(arrayToSearch) - 1
    if start > end:
        return "Item not in list"

    mid = (start + end) // 2

    if arrayToSearch[mid] == item:
        return "The index of the item is: " + str(mid)
    elif arrayToSearch[mid] > item:
        return BinarySearch(arrayToSearch, item, start, mid - 1)
    elif arrayToSearch[mid] < item:
        return BinarySearch(arrayToSearch, item, mid + 1, end)

def itterativeBinarySearch(arrayToSearch, item):
    start = 0
    end = len(arrayToSearch) - 1


    while start <= end:
        mid = (start + end) // 2
        if arrayToSearch[mid] == item:
            return "The value is at the index: " + mid
        elif arrayToSearch[mid] > item:
            end = mid - 1
        elif arrayToSearch[mid] < item:
            start = mid + 1

    return "The item you entered is not in the list"



arrayToSearch = [1,2,3,4,5,6,7,8,9,10,11,12]

print(sequentialSort(arrayToSearch, 12))
print(BinarySearch(arrayToSearch, 12))
print(sequentialSort(arrayToSearch, 12))

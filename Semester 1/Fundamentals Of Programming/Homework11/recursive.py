import unittest


def backtracking(numberOfDigits: int, sequence=None, checker=0, index=0, stack = None):
    """
    Description: takes a number and then gets all the sequences without consecutive subsequences
    """
    numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9]

    if sequence == None:
        print(sequence)

    else:
        numbers.pop(sequence[-1] - 1)

    # Base case for recursive formula
    if numberOfDigits == 0:
        stack.append(sequence)
        return stack


    for possibleNumber in numbers:
        if possibleNumber in sequence:
            # Checks to see if a subsequences exists

            sequence.append(possibleNumber)
            if sequence[index + 1] != possibleNumber or sequence[len(sequence) - 1 - checker] == possibleNumber:
                checker += 1
                backtracking(numberOfDigits - 1, sequence, checker,len(sequence) - list(reversed(sequence)).index(possibleNumber) - 1)
            sequence.pop(-1)
            checker = 0
            index = 0
        else:
            sequence.append(possibleNumber)
            backtracking(numberOfDigits - 1, sequence,)
            sequence.pop(-1)

for items in backtracking(2):
    print(items)
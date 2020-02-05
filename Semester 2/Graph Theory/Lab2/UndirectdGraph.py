import sys
from queue import Queue


class UndirectedGraph(object):
    def __init__(self):
        self.edges = {}
        self.costs = {}
        self.numberOfEdges = 0
        self.numberOfVertices = 0

    def add_edge(self, vertex1, vertex2, cost=1):
        # If the key holds no values, put an empty list at it's index
        self.add_vertex(vertex1)
        self.add_vertex(vertex2)

        # Check if the edges exists
        if vertex2 in self.edges[vertex1]:
            return

        # Adds edge to the dictinoary
        if vertex2 not in self.edges[vertex1]:
            self.edges[vertex1].append(vertex2)
            self.edges[vertex2].append(vertex1)

        self.costs[(vertex1, vertex2)] = cost
    def is_edge(self, vertex1, vertex2):
        if vertex2 in self.edges[vertex1]:
            return True
        return False
    def add_vertex(self, vertex):
        if vertex not in self.edges.keys():
            self.edges[vertex] = []

    def remove_vertex(self, vertex):
        for vertices in self.edges[vertex]:
            self.edges[vertices].remove(vertex)
        del self.edges[vertex]

    def remove_edge(self, vertex1, vertex2):
        # removing the cost

        # removing the edge
        self.edges[vertex1].remove(vertex2)
        self.edges[vertex2].remove(vertex1)

        for key in self.costs.keys():
            if key == (vertex1, vertex2):
                del self.costs[key]
                return
            elif key == (vertex2, vertex1):
                del self.costs[key]
                return

    def adjacent(self, vertex1, vertex2):
        # Python evaluates operations lazily and hence it won't look for vertex2 if vertex1 does not exists
        if vertex1 in self.edges.keys() and vertex2 in self.edges[vertex1]:
            return True
        return False

    def neighbours(self, vertex):
        if vertex in self.edges.keys():
            return self.edges[vertex]

    def set_edge_cost(self, vertex1, vertex2, newCost):
        for key in self.costs.keys():
            if (vertex1, vertex2) == key:
                self.costs[key] = newCost
                return
            if (vertex2, vertex1) == key:
                self.costs[key] = newCost
                return
        raise ValueError("The edge does not exist!")

    def get_edge_cost(self, vertex1, vertex2):
        for key in self.costs.keys():
            if (vertex1, vertex2) == key:
                return self.costs[key]
            if (vertex2, vertex1) == key:
                return self.costs[key]

        raise ValueError("The edge does not exists!")

    def get_degree(self, vertex):
        if vertex in self.edges.keys():
            return len(self.edges[vertex])

    def printable(self):
        # Parses the graph
        printableText = ""
        for keys, costs in self.costs:
            printableText += "The vertices " + str(keys[0]) + " and " + str(keys[1]) + "Define an edge that has the " \
                                                                                       "cost: " + costs + "\n "
        return printableText

    def connected_components(self, allConnectedComponents = None, visited = None):
        # checks if this is the first time the function is being called, if so it initialized the dictionary visited
        # and the list of lists allConnectedComponents
        if allConnectedComponents == None:
            allConnectedComponents = []
            visited = {}
            for keys in self.edges.keys():
                visited[keys] = False
        # initalized the list connectedComponents that holds the connected components 
        connectedComponents = []
        queue = []
        AllVisited = True
        for i in self.edges.keys():
            if visited[i] == False:
                queue.append(i)
                visited[i] = True
                connectedComponents.append(i)
                AllVisited = False
                break

        #if no new node is visited, then all nodes have been visited
        if AllVisited == True:
            return allConnectedComponents
        #goes through the array until the whole
        while(queue != []):
            v = queue.pop()
            for items in self.edges[v]:
                if visited[items] == False:
                    visited[items] = True
                    queue.append(items)
                    connectedComponents.append(items)

        allConnectedComponents.append(connectedComponents)
        return self.connected_components(allConnectedComponents, visited)

    def printable_connected_components(self):
        connectComponents = self.connected_components()
        printable = ""
        for items in connectComponents:
            for numbers in items:
                printable += str(numbers)
                printable += " "

            printable += "\n\n"

        return printable


    def shortest_walk(self, vertex1,vertex2):
        # sets max variable (infinity)
        max = 999999
        # distance dictionary that holds the cost adn teh previous vertex
        distance = {}
        for items in self.edges.keys():
            distance[items] = [max, None]
        distance[vertex1] = [0, None]
        queue = [vertex1]
        allWasVisited = False
        while queue != []:
            #puts the first element of the queue in the currentVertex Variable
            currentVertex = queue.pop()
            # loops over all the edges of the currentVertex variable
            for items in self.edges[currentVertex]:
                # checks if the distance from the root vertex, vertex1 is
                # shorter than the distance from the currentVertex
                if distance[currentVertex][0] + self.get_edge_cost(currentVertex,items ) < distance[items][0]:
                    # if the distance from the currnet vertex is shorter, you add the item to the queue and you
                    # change the distance
                    queue.append(items)
                    distance[items] = [distance[currentVertex][0] + self.get_edge_cost(currentVertex, items), currentVertex]
        # creates a list with the shortest walk and the total cost
        shortestWalk = [vertex2]
        # creates a variable item
        item = vertex2
        # looping until item does not have a previous vertex (reaches root vertex)
        while distance[item][1] != None:
            item = distance[item][1]
            shortestWalk.append(item)

        return [shortestWalk, distance[vertex2][0]]

    def printable_shortes_walk(self, vertex1, vertex2):
        values = self.shortest_walk(vertex1, vertex2)
        print("The shortest walk goes from")
        for items in values[0]:
            print(str(items) + " ")
        print("The cost is: " + str(values[1]))


    def prim(self):
        currentItem =



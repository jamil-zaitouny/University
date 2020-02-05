from DirectedGraph import DirectedGraph


class UI(object):
    def __init__(self):
        self.fileName = input("Please input the fileName")
        self.directedGraph = DirectedGraph(self.fileName)
        self.directedGraph.initalizeLists()

    def start(self):
        self.copy_or_no()
        self.main()
    def copy_or_no(self):
        print("1. Yes")
        print("2. No")
        userOption = int(input("Do you want the current changes to affect the file?"))
        self.directedGraph.copy = userOption;
        self.main()
    def main(self):
        print("1. add edge")
        print("2. remove edge")
        print("3. remove vertex")
        print("4. add vertex")
        print("5. modify edge cost")
        print("6. get number of vertices")
        print("7. check if 2 vertices have an edge")
        print("8. print in degree of vertex")
        print("9. print verteices")
        print("10. print edges of a vertex")
        print("11. get edge with cost")
        userOption = input("What do you want to do?")
        options = {
            "1":self.add_edge,
            "2":self.remove_edge,
            "3":self.remove_vertex,
            "4":self.add_vertex,
            "5":self.modify_edge_cost,
            "6":self.get_number_vertices,
            "7":self.check_if_2_vertices_have_edge,
            "8":self.print_indegree_of_vertex,
            "10":self.print_edges_of_vertex,
            "9":self.print_vertices,
            "11":self.get_edge_with_cost
        }
        options[userOption]()

    def add_edge(self):
        vertex1 = int(input("Vertex1: "))
        vertex2 = int(input("Vertex2: "))
        cost = int(input("Enter the cost of the edge"))
        self.directedGraph.add_edge(vertex1, vertex2, cost)

    def remove_edge(self):
        vertex1 = int(input("Vertex1: "))
        vertex2 = int(input("Vertex2: "))
        self.directedGraph.remove_edge(vertex1, vertex2)

    def remove_vertex(self):
        vertex = int(input("Vertex: "))
        self.directedGraph.remove_vertex(vertex)

    def add_vertex(self):
        vertex = int(input("Vertex: "))
        self.directedGraph.add_vertex(vertex)

    def get_edge_with_cost(self):
        vertex1 = int(input("Vertex1: "))
        vertex2 = int(input("Vertex2: "))
        self.directedGraph.retrieveEdgeWithCost(vertex1, vertex2)

    def print_vertices(self):
        self.directedGraph.print_vertices()

    def print_edges_of_vertex(self):
        vertex = int(input("Vertex: "))
        self.directedGraph.print_edges_of_vertex(vertex)

    def modify_edge_cost(self):
        vertex1 = int(input("Vertex1: "))
        vertex2 = int(input("Vertex2: "))
        self.directedGraph.modify_edge(vertex1, vertex2)

    def get_number_vertices(self):
        print(self.directedGraph.get_number_of_vertices() + "\n")

    def check_if_2_vertices_have_edge(self):
        vertex1 = int(input("Vertex1: "))
        vertex2 = int(input("Vertex2: "))
        self.directedGraph.check_if_vertices_have_edge(vertex1, vertex2)

    def print_indegree_of_vertex(self):
        vertex = int(input("Vertex: "))
        self.directedGraph.check_in_degree(vertex)



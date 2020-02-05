import java.nio.file.Path;

public interface DirectedGraph {
    int get_number_of_vertices();
    void parse_vertices();
    void get_edge_between_vertices(Vertex vertex1, Vertex vertex2);
    void get_indegree_and_outdegree(Vertex vertex);
    void parse_outbound_edges_of_vertex(Vertex vertex);
    void parse_inbound_edges_of_vertex(Vertex vertex);
    void get_endpoint_of_edge(Edge edge);
    void retrieve_modify_edges(Edge edge);
    void modify_graph();
    void make_copy_of_graph(String newFileName);

}

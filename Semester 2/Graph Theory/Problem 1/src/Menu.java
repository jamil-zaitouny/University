import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private Scanner scan = new Scanner(System.in);
    private String fileName;
    DirectedGraphImp graph;
    Menu() throws IOException {
        System.out.print("Please enter the fileName: ");
        this.fileName = scan.nextLine();
        graph = new DirectedGraphImp(this.fileName);
    }

    void main_options(){
        System.out.println("Here are the available options: ");
        System.out.println("1. Get the number of vertices.");
        System.out.println("2. Parse the set of vertices from a file.");
        System.out.println("3. Find if there's an edge between two given vertices.");
        System.out.println("4. Get the in-degree and out-degree of a vertex.");
        System.out.println("5. Parse the set of outbound edges of a vertex.");
        System.out.println("6. Parse the set of inbound edges of a vertex.");
        System.out.println("7. Get the endpoints of ane edge.");
        System.out.println("8. retrieve or modify the information attached to a specified edge.");
        System.out.println("9. Options to modify graph.");
        System.out.println("10. Make a test copy of the current graph.");
        System.out.println("11. Go back to previous version of graph.");
        System.out.println("Please input the number associated with the operation that you would like to perform: ");
        int option = scan.nextInt();

        switch (option){
            case 1:

                    print_number_of_vertices();

                    break;
            case 2:
                parse_vertices();
                    break;
            case 3:
                edge_between_vertices();
                    break;
            case 4:
                in_out_vertex();
                    break;
            case 5:
                parse_outbound_edges();
                    break;
            case 6:
                parse_inbound_edges();
                    break;
            case 7:
                get_edge_endpoints();
                    break;
            case 8:
                retrieve_or_modify_edge();
                    break;
            case 9: edit_graph_submenu();
                    break;
            case 10:
                make_test_copy_of_current_graph();
                    break;
            case 11:
                reset_to_old_graph();
                    break;
            default:
                System.out.println("Invalid operation!");
                System.out.println("Please input a valid number from the list!");
                main_options();

        }
    }

    void parse_vertices() {
    }

    void edge_between_vertices() {
    }

    void in_out_vertex() {
    }

    void parse_outbound_edges() {
    }

    void parse_inbound_edges() {
    }

    void get_edge_endpoints() {
    }

    void retrieve_or_modify_edge() {
    }

    void make_test_copy_of_current_graph() {
    }

    void reset_to_old_graph() {
    }

    void print_number_of_vertices() {

    }

    void edit_graph_submenu(){
        System.out.println("What do you want to edit?");
        System.out.println("1. add an edge");
        System.out.println("2. remove an edge");
        System.out.println("3. add an vertex");
        System.out.println("4. remove an vertex");
    }
}

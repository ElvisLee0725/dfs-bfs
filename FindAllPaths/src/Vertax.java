import java.util.List;

class Vertex {
    Character id;
    List<Vertex> edges;
    public Vertex(Character id, List<Vertex> edges) {
        this.id = id;
        this.edges = edges;
    }
}

package MyMath.graph;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Denis on 17.11.2016.
 */
public class Vertex {

    private int id;
    private HashSet<Edge> edges = new HashSet<>();

    public Vertex(int id) {
        this.id = id;
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

package MyMath.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Denis on 17.11.2016.
 */
public class DirectedGraph {

    static VertexFactory vertexFactory = new VertexFactory();
    HashMap<Integer, Vertex> vertices = new HashMap<>();
    HashMap<Integer, Integer> verticesIdMap = new HashMap<>();

    public DirectedGraph() {
    }

    public Vertex getVertex(int id) {
        return vertices.get(verticesIdMap.get(id));
    }

    public Vertex addVertex(int id) {
        Vertex v = vertexFactory.create();
        vertices.put(v.getId(), v);
        verticesIdMap.put(id, v.getId());
        return v;
    }

    public Edge addEdge(Vertex from, Vertex to, double weight) {
        Edge e = new Edge(from, to, weight);
        from.addEdge(e);
        return e;
    }

}

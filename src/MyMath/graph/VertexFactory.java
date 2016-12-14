package MyMath.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Denis on 17.11.2016.
 */
public class VertexFactory {

    private int currentId = 0;
    HashMap<Integer, Vertex> vertexHashMap = new HashMap<>();

    public VertexFactory() {
    }

    public Vertex get(int i) {
        if (!vertexHashMap.containsKey(i)) {
            vertexHashMap.put(i, new Vertex(i));
        }
        return vertexHashMap.get(i);
    }

    public Vertex create() {
        Vertex v = new Vertex(currentId);
        vertexHashMap.put(currentId, v);
        currentId++;
        return v;
    }

}

package MyMath.graph;

import java.util.HashMap;

/**
 * Created by Denis on 17.11.2016.
 */
public abstract class DirectedGraphFactory {

    public static DirectedGraph create(double[][] weights) {
        DirectedGraph graph = new DirectedGraph();
        HashMap<Integer, Vertex> vertices = new HashMap<>();
        HashMap<Integer, Integer> idMap = new HashMap<>();
        int vertexesCount = weights.length;
        for (int i = 0; i < vertexesCount; i++) {
            Vertex v = graph.addVertex(i);
            vertices.put(v.getId(), v);
            idMap.put(i, v.getId());
        }

        for (int i = 0; i < vertexesCount; i++) {
            for (int j = 0; j < vertexesCount; j++) {
                if (weights[i][j] > 0.0) {
                    graph.addEdge(vertices.get(idMap.get(i)),
                            vertices.get(idMap.get(j)), weights[i][j]);
                }
            }
        }

        return graph;
    }


}

package MyMath.graph;

/**
 * Created by Denis on 17.11.2016.
 */
public class Edge {

    private Vertex from;
    private Vertex to;
    private double weight;

    public Edge(Vertex from, Vertex to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

}

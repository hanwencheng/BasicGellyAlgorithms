package gelly.algorithms;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.graph.Graph;
import org.apache.flink.graph.Vertex;

import java.util.List;

/**
 * Created by hanwencheng on 1/18/16.
 */
public class BFS {
    DataSet nodeList;
    DataSet edgeList;

    private static BFS instance = null;

    protected BFS(){
    }

    public static gelly.algorithms.BFS getInstance() {
        if(instance == null){
            return new BFS();
        }
        return instance;
    }

    public List printVerteces(Graph graph, Vertex startNode){
        nodeList = graph.getVertices();
        edgeList = graph.getEdges();
    }
}

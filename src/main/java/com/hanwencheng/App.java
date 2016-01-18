package com.hanwencheng;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.graph.Edge;
import org.apache.flink.graph.Graph;
import org.apache.flink.graph.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by hanwencheng on 1/16/16.
 */
public class App {

    private final static Logger Log = Logger.getLogger(App.class.getName());

    public static void main(String [ ] args)
    {

        ArrayList<Vertex<Long, Position>> nodeList = new ArrayList<Vertex<Long, Position>>();
        ArrayList<Edge<Long, Object>> edgeList = new ArrayList<Edge<Long, Object>>();

        List all = new ArrayList();
        addNode(1L, 1, 1, nodeList);
        addNode(2L, 2, 1, nodeList);
        addNode(3L, 3, 1, nodeList);
        addNode(4L, 1, 2, nodeList);
        addNode(5L, 2, 2, nodeList);

        addEdge(1L, 2L, "I am fine", edgeList);
        addEdge(3L, 2L, 0.5, edgeList);
        addEdge(4L, 1L, 5, edgeList);
        addEdge(2L, 4L, new Position(5,5), edgeList);
        addEdge(5L, 4L, true, edgeList);

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        Graph<Long, Position, Object> graph = Graph.fromCollection(nodeList, edgeList, env);

        Log.info("graph is" + graph);
    }

    private static boolean addNode(Long vertexId, int x, int y, ArrayList<Vertex<Long, Position>> vertices){
        Position position = new Position(x, y);
        Vertex<Long, Position> vertexTopLeft = new Vertex<Long, Position>(vertexId, position);
        return vertices.add(vertexTopLeft);
    }

    private static boolean addEdge(Long sourceId, Long targetId, Object value, ArrayList<Edge<Long, Object>> edges){
        Edge<Long, Object> edge = new Edge<Long, Object>(sourceId, targetId, value);
        return edges.add(edge);
    }
}

/**
 * Created by hanwencheng on 1/16/16.
 * The introduction application
 */

package com.hanwencheng;

import gelly.algorithms.BFS;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.graph.Edge;
import org.apache.flink.graph.Graph;
import org.apache.flink.graph.Vertex;

import java.util.ArrayList;
import java.util.logging.Logger;

public class App {

    private final static Logger Log = Logger.getLogger(App.class.getName());

    public static void main(String [ ] args) throws Exception {
        Graph<Long, Position, Integer> graph = buildGraph();

        BFS walker = BFS.getInstance(false);

        Log.info("========start test=========");
//        Log.info("visited list is" + walker.walk(graph, 2L));

        Log.info("========start bi-direction test=========");
        walker = BFS.getInstance(true);
        Log.info("visited list is" + walker.walk(graph, 5L));
    }

    public static Graph<Long, Position, Integer> buildGraph(){
        ArrayList<Vertex<Long, Position>> nodeList = new ArrayList<>();
        //cannot set Object as edge value here,
        ArrayList<Edge<Long, Integer>> edgeList = new ArrayList<>();
        addNode(1L, 1, 1, nodeList);
        addNode(2L, 2, 1, nodeList);
        addNode(3L, 3, 1, nodeList);
        addNode(4L, 1, 2, nodeList);
        addNode(5L, 2, 2, nodeList);

        addEdge(1L, 2L, 2, edgeList);
        addEdge(3L, 2L, 1, edgeList);
        addEdge(4L, 1L, 5, edgeList);
        addEdge(2L, 4L, 3, edgeList);
        addEdge(5L, 4L, 4, edgeList);
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        Graph<Long, Position, Integer> graph = Graph.fromCollection(nodeList, edgeList, env);
        Log.info("graph is" + nodeList + edgeList);
        return graph;
    }

    private static boolean addNode(Long vertexId, int x, int y, ArrayList<Vertex<Long, Position>> vertices){
        Position position = new Position(x, y);
        Vertex<Long, Position> vertex = new Vertex<>(vertexId, position);
        return vertices.add(vertex);
    }

    private static boolean addEdge(Long sourceId, Long targetId, Integer value, ArrayList<Edge<Long, Integer>> edges){
        Edge<Long, Integer> edge = new Edge<>(sourceId, targetId, value);
        return edges.add(edge);
    }
}

/**
 * Created by hanwencheng on 1/18/16.
 * The BFS algorithms we use
 */

package gelly.algorithms;

import com.hanwencheng.BFSNode;
import com.hanwencheng.Position;
import org.apache.flink.graph.Edge;
import org.apache.flink.graph.Graph;
import org.apache.flink.graph.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BFS {
    private static Logger Log;
    private static BFS instance = null;
    List<Vertex<Long, Position>> nodes;
    List<Edge<Long, Integer>> edges;
    BFSNode startNode = null;
    List<BFSNode> nodeList;
    ArrayList<BFSNode> queue;
    //if it is bi-directory graph
    private boolean BD;
    //the list saved the visited node
    List<BFSNode> visited = null;

    protected BFS(boolean BD){
        Log = Logger.getLogger(BFS.class.getName());
        nodeList = new ArrayList<>();
        queue = new ArrayList<>();
        this.BD = BD;
    }

    /**
     * public singleton constructor
     * @param BD whether we enable bi-direction BFS search
     * @return The BFS instance
     */
    public static gelly.algorithms.BFS getInstance(boolean BD) {
        if(instance == null){
            return new BFS(BD);
        }
        instance.setBD(BD);
        return instance;
    }

    private void setBD(boolean BD){
        this.BD = BD;
    }

    /**
     * function to start BFS walk
     * @param graph the Gelly graph object we use
     * @param startNodeId the id of start node
     * @return a List of node according to visit order
     * @throws Exception
     */
    public List<BFSNode> walk(Graph<Long, Position, Integer> graph, Long startNodeId) throws Exception {
        nodes = graph.getVertices().collect();
        edges = graph.getEdges().collect();
        visited = new ArrayList<>();

        nodes.stream().forEach(node -> nodeList.add(new BFSNode(node)));
        nodeList.stream().filter(node -> node.getId() == startNodeId).forEach(node -> startNode = node);
        if(startNode != null){
            initWalk();
        }
        return visited;
    }

    /* ================Followings are help function================ */

    /**
     * Set the start status of the BFS walk
     */
    private void initWalk(){
        startNode.setCost(0);
        startNode.setColor(BFSNode.GREY);
        queue.add(startNode);
        visited.add(startNode);

        while(!queue.isEmpty()){
            propagate(queue.get(0));
            queue.remove(0);
        }
    }

    /**
     * define the propagating action
     * @param origin the start node
     */
    private void propagate(BFSNode origin){
        Log.info("propagating: start at node: " +  origin.getId());
        // get the node with the same source
        edges.stream().filter(edge ->edge.getSource().equals(origin.getId())).forEach(edge -> {
            // for each output edge change the status of the node
            nodeList.stream().filter(node -> node.getId() == edge.getTarget()).forEach(target ->
                judgeValue(target, origin, edge));
        });

        // if it is bi-direction
        if(BD){
            edges.stream().filter(edge ->edge.getTarget().equals(origin.getId())).forEach(edge -> {
                // for each input edge change the status of the node
                nodeList.stream().filter(node -> node.getId() == edge.getSource()).forEach(source ->
                    judgeValue(source, origin, edge));
            });
        }
        // set the status of node to discovered
        if(!origin.getColor().equals(BFSNode.BLACK)) {
            origin.setColor(BFSNode.BLACK);
            Log.info("propagating: set to black, stop at node: " + origin.getId());
        }else {
            Log.info("propagating: stop at node: " + origin.getId());
        }
    }

    /**
     * define what to do when we arrive the new node based on the origin node
     * @param target the arriving node
     * @param origin the base node
     * @param edge the edge by which we arrive the target
     */
    private void judgeValue(BFSNode target, BFSNode origin, Edge<Long, Integer> edge){
        if(BD){
            // skip the black node
            if(target.getColor().equals(BFSNode.BLACK)) return;
        }
        if(target.getColor().equals(BFSNode.WHITE)){
            visited.add(target);
            target.setColor(BFSNode.GREY);
            Log.info("Arrive Target : set color from white to grey for node: " + target.getId());
        }
        int newValue = BD? origin.getCost() + 1 : origin.getCost() + edge.getValue();
        Log.info("Arrive Target : node " + target.getId() +"'s origin cost is: " + target.getCost());
        if(target.getCost() == BFSNode.INFINITY || target.getCost() > newValue) {
            target.setCost(newValue);
            target.setParent(origin);
            Log.info("Arrive Target : set the cost to new value: " + target.getCost() +
                    ", set the parent to new value: " + target.getParent().getId());
            //else if it is already discovered with a short path, ignore it
            queue.add(target);
        }
    }
}

package com.hanwencheng;

import gelly.algorithms.BFS;
import junit.framework.Assert;
import org.apache.flink.graph.Graph;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.NodeList;

import java.util.List;
import java.util.logging.Logger;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by hanwencheng on 1/24/16.
 */
public class TestGraph {

    private final static Logger Log = Logger.getLogger(App.class.getName());
    private static Graph<Long, Position, Integer> graph;
    private static BFS walker;

    @Test
    public void test() throws Exception {
        int sum = 1 + 2;
        String[] args = {};
        App.main(args);
        assertEquals (3, sum);
    }

    @BeforeClass
    public static void init(){
        graph = App.buildGraph();
        walker = BFS.getInstance(true);
    }

    @Test
    public void testNode1() throws Exception {
        testOrder(1L, new long[]{1L, 2L, 4L, 3L, 5L});
    }

    @Test
    public void testNode2() throws Exception {
        testOrder(2L, new long[]{2L, 4L, 1L, 3L, 5L});
    }

    @Test
    public void testNode3() throws Exception {
        testOrder(3L, new long[]{3L, 2L, 4L, 1L, 5L});
    }

    @Test
    public void testNode4() throws Exception{
        testOrder(4L, new long[]{4L, 1L, 2L, 5L, 3L});
    }

    @Test
    public void testNode5() throws Exception{
        testOrder(5L, new long[]{5L, 4L, 1L, 2L, 3L});
    }

    private void testOrder(long startNodeId, long[] order) throws Exception {
        List<BFSNode> visited = walker.walk(graph, startNodeId);
        for(int i = 0; i < 5 ; i++ ){
            assertEquals(visited.get(i).getId(), order[i]);
        }
    }

}

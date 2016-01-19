/**
 * Created by hanwencheng on 1/19/16.
 */

package com.hanwencheng;

import org.apache.flink.graph.Vertex;
public class BFSNode {
    public static String BLACK = "BFS.black";
    public static String GREY = "BFS.grey";
    public static String WHITE = "BFS.white";
    public static int INFINITY = -1;

    private int cost = INFINITY;
    private String color = WHITE;
    private BFSNode parent = null;
    private long id;
    private Position value;

    public BFSNode(Vertex<Long, Position> vertex) {
        this.id = vertex.getId();
        this.value = vertex.getValue();
    }

    public long getId() {
        return this.id;
    }

    public Position getValue() {
        return value;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setParent(BFSNode parent) {
        this.parent = parent;
    }

    public int getCost() {
        return cost;
    }

    public String getColor() {
        return color;
    }

    public BFSNode getParent() {
        return parent;
    }
}

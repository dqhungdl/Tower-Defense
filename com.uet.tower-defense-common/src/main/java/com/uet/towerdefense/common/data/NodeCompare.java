package com.uet.towerdefense.common.data;

import javafx.scene.Node;

public class NodeCompare implements Comparable<NodeCompare> {

    private Node node;

    public NodeCompare(Node node) {
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public int compareTo(NodeCompare nodeCompare) {
        int value1 = Integer.parseInt(node.getId());
        int value2 = Integer.parseInt(nodeCompare.getNode().getId());
        return Integer.compare(value1, value2);
    }
}

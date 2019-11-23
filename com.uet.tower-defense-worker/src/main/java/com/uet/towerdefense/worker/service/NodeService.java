package com.uet.towerdefense.worker.service;

import javafx.scene.Node;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeService {

    private List<Node> nodes;

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void add(Node tempNode) {
        for (Node node : nodes)
            if (node == tempNode)
                return;
        for (int i = 0; i < nodes.size(); i++)
            if (Integer.parseInt(nodes.get(i).getId()) > Integer.parseInt(tempNode.getId())) {
                nodes.add(i, tempNode);
                return;
            }
        nodes.add(tempNode);
    }

    public void remove(Node tempNode) {
        for (Node node : nodes)
            if (node == tempNode) {
                nodes.remove(node);
                break;
            }
    }

    public void remove(String renderId) {
        for (int i = 0; i < nodes.size(); i++)
            if (nodes.get(i).getId().equals(renderId))
                nodes.remove(i--);
    }
}

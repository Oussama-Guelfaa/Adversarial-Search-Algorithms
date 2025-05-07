package fr.emse.ai.util;

import java.util.ArrayList;
import java.util.List;

public class SimpleTwoPlyGameTree {

    private Integer value;
    private boolean max;
    private ArrayList<SimpleTwoPlyGameTree> children;

    public SimpleTwoPlyGameTree(Integer value, boolean max) {
        this.value = value;
        this.max = max;
        children = new ArrayList<SimpleTwoPlyGameTree>();
    }

    public SimpleTwoPlyGameTree(Integer value, boolean max, List<SimpleTwoPlyGameTree> children) {
        this(value, max);
        for (SimpleTwoPlyGameTree child : children)
            this.children.add(child);
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public boolean isMax() {
        return max;
    }

    public void addChild(SimpleTwoPlyGameTree child) {
        this.children.add(child);
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public ArrayList<SimpleTwoPlyGameTree> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<SimpleTwoPlyGameTree> children) {
        this.children = children;
    }

    public String toString() {
        String s = "";
        s += "value = " + value + " | ";
        s += "child = " + children;
        return s;
    }
}

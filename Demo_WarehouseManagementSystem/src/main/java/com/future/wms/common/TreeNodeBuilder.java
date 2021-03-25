package com.future.wms.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 把没有层级关系的集合变成有层级关系的集合
 * @author evanliu
 * @create 2021-03-25 18:42
 */
public class TreeNodeBuilder {
    public static List<TreeNode> build(List<TreeNode> treeNodes, Integer topPid) {
        List<TreeNode> nodes = new ArrayList<>();
        for (TreeNode n1 : treeNodes) {
            // 先找父节点
            if (topPid.equals(n1.getPid())) {
                nodes.add(n1);
            }
            // 再找子节点
            for (TreeNode n2 : treeNodes) {
                if (n1.getId().equals(n2.getPid())) {
                    n1.getChildren().add(n2);
                }
            }
        }
        return nodes;
    }
}

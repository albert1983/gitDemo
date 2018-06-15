package com.zhuoyue.vo;

import java.io.Serializable;

/**
 * 模型树结构
 *
 * @author 14258
 */
public class ModelTree implements Serializable {

    private static final long serialVersionUID = 8725299753077094238L;


    private String modelId;
    private Object tree;
    private String treeType;


    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public Object getTree() {
        return tree;
    }

    public void setTree(Object tree) {
        this.tree = tree;
    }

    public String getTreeType() {
        return treeType;
    }

    public void setTreeType(String treeType) {
        this.treeType = treeType;
    }
}

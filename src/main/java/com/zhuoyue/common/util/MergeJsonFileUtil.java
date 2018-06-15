package com.zhuoyue.common.util;
 
import java.util.List;


/**
 * @author 14258
 */
public class MergeJsonFileUtil {


   
}


  class Root {
    private Objects objects;

    public Objects getObjects() {
        return this.objects;
    }

    public void setObjects(Objects objects) {
        this.objects = objects;
    }
}


  class Objects {
    private String num;

    private List<Child> object;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public List<Child> getObject() {
        return object;
    }

    public void setObject(List<Child> object) {
        this.object = object;
    }
}


class Child {

    private String index;

    private String name;

    private String pos;

    private String dir;

    private String min;

    private String max;

    private String style;

    private String scale;

    private String isObj;


    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getIsObj() {
        return isObj;
    }

    public void setIsObj(String isObj) {
        this.isObj = isObj;
    }

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPos() {
        return this.pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getDir() {
        return this.dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getMin() {
        return this.min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return this.max;
    }

    public void setMax(String max) {
        this.max = max;
    }
}



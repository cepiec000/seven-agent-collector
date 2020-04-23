package com.seven.agent.collector.bean.dto;

import java.util.List;

public class SeriesDTO {
    private String name;
    private String type;
    private List data;
    private boolean smooth=false;
    private Object itemStyle;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public boolean isSmooth() {
        return smooth;
    }

    public void setSmooth(boolean smooth) {
        this.smooth = smooth;
    }

    public Object getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(Object itemStyle) {
        this.itemStyle = itemStyle;
    }
}

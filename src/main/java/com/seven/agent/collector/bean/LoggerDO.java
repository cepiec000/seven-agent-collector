package com.seven.agent.collector.bean;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "logger")
public class LoggerDO extends BaseDO {
    private String message;
    private String throwable;
    private String level;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getThrowable() {
        return throwable;
    }

    public void setThrowable(String throwable) {
        this.throwable = throwable;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return this.getCreateDate() + " " + this.getMessage() + " " + level + " " + (level.equals("ERROR") ? throwable : "");
    }
}

package com.seven.agent.collector.exception;

public class AgentCollectorException extends RuntimeException {

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public AgentCollectorException(String message){
        super(message);
        this.message=message;
    }
}

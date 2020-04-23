package com.seven.agent.collector.bean;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jvmInfo")
public class JvmInfoDO extends BaseDO{
    private String javaVersion;
    private String jreHome;
    private String javaVmVersion;
    private String javaVmName;
    private String javaClassPath;
    private String javaLibraryPath;
    private String javaVmArguments;

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public String getJreHome() {
        return jreHome;
    }

    public void setJreHome(String jreHome) {
        this.jreHome = jreHome;
    }

    public String getJavaVmVersion() {
        return javaVmVersion;
    }

    public void setJavaVmVersion(String javaVmVersion) {
        this.javaVmVersion = javaVmVersion;
    }

    public String getJavaVmName() {
        return javaVmName;
    }

    public void setJavaVmName(String javaVmName) {
        this.javaVmName = javaVmName;
    }

    public String getJavaClassPath() {
        return javaClassPath;
    }

    public void setJavaClassPath(String javaClassPath) {
        this.javaClassPath = javaClassPath;
    }

    public String getJavaLibraryPath() {
        return javaLibraryPath;
    }

    public void setJavaLibraryPath(String javaLibraryPath) {
        this.javaLibraryPath = javaLibraryPath;
    }

    public String getJavaVmArguments() {
        return javaVmArguments;
    }

    public void setJavaVmArguments(String javaVmArguments) {
        this.javaVmArguments = javaVmArguments;
    }

}

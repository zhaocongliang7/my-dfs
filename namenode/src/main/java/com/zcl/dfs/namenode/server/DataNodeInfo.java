package com.zcl.dfs.namenode.server;

public class DataNodeInfo {
    private String ip;
    private String hostName;

    public DataNodeInfo(String ip, String hostName) {
        this.ip = ip;
        this.hostName = hostName;
    }

    public String getIp() {
        return ip;
    }

    public String getHostName() {
        return hostName;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @Override
    public String toString() {
        return "DataNodeInfo{" +
                "ip='" + ip + '\'' +
                ", hostName='" + hostName + '\'' +
                '}';
    }
}

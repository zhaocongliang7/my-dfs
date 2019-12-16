package com.zcl.dfs.namenode.server;

public class NameNodeRpcServer {

    private FSNamesystem fsNamesystem;

    private DataNodeManager dataNodeManager;

    public NameNodeRpcServer(FSNamesystem fsNamesystem,DataNodeManager dataNodeManager){
        this.fsNamesystem = fsNamesystem;
        this.dataNodeManager = dataNodeManager;
    }

    public Boolean mkdir (String path) throws Exception {
        return this.fsNamesystem.mkdir(path);
    }

    public Boolean register (String ip, String hostName) {
        return this.dataNodeManager.register(ip, hostName);
    }

    public void start() {
        System.out.println("开始监听指定的rpc server的端口号，来接收请求");
    }
}

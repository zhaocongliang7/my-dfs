package com.zcl.dfs.namenode.server;

public class NameNode {

    private volatile boolean shouldRun;

    private FSNamesystem fsNamesystem;

    private DataNodeManager dataNodeManager;

    private NameNodeRpcServer nameNodeRpcServer;

    private void initialize(){
        this.shouldRun = true;
        this.fsNamesystem = new FSNamesystem();
        this.dataNodeManager = new DataNodeManager();
        this.nameNodeRpcServer = new NameNodeRpcServer(this.fsNamesystem,this.dataNodeManager);
        this.nameNodeRpcServer.start();
    }

    private void run(){

        try {
            while (shouldRun) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NameNode nameNode = new NameNode();
        nameNode.initialize();
        nameNode.run();
    }
}

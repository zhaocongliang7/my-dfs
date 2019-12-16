package com.zcl.dfs.datanode.server;

public class DataNode {

    private volatile boolean shouldRun;

    private NameNodeExchange nameNodeExchange;

    private void initialize(){
        this.shouldRun = true;
        this.nameNodeExchange = new NameNodeExchange();
        this.nameNodeExchange.registry();
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
        DataNode dataNode = new DataNode();
        dataNode.initialize();
        dataNode.run();
    }

}

package com.zcl.dfs.datanode.server;

public class NameNodeExchange {

    public void start() {
        registry();
    }

    public void registry() {
       RegistryThread registryThread = new RegistryThread();
       registryThread.start();
    }

    class RegistryThread extends Thread {
        @Override
        public void run() {
            try {
                System.out.println("发送RPC请求到NameNode进行注册.......");
                String ip = "127.0.0.1";
                String hostname = "dfs-data-01";
                // 通过RPC接口发送到NameNode他的注册接口上去

                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

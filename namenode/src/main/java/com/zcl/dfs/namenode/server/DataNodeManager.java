package com.zcl.dfs.namenode.server;

import java.util.ArrayList;

public class DataNodeManager {

    private ArrayList<DataNodeInfo> dataNodeInfos = new ArrayList<DataNodeInfo>();

    public Boolean register(String ip, String hostName) {
        DataNodeInfo dataNodeInfo = new DataNodeInfo(ip,hostName);
        dataNodeInfos.add(dataNodeInfo);
        return true;
    }
}

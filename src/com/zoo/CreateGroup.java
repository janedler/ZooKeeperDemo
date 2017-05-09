package com.zoo;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * Created by janedler on 2017/5/9.
 */
public class CreateGroup extends ConnectionWatcher {

    private void create(String groupName) throws KeeperException, InterruptedException {
        String path="/"+groupName;
        if(zk.exists(path, false)== null){
            zk.create(path, null/*data*/, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        System.out.println("Created:"+path);
    }


    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        CreateGroup createGroup = new CreateGroup();
        createGroup.connect("192.168.179.150");
        createGroup.create("zoo");
        createGroup.close();
    }

}

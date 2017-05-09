package com.zoo;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.List;

/**
 * Created by janedler on 2017/5/9.
 */
public class DeleteGroup extends ConnectionWatcher {

    public void delete(String groupName) throws InterruptedException, KeeperException {
        String path="/"+groupName;
        List<String> children;
        try {
            children = zk.getChildren(path, false);
            for(String child:children){
                zk.delete(path+"/"+child, -1);
            }
            zk.delete(path, -1);
        } catch (KeeperException.NoNodeException e) {
            System.out.printf("Group %s does not exist\n", groupName);
            System.exit(1);
        }
    }
    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        DeleteGroup deleteGroup = new DeleteGroup();
        deleteGroup.connect("192.168.179.150");
        deleteGroup.delete("zoo");
        deleteGroup.close();
    }

}

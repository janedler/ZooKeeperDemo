package com.zoo;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.server.ExitCode;
import sun.rmi.log.LogInputStream;

import java.io.IOException;
import java.util.List;

/**
 * Created by janedler on 2017/5/9.
 */
public class ListGroup extends ConnectionWatcher{

    public void list(String groupNmae) throws KeeperException,InterruptedException{
        String path = "/" + groupNmae;
        try {
            List<String> children = zk.getChildren(path,false);
            if (children.isEmpty()){
                System.out.printf("No memebers in group %s\n",groupNmae);
                System.exit(1);
            }
            for (String child:children) {
                System.out.println(child);
            }

        }catch (Exception e){
            System.out.printf("No memebers in group %s\n",groupNmae);
            System.exit(1);
        }

    }

    public static void main(String[] args)
            throws IOException, InterruptedException, KeeperException {
        ListGroup listGroup = new ListGroup();
        listGroup.connect("192.168.179.150");
        listGroup.list("zoo");
        listGroup.close();
    }

}

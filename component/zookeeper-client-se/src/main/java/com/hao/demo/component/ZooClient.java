package com.hao.demo.component;

import java.util.Random;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * 对 ZooKeeper 的封装，提供更加便捷的方法
 */
public class ZooClient {

    private static final int DEFAULT_SESSION_TIMEOUT = 30000;
    private static final Watcher EMPTY_WATCHER = event -> {};

    private ZooKeeper zooKeeper;

    /**
     * 私有化构造器
     */
    private ZooClient() {}

    /**
     * 从连接地址创建连接对象
     * @param connectString 链接地址
     * @return 自定义连接对象
     */
    public static ZooClient createInstance(String connectString) {
        try {
            ZooClient zooClient = new ZooClient();
            zooClient.zooKeeper = new ZooKeeper(connectString, DEFAULT_SESSION_TIMEOUT, EMPTY_WATCHER);
            return zooClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建一个 ZNode 节点，创建失败抛出异常
     * @param path ZNode 对应的 path
     * @param value ZNode 对应的值
     */
    public void create(String path, String value) {
        try {
            // 创建 ZNode，前两个参数容易理解，就是 ZNode 的 path 以及值，其中值是字符串对应的字节数组
            // 第三个参数为该节点的权限信息，即 ACL，本 demo 统一采用默认的权限信息，即 world:anyone:cdrwa
            // 第四个参数即创建节点时的 -s 参数，为持久化节点还是临时节点，本 demo 统一采用持久化节点
            zooKeeper.create(path, value.getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean exist(String path) {
        try {
            return zooKeeper.exists(path, false) != null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setData(String path, String value) {
        try {
            zooKeeper.setData(path, value.getBytes(), -1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String path) {
        try {
            zooKeeper.delete(path, -1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void put(String path, String value) {
        try {
            if (!exist(path)) {
                create(path, value);
                return;
            }
            setData(path, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void replace(String path, String value) {
        try {
            delete(path);
            setData(path, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据 path 获取 Stat 信息
     * @param path ZNode 对应路径
     * @return 存在对应 ZNode 则返回 Stat，不存在则返回 null
     */
    public Stat getStat(String path) {
        try {
            return zooKeeper.exists(path, false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        ZooClient zooClient = createInstance("192.168.25.41:2181");
        Random random = new Random();
        final String val = String.valueOf(random.nextInt() % 100 + 1);
        zooClient.create("/test", val);
    }
}

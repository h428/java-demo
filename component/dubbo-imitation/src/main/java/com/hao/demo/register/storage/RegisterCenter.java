package com.hao.demo.register.storage;

import com.hao.demo.framework.ProviderURL;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 模拟注册中心
 */
public class RegisterCenter {

    /**
     * 模拟注册中心的存储各个服务提供者的 ip 的数据结构： 服务接口名称 --> 所有服务提供者的 ip 地址列表
     */
    private static Map<String, List<ProviderURL>> PROVIDER_MAP = new HashMap<>();

    private static final Random random = new Random();

    public static void register(String interfaceName, ProviderURL providerUrl) {
        List<ProviderURL> providerUrls = PROVIDER_MAP.computeIfAbsent(interfaceName, k -> new ArrayList<>());
        providerUrls.add(providerUrl);
        // 将 PROVIDER_MAP 保存到本地文件
        saveToFile();
    }

    /**
     * 模拟从注册中心查询服务提供者以及负载均衡
     * @param interfaceName 接口名称
     * @return 返回可用服务提供者的 ServiceURL 机器地址
     */
    public static ProviderURL getProviderUrl(String interfaceName) {
        // 先从本地文件读取 PROVIDER_MAP 数据
        readFromFile();

        List<ProviderURL> urls = PROVIDER_MAP.get(interfaceName);

        if (urls == null || urls.isEmpty()) {
            return null;
        }

        // 采取随机策略进行负载均衡
        return urls.get(random.nextInt(urls.size()));
    }

    private static final String tempFileName = "./temp.txt";

    /**
     * 由于没有采用分布式中间件，而服务提供者与消费者运行在不同的进程，PROVIDER_MAP 字段并不共享，
     * 因此服务消费者的 PROVIDER_MAP 实际上会为空，为了解决该问题，由于测试 demo 的服务提供者和消费者在同一机器；
     * 可以利用文件读写的方式达到数据共享的目的
     */
    private static void saveToFile() {
        try {
            FileOutputStream fos = new FileOutputStream(tempFileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(PROVIDER_MAP);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private static void readFromFile() {
        try {
            FileInputStream fis = new FileInputStream(tempFileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            PROVIDER_MAP = (Map<String, List<ProviderURL>>) ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

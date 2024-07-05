package com.redisson;

import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class RedissonDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(RedissonDemo.class.getClassLoader().getResource("redisson.yaml"));

        FileReader fileReader = new FileReader("/Users/zhaodong/work/work_project/ToolDemoNew/src/main/java/com/redisson/redisson.yaml");
        Config config = Config.fromYAML(fileReader);
        RedissonClient redisson = Redisson.create(config);

//        // 设置Hash的key和value
//        RMap<String, String> map = redisson.getMap("hash");
//        map.put("field", "value");
//
//        // 对于相应的key设置过期时间
//        RBucket<String> bucket = redisson.getBucket("hash:field:expire");
//        bucket.set("dummyValue", 5, TimeUnit.MINUTES);
//
//        System.out.println("value:"+bucket.get());

        // redisson  延迟队列

        RedissonMsgDto msgDto = new RedissonMsgDto(4, "test004");
        String queueName ="Ten-Year-Pmf-GP-Alert";
        RBlockingQueue<RedissonMsgDto> blockingQueue = redisson.getBlockingQueue(queueName);
        RDelayedQueue<RedissonMsgDto> delayedQueue = redisson.getDelayedQueue(blockingQueue);
        delayedQueue.offer(msgDto, 5, TimeUnit.SECONDS);
        while(true){
            RedissonMsgDto msgDto1 =   blockingQueue.take();
            if(msgDto1 != null){
                System.out.println("msgDto1:"+msgDto1.getCode()+"-"+msgDto1.getValue());
            }

        }

    }


}

package com.stream;

import com.stream.dto.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamFlatMapDemo {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        for(int i=0;i<103;i++){
            User user = User.builder().name("name"+i).age(i).score(i).build();
            userList.add(user);
        }

       List<Integer> scoreList =  IntStream.range(0,userList.size()/20+1).mapToObj(index->
               {
                   System.out.println("-------------index:"+index);

                   return  userList.subList(index*20,(index+1)*20>userList.size()?userList.size():(index+1)*20);
               }).flatMap(list-> {
                            return list.stream().map(
                                    user -> {
                                        System.out.println("User:"+user.getName());
                                       return user.getScore() + 100;
                                    }
                            );

                        }).collect(Collectors.toList());

        System.out.println(scoreList.size());

    }
}

package com.mapStruct;

import com.mapStruct.dto.Item;
import com.mapStruct.dto.SourceDto;
import com.mapStruct.dto.TargetDto;

import java.util.ArrayList;
import java.util.List;

public class MapStructDemo {

    public static void main(String[] args) {
        Item item = Item.builder().itemName("1-item-name").build();
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        SourceDto sourceDto = SourceDto.builder().sourceId("1")
                .sourceName("1-name").itemList(itemList)
        .build();
        TargetDto targetDto = TestMapper.INSTANCE.convertTest(sourceDto);
        System.out.println(targetDto.getItemList2());

    }

}

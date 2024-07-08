package com.mapStruct;

import com.mapStruct.dto.Item;
import com.mapStruct.dto.Item2;
import com.mapStruct.dto.SourceDto;
import com.mapStruct.dto.TargetDto;
import com.mapStruct.enums.Item2Enum;
import com.mapStruct.enums.ItemEnum;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TestMapper {

    TestMapper INSTANCE = Mappers.getMapper(TestMapper.class);

    @Mappings({
            @Mapping( target = "itemList2",source = "itemList")
    })
    TargetDto convertTest(SourceDto source);

    @Mappings({
            @Mapping( target = "item2Id", source = "itemId"),
            @Mapping(target = "item2Name" ,source = "itemName")
    })
    Item2 convertItem2(Item source);

    List<Item2> convertItem2List(List<Item> sourceList);

    @ValueMappings({
            @ValueMapping(target = "ONE",source = "ONE_ONE"),
            @ValueMapping(target = "TWO",source = "TWO_TWO"),
            @ValueMapping(target = "TWO",source = "THREE_THREE"),
    })
    Item2Enum convertToItem2Enum(ItemEnum source);


}

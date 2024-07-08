package com.mapStruct.dto;

import com.mapStruct.enums.Item2Enum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item2 {

    private String item2Id;

    private String item2Name;

    private Item2Enum itemEnum;
}

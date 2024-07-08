package com.mapStruct.dto;

import com.mapStruct.enums.ItemEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private String itemId;

    private String itemName;

    private ItemEnum itemEnum;
}

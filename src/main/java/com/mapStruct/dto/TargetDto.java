package com.mapStruct.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TargetDto {

    private String targetId;

    private String targetName;

    private List<Item2> itemList2;
}

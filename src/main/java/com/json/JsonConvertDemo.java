package com.json;

import com.tuniu.flightticket.common.util.JsonUtil;
import com.json.dto.BaggageInfoSupplementEntity;

public class JsonConvertDemo {
    public static void main(String[] args) {
        String restData= "{\"uid\":\"16130\",\"token\":\"undefined\",\"nickname\":\"赵东\",\"r\":0.8887362344176968,\"aircomIataCode\":\"9C\",\"isVendorId\":\"1\",\"vendorId\":\"1/14/22/21/100/13/10/8/20/23/24/25/26/27/28/29/30/31/32/33/34/35/36/37/38/39\",\"startDate\":\"2018-10-12\",\"endDate\":\"2019-06-30\",\"issueStartDate\":\"2018-10-12\",\"issueEndDate\":\"2019-06-30\",\"isRoutes\":\"2\",\"routesDirection\":\"0\",\"isFlightNo\":0,\"routes\":\"\",\"flightNo\":\"\",\"opUid\":\"16130\",\"opName\":\"赵东\",\"baggageInfoSupplementConfig\":[{\"seatCodes\":\"H\",\"checkNumber\":\"1\",\"checkTheMaxWeight\":\"g\",\"checkTheMaxSize\":\"1\",\"portableNumber\":\"1\",\"portableTheMaxWeight\":\"9\",\"portableTheMaxSize\":\"\",\"remark\":\"\"}],\"baggageInfoSupplementId\":\"BISSC_1539323225073349\"}";
        BaggageInfoSupplementEntity baggageInfoEntity = JsonUtil.toBean(restData, BaggageInfoSupplementEntity.class);
        System.out.println(baggageInfoEntity);
    }
}

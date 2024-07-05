package com.json;

import com.tuniu.mauritius.confirmation.common.model.QueryOrderTotalCostReq;
import com.tuniu.pebble.common.util.JSONUtil;

public class JsonDemo {
    public static void main(String[] args) {
        QueryOrderTotalCostReq req = new QueryOrderTotalCostReq();
        System.out.println(JSONUtil.thriftToJSON(req));
    }
}

package org.json;

import com.alibaba.fastjson.JSONObject;
import com.tuniu.mauritius.confirmation.common.model.QueryOrderTotalCostReq;
import com.tuniu.pebble.common.util.JSONUtil;

public class JsonPebbleDemo {
    public static void main(String[] args) {
        QueryOrderTotalCostReq req = new QueryOrderTotalCostReq();
        System.out.println("pebble工具转换JSON:"+JSONUtil.thriftToJSON(req));
        System.out.println("--------------------------------");
        System.out.println("普通工具转换JSON:"+JSONObject.toJSONString(req));
    }
}

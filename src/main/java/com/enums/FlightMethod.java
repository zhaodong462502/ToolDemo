package com.enums;

import org.apache.commons.lang3.StringUtils;

public enum FlightMethod {
    air_query("FlightSearchService"),//列表查询
    air_patFlight("CheckStatusService"),//验舱验价
    air_getFlightRule("flightRcsService"),//退改签规则查询
    air_createOrder("flightOrderService"),//创建订单
    air_payOrder("ConfirmOrderService"),//支付订单
    air_order_queryAirById("FlightOrderDetailService"),//订单详情
    air_cancelOrder("CancelOrderService"),//取消订单
    air_refundApply("ApplyRefundService"),//退票申请
    air_changeQuery(""),//改期航班查询
    air_changeApply(""),//改期申请
    air_order_queryRefundById("QueryRefundOrderService"),//退票单详情
    air_refundFeeQuery("QueryRefundFeeService"),//退票费用查询
    air_changeFeeQuery(""),//改期费用查询
    air_cancelChange(""),//取消改期申请
    ;

    FlightMethod(String method) {
        this.method = method;
    }

    private String method;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}

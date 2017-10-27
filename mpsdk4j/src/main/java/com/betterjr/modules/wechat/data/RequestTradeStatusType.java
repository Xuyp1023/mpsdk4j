package com.betterjr.modules.wechat.data;

import com.betterjr.common.utils.BetterStringUtils;

public enum RequestTradeStatusType {
    //申请状态；0未处理，1待完善资料，2审批中，3待签约，4已签约，5待录入流水号，6还款中，7已逾期，8已还完，9已失效，X融资失败
    PENDING("0", "未处理"), BLENDING("1", "待完善资料"), REPORTING("2", "审批中"), REJECT_MODIFIY("3", "待签约"), WASTE_SINGLE("4", "已签约"), BE_REMOVED("5", "待录入"), 
    REPORTED("6", "还款中"), CONFIRMED("7", "已逾期"), FINISHED("8", "已还完"),TRADE_FIAL("9","已失效"), APP_FIAL("X","融资失败"),LOAN("10", "放款中");

    private final String title;
    private final String value;

    RequestTradeStatusType(String anValue, String anTitle) {
        this.title = anTitle;

        this.value = anValue;
    }

    public String getTitle() {
        return this.title;
    }

    public String getValue() {
        return this.value;
    }

    public static RequestTradeStatusType checking(String anWorkType) {
        try {
            if (BetterStringUtils.isNotBlank(anWorkType)) {
                for (RequestTradeStatusType statusType : RequestTradeStatusType.values()) {
                    if (statusType.value.equals(anWorkType)) {

                        return statusType;
                    }
                }
                return RequestTradeStatusType.valueOf(anWorkType.trim().toUpperCase());
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

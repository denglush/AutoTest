package com.kn.model;

import lombok.Data;

@Data
public class TradeModel {

    private int  transfer_id;
    private String tradeCode;
    private String tradeType;
    private String payAccNo;
    private String recAccNo;
    private long txnAmt;


    public TradeModel(String tradeCode, String tradeType, String payAccNo, String recAccNo, long txnAmt) {
        this.tradeCode = tradeCode;
        this.tradeType = tradeType;
        this.payAccNo = payAccNo;
        this.recAccNo = recAccNo;
        this.txnAmt = txnAmt;
    }


    public TradeModel(int transfer_id, String tradeCode, String tradeType, String payAccNo, String recAccNo, long txnAmt) {
        this.transfer_id = transfer_id;
        this.tradeCode = tradeCode;
        this.tradeType = tradeType;
        this.payAccNo = payAccNo;
        this.recAccNo = recAccNo;
        this.txnAmt = txnAmt;
    }

    @Override
    public String toString() {
        return "tradeCode:"+tradeCode+","+
        "tradeType:"+tradeType+","+
        "payAccNo:"+payAccNo+","+
        "recAccNo:"+recAccNo+","+
        "txnAmt:"+txnAmt;
    }

    public int getTransfer_id() {
        return transfer_id;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public String getTradeType() {
        return tradeType;
    }

    public String getPayAccNo() {
        return payAccNo;
    }

    public String getRecAccNo() {
        return recAccNo;
    }

    public long getTxnAmt() {
        return txnAmt;
    }


}

package com.ruoyi.system.dto.paymentRequest;


import com.ruoyi.system.domain.PaymentAccount;
import com.ruoyi.system.domain.paymentRequest.PaymentRequest;
import com.ruoyi.system.domain.paymentRequest.PaymentRequestDt1;

import java.io.Serializable;
import java.util.List;

/**
 * 请款单Dto
 * @author SKaiL
 * @date 2022/12/4 15:54
 * @version 1.0
 */
public class PaymentRequestDto implements Serializable {

    /** 单子id(用于APP接口) */
    private Long id;
    
    private PaymentRequest paymentRequest;

    private PaymentAccount paymentAccount;

    private List<PaymentRequestDt1> paymentRequestDt1List;
    
//    private List<ApiFile> files;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentRequest getPaymentRequest() {
        return paymentRequest;
    }

    public void setPaymentRequest(PaymentRequest paymentRequest) {
        this.paymentRequest = paymentRequest;
    }

    public PaymentAccount getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(PaymentAccount paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public List<PaymentRequestDt1> getPaymentRequestDt1List() {
        return paymentRequestDt1List;
    }

    public void setPaymentRequestDt1List(List<PaymentRequestDt1> paymentRequestDt1List) {
        this.paymentRequestDt1List = paymentRequestDt1List;
    }

//    public List<ApiFile> getFiles() {
//        return files;
//    }
//
//    public void setFiles(List<ApiFile> files) {
//        this.files = files;
//    }
}

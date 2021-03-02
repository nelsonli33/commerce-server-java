package com.shopflix.ecpayb2cinvoiceservices.services;

import com.shopflix.ecpayb2cinvoiceservices.data.*;

public interface EcpayInvoiceService
{
    /**
     * 開立發票
     * @param requestData
     * @return {@link InvoiceResultData}
     */
    InvoiceResultData postIssue(InvoiceRequestData requestData);

    /**
     * 作廢發票
     * @param requestData
     * @return {@link InvoiceInvalidResultData}
     */
    InvoiceInvalidResultData postIssueInvalid(InvoiceInvalidRequestData requestData);


    /**
     * 查詢發票明細
     * @param orderCode
     * @return {@link QueryInvoiceResultData}
     */
    QueryInvoiceResultData postQueryIssue(String orderCode);

    /**
     * 查詢發票作廢明細
     * @param orderCode
     * @return {@link QueryIssueInvalidResultData}
     */
    QueryIssueInvalidResultData postQueryIssueInvalid(String orderCode);

    /**
     * 發送發票開立通知
     * @param requestData
     * @return
     */
    InvoiceNotifyResultData postInvoiceNotify(InvoiceNotifyRequestData requestData);

    /**
     * 捐贈碼驗證
     * @param loveCode - the love code
     * @return <code>true</code> if exists
     */
    boolean postCheckLoveCode(String loveCode);
}

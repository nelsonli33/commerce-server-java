package com.shopflix.ecpayb2cinvoiceservices.services.impl;

import com.shopflix.ecpayb2cinvoiceservices.data.*;
import com.shopflix.ecpayb2cinvoiceservices.ecpay.EnhancedEcpayFunction;
import com.shopflix.ecpayb2cinvoiceservices.ecpay.InvoiceAllInOne;
import com.shopflix.ecpayb2cinvoiceservices.services.EcpayInvoiceService;
import ecpay.invoice.integration.domain.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;


public class DefaultEcpayInvoiceService implements EcpayInvoiceService
{
    private InvoiceAllInOne invoiceAllInOne;

    public InvoiceResultData postIssue(InvoiceRequestData requestData)
    {
        IssueObj obj = new IssueObj();
        obj.setRelateNumber(requestData.getOrderCode());
        obj.setCustomerName(requestData.getCustomerName());
        obj.setCustomerAddr(requestData.getCustomerAddress());
        obj.setCustomerIdentifier(requestData.getBusinessNumber());
        obj.setCustomerPhone(requestData.getCustomerPhone());

        obj.setPrint("0");

        if (StringUtils.isNotEmpty(requestData.getBusinessNumber()))
        {
            obj.setPrint("1");
        }

        if (requestData.getDonation())
        {
            obj.setDonation("1");
            obj.setLoveCode(requestData.getLoveCode());
        }
        else if (!requestData.getDonation() || StringUtils.isNotEmpty(requestData.getBusinessNumber()))
        {
            obj.setDonation("0");
        }


        if (StringUtils.isNotEmpty(requestData.getBusinessNumber()) || requestData.getDonation() ||
                "1".equals(obj.getPrint()))
        {
            obj.setCarruerType("");
            obj.setCarruerNum("");
        }
        else
        {
            obj.setCarruerType("1");
            obj.setCarruerNum("");
        }

        obj.setTaxType("1");
        obj.setSalesAmount(String.valueOf(requestData.getSalesAmount()));
        obj.setItemName(requestData.getItemName());
        obj.setItemCount(requestData.getItemCount());
        obj.setItemWord(requestData.getItemUnit());
        obj.setItemPrice(requestData.getItemPrice());
        obj.setItemAmount(requestData.getItemAmount());
        obj.setInvType("07");
        obj.setVat("1");

        final String resultStr = invoiceAllInOne.issue(obj);
        final Map<String, String> resultMap = EnhancedEcpayFunction.parseResultStr(resultStr);
        return invoiceAllInOne.convert(resultMap, InvoiceResultData.class);
    }


//    public AllowanceByCollegiateResultData postAllowanceByCollegiate(AllowanceByCollegiateRequestData requestData){
//        AllowanceByCollegiateObj obj = new AllowanceByCollegiateObj();
//        obj.setInvoiceNo(requestData.getInvoiceNo());
//        obj.setAllowanceNotify("E");
//        obj.setCustomerName(requestData.getCustomerName());
//        obj.setNotifyMail(requestData.getNotifyMail());
//        obj.setNotifyPhone("");
//        obj.setAllowanceAmount(requestData.getAllowanceAmount());
//        obj.setItemName(requestData.getItemName());
//        obj.setItemCount(requestData.getItemCount());
//        obj.setItemWord(requestData.getItemUnit());
//        obj.setItemPrice(requestData.getItemPrice());
//        obj.setItemAmount(requestData.getItemAmount());
//        obj.setReturnURL(requestData.getReturnURL());
//
//        final String resultStr = invoiceAllInOne.allowancebycollegiate(obj);
//        final Map<String, String> resultMap = EnhancedEcpayFunction.parseResultStr(resultStr);
//        return invoiceAllInOne.convert(resultMap, AllowanceByCollegiateResultData.class);
//    }


    public InvoiceInvalidResultData postIssueInvalid(InvoiceInvalidRequestData requestData)
    {
        IssueInvalidObj obj = new IssueInvalidObj();
        obj.setInvoiceNumber(requestData.getInvoiceNumber());
        obj.setReason(requestData.getReason());

        final String resultStr = invoiceAllInOne.issueInvalid(obj);
        final Map<String, String> resultMap = EnhancedEcpayFunction.parseResultStr(resultStr);
        return invoiceAllInOne.convert(resultMap, InvoiceInvalidResultData.class);
    }

    //
//    public static String postAllowanceInvalid(){
//        AllowanceInvalidObj obj = new AllowanceInvalidObj();
//        obj.setInvoiceNo("XN12345678");
//        obj.setAllowanceNo("1234123412341234");
//        obj.setReason("´ú¸Õ");
//        return all.allowanceInvalid(obj);
//    }
//
    public QueryInvoiceResultData postQueryIssue(String orderCode)
    {
        QueryIssueObj obj = new QueryIssueObj();
        obj.setRelateNumber(orderCode);

        final String resultStr = invoiceAllInOne.queryIssue(obj);
        final Map<String, String> resultMap = EnhancedEcpayFunction.parseResultStr(resultStr);
        return invoiceAllInOne.convert(resultMap, QueryInvoiceResultData.class);
    }

    //
//    public static String postQueryAllowance(){
//        QueryAllowanceObj obj = new QueryAllowanceObj();
//        obj.setInvoiceNo("TT00012440");
//        obj.setAllowanceNo("2017063010319868");
//        return all.queryAllowance(obj);
//    }
//
    public QueryIssueInvalidResultData postQueryIssueInvalid(String orderCode)
    {
        QueryIssueInvalidObj obj = new QueryIssueInvalidObj();
        obj.setRelateNumber(orderCode);
        final String resultStr = invoiceAllInOne.queryIssueInvalid(obj);
        final Map<String, String> resultMap = EnhancedEcpayFunction.parseResultStr(resultStr);
        return invoiceAllInOne.convert(resultMap, QueryIssueInvalidResultData.class);
    }

    //
//    public static String postQueryAllowanceInvalid(){
//        QueryAllowanceInvalidObj obj = new QueryAllowanceInvalidObj();
//        obj.setInvoiceNo("TU00005705");
//        obj.setAllowanceNo("2017052311296404");
//        return all.queryAllowanceInvalid(obj);
//    }
//
    public InvoiceNotifyResultData postInvoiceNotify(InvoiceNotifyRequestData requestData)
    {
        InvoiceNotifyObj obj = new InvoiceNotifyObj();
        obj.setInvoiceNo(requestData.getInvoiceNo());
        obj.setAllowanceNo(requestData.getAllowanceNo());
        obj.setNotifyMail(requestData.getNotifyMail());
        obj.setNotify("E");
        obj.setInvoiceTag(requestData.getInvoiceTag().getCode());
        obj.setNotified("A");
        final String resultStr = invoiceAllInOne.invoiceNotify(obj);
        final Map<String, String> resultMap = EnhancedEcpayFunction.parseResultStr(resultStr);
        return invoiceAllInOne.convert(resultMap, InvoiceNotifyResultData.class);
    }
//
//    public static String postCheckMobileBarCode(){
//        CheckMobileBarCodeObj obj = new CheckMobileBarCodeObj();
//        obj.setBarCode("/6G.X3LQ");
//        return all.checkMobileBarCode(obj);
//    }

    public boolean postCheckLoveCode(String loveCode)
    {
        CheckLoveCodeObj obj = new CheckLoveCodeObj();
        obj.setLoveCode(loveCode);

        final String resultStr = invoiceAllInOne.checkLoveCode(obj);
        final Map<String, String> resultMap = EnhancedEcpayFunction.parseResultStr(resultStr);

        final CheckLoveCodeResultData resultData = invoiceAllInOne.convert(resultMap, CheckLoveCodeResultData.class);

        if ("Y".equals(resultData.getIsExist()))
        {
            return true;
        }
        return false;
    }


    public InvoiceAllInOne getInvoiceAllInOne()
    {
        return invoiceAllInOne;
    }

    public void setInvoiceAllInOne(InvoiceAllInOne invoiceAllInOne)
    {
        this.invoiceAllInOne = invoiceAllInOne;
    }
}

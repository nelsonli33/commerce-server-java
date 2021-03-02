package com.shopflix.ecpayb2cinvoiceservices.ecpay;

import com.shopflix.ecpayb2cinvoiceservices.data.BaseResultData;
import ecpay.invoice.integration.ecpayOperator.EcpayFunction;
import ecpay.invoice.integration.errorMsg.ErrorMessage;
import ecpay.invoice.integration.exception.EcpayException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnhancedEcpayFunction extends EcpayFunction
{
    private Logger LOG = LoggerFactory.getLogger(EnhancedEcpayFunction.class);


    public static Map<String, String> parseResultStr(String resultStr)
    {
        if (StringUtils.isEmpty(resultStr))
        {
            return Collections.emptyMap();
        }

        Map<String, String> resultMap = new HashMap<>();

        final String[] pairs = resultStr.split("&");

        for (String pair : pairs)
        {
            final int idx = pair.indexOf("=");
            resultMap.put(pair.substring(0, idx), pair.substring(idx + 1));
        }

        return resultMap;
    }


}

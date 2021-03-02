package com.shopflix.ecpayb2cinvoiceservices.ecpay;

import com.shopflix.ecpayb2cinvoiceservices.data.BaseResultData;
import ecpay.invoice.integration.AllInOne;
import ecpay.invoice.integration.ecpayOperator.EcpayFunction;
import ecpay.invoice.integration.errorMsg.ErrorMessage;
import ecpay.invoice.integration.exception.EcpayException;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.Map;

public class InvoiceAllInOne extends AllInOne
{
    private final static Logger log = Logger.getLogger(InvoiceAllInOne.class.getName());

    public InvoiceAllInOne()
    {
        super();
    }


    public <T extends BaseResultData> T convert(final Map<String, String> resultMap, final Class<T> clazz)
    {
        T obj = null;
        try
        {
            obj = this.setResponseValue(resultMap, clazz);
        }
        catch (final Exception e)
        {
            throw new EcpayException(ErrorMessage.OBJ_MISSING_FIELD);
        }
        log.info(clazz.getName() + "params:" + obj.toString());
        final String checkMacValue = EcpayFunction.genCheckMacValue(HashKey, HashIV, obj);
        log.info(clazz.getName() + " self generate CheckMacValue: " + checkMacValue + ", received CheckMacValue: "
                + obj.getCheckMacValue());
        if (!checkMacValue.equals(obj.getCheckMacValue()))
        {
            log.error(ErrorMessage.CHECK_MAC_VALUE_NOT_EQUALL_ERROR);
            throw new EcpayException(ErrorMessage.CHECK_MAC_VALUE_NOT_EQUALL_ERROR);
        }
        return obj;
    }

    private <T extends BaseResultData> T setResponseValue(final Map<String, String> resultMap, final Class<T> clazz) throws Exception
    {
        final T obj = clazz.newInstance();

        for (final String name : resultMap.keySet())
        {
            Field declaredField = null;
            try
            {
                declaredField = clazz.getDeclaredField(name);
            }
            catch (final Exception ex1)
            {

                try
                {
                    declaredField = clazz.getSuperclass().getDeclaredField(name);
                }
                catch (final Exception ex2)
                {
                    log.warn("Not found the field:[" + name + "]");
                    continue;
                }
            }
            declaredField.setAccessible(true);
            declaredField.set(obj, resultMap.get(name));
        }
        return obj;

    }
}

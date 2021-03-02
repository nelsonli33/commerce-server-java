package ecpay.invoice.integration;

import ecpay.invoice.integration.ecpayOperator.EcpayFunction;
import ecpay.invoice.integration.errorMsg.ErrorMessage;
import ecpay.invoice.integration.exception.EcpayException;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;

public class AllInOneBase
{
    protected static String operatingMode;
    protected static String mercProfile;
    protected static String HashKey;
    protected static String HashIV;
    protected static String MerchantID;
    protected static String issueUrl;
    protected static String delayIssueUrl;
    protected static String triggerIssueUrl;
    protected static String allowanceUrl;
    protected static String allowancebycollegiateUrl;
    protected static String issueInvalidUrl;
    protected static String allowanceInvalidUrl;
    protected static String queryIssueUrl;
    protected static String queryAllowanceUrl;
    protected static String queryIssueInvalidUrl;
    protected static String queryAllowanceInvalidUrl;
    protected static String invoiceNotifyUrl;
    protected static String checkMobileBarCodeUrl;
    protected static String checkLoveCodeUrl;

    public AllInOneBase()
    {


        try
        {
            Document doc;


            /* when using web project, please use the following code with try/catch wrapped*/
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String configPath = URLDecoder.decode(classLoader.getResource("invoice_conf.xml").getPath(), "UTF-8");
            doc = EcpayFunction.xmlParser(configPath);

            /* when using testing code*/
//		String configPath = "./src/main/resources/invoice_conf.xml";
//		doc = EcpayFunction.xmlParser(configPath);

            doc.getDocumentElement().normalize();
            //OperatingMode
            Element ele = (Element) doc.getElementsByTagName("OperatingMode").item(0);
            operatingMode = ele.getTextContent();
            //MercProfile
            ele = (Element) doc.getElementsByTagName("MercProfile").item(0);
            mercProfile = ele.getTextContent();
            //MID, HashKey, HashIV
            NodeList nodeList = doc.getElementsByTagName("MInfo");
            for (int i = 0; i < nodeList.getLength(); i++)
            {
                ele = (Element) nodeList.item(i);
                if (ele.getAttribute("name").equalsIgnoreCase(mercProfile))
                {
                    MerchantID = ele.getElementsByTagName("MerchantID").item(0).getTextContent();
                    HashKey = ele.getElementsByTagName("HashKey").item(0).getTextContent();
                    HashIV = ele.getElementsByTagName("HashIV").item(0).getTextContent();
                }
            }
            if (HashKey == null)
            {
                throw new EcpayException(ErrorMessage.MInfo_NOT_SETTING);
            }
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
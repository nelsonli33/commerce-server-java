package com.shopflix.storefront.forms.validation;

import com.shopflix.core.enums.InvoiceType;
import com.shopflix.ecpayb2cinvoiceservices.services.EcpayInvoiceService;
import com.shopflix.storefront.forms.InvoiceSettingForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component(value = "invoiceSettingValidator")
public class InvoiceSettingValidator implements Validator
{
    public static final Pattern TWBID_PATTERN = Pattern.compile("^[0-9]{8}$");

    private EcpayInvoiceService ecpayInvoiceService;

    @Override
    public boolean supports(Class<?> clazz)
    {
        return InvoiceSettingForm.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors)
    {
        InvoiceSettingForm form = (InvoiceSettingForm) object;

        final InvoiceType invoiceType = InvoiceType.from(form.getInvoiceType());

        switch (invoiceType)
        {
            case PERSON:
                validatePersonInvoice(form, errors);
                break;
            case COMPANY:
                validateCompanyInvoice(form, errors);
                break;
            case DONATION:
                validateDonationInvoice(form, errors);
                break;
        }
    }

    protected void validatePersonInvoice(final InvoiceSettingForm form, Errors errors)
    {
        final String contactEmail = form.getContactEmail();
        validateContactEmailField(contactEmail, errors);
    }

    protected void validateCompanyInvoice(final InvoiceSettingForm form, Errors errors)
    {
        final String contactEmail = form.getContactEmail();
        final String invoiceTitle = form.getInvoiceTitle();
        final String businessNumber = form.getBusinessNumber();

        validateContactEmailField(contactEmail, errors);
        validateInvoiceTitleField(invoiceTitle, errors);
        validateBusinessNumberField(businessNumber, errors);
    }

    protected void validateDonationInvoice(final InvoiceSettingForm form, Errors errors)
    {
        final String loveCode = form.getLoveCode();
        validateLoveCodeField(loveCode, errors);
    }

    protected void validateContactEmailField(String contactEmail, Errors errors)
    {
        if (StringUtils.isEmpty(contactEmail))
        {
            errors.rejectValue("contactEmail", "invoice.contactEmail.invalid");
        }
        else if (StringUtils.length(contactEmail) > 255)
        {
            errors.rejectValue("contactEmail", "invoice.contactEmail.invalid");
        }
    }

    protected void validateInvoiceTitleField(String invoiceTitle, Errors errors)
    {
        if (StringUtils.isEmpty(invoiceTitle))
        {
            errors.rejectValue("invoiceTitle", "invoice.invoiceTitle.invalid");
        }
        else if (StringUtils.length(invoiceTitle) > 255)
        {
            errors.rejectValue("invoiceTitle", "invoice.invoiceTitle.invalid");
        }
    }

    protected boolean validateBusinessNumberField(String businessNumber, Errors errors)
    {
        boolean result = false;

        if (StringUtils.isEmpty(businessNumber))
        {
            errors.rejectValue("businessNumber", "invoice.businessNumber.invalid");
            return result;
        }

        if (!TWBID_PATTERN.matcher(businessNumber).matches())
        {
            errors.rejectValue("businessNumber", "invoice.businessNumber.invalid");
            return result;
        }
        else
        {
            final String weight = "12121241";
            boolean type2 = false; //第七個數是否為七

            int tmp = 0, sum = 0;
            for (int i = 0; i < 8; i++)
            {
                tmp = (businessNumber.charAt(i) - '0') * (weight.charAt(i) - '0');
                sum += tmp / 10 + (tmp % 10); //取出十位數和個位數相加
                if (i == 6 && businessNumber.charAt(i) == '7')
                {
                    type2 = true;
                }
            }
            if (type2)
            {
                if ((sum % 10) == 0 || ((sum + 1) % 10) == 0)
                { //如果第七位數為7
                    result = true;
                }
            }
            else
            {
                if ((sum % 10) == 0)
                {
                    result = true;
                }
            }

            if (!result) {
                errors.rejectValue("businessNumber", "invoice.businessNumber.invalid");
            }
            return result;
        }
    }


    protected void validateLoveCodeField(String loveCode, Errors errors)
    {
        if (StringUtils.isEmpty(loveCode))
        {
            errors.rejectValue("loveCode", "invoice.loveCode.invalid");
        }
        else if (StringUtils.length(loveCode) > 255)
        {
            errors.rejectValue("loveCode", "invoice.loveCode.invalid");
        }

        final boolean isExist = getEcpayInvoiceService().postCheckLoveCode(loveCode);
        if (!isExist)
        {
            errors.rejectValue("loveCode", "invoice.loveCode.invalid");
        }
    }

    public EcpayInvoiceService getEcpayInvoiceService()
    {
        return ecpayInvoiceService;
    }

    @Autowired
    public void setEcpayInvoiceService(EcpayInvoiceService ecpayInvoiceService)
    {
        this.ecpayInvoiceService = ecpayInvoiceService;
    }
}

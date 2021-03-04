package com.shopflix.storefront.facades.user.converters.populator;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.enums.InvoiceType;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.user.InvoiceSettingModel;
import com.shopflix.storefront.facades.user.data.InvoiceSettingData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class InvoiceSettingPopulator implements Populator<InvoiceSettingModel, InvoiceSettingData>
{
    @Override
    public void populate(InvoiceSettingModel source, InvoiceSettingData target) throws ConversionException
    {
        Assert.notNull(source, "Parameter source cannot be null.");
        Assert.notNull(target, "Parameter target cannot be null.");

        target.setId(source.getId());

        final InvoiceType invoiceType = source.getInvoiceType();
        if (invoiceType != null) {
            target.setInvoiceType(invoiceType.getCode());
        }

        target.setInvoiceTitle(StringUtils.defaultString(source.getInvoiceTitle()));
        target.setBusinessNumber(StringUtils.defaultString(source.getBusinessNumber()));

        if (StringUtils.isNotEmpty(source.getContactEmail())) {
            target.setContactEmail(source.getContactEmail());
        }
        target.setLoveCode(StringUtils.defaultString(source.getLoveCode()));
    }
}

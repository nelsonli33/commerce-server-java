package com.shopflix.storefront.facades.user.converters.populator;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.enums.InvoiceType;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.user.InvoiceSettingModel;
import com.shopflix.storefront.facades.user.data.InvoiceSettingData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class InvoiceSettingReversePopulator implements Populator<InvoiceSettingData, InvoiceSettingModel>
{
    @Override
    public void populate(InvoiceSettingData source, InvoiceSettingModel target) throws ConversionException
    {
        Assert.notNull(source, "Parameter source cannot be null.");
        Assert.notNull(target, "Parameter target cannot be null.");

        target.setInvoiceType(InvoiceType.from(source.getInvoiceType()));
        target.setInvoiceTitle(source.getInvoiceTitle());
        target.setBusinessNumber(source.getBusinessNumber());
        target.setContactEmail(source.getContactEmail());
        target.setLoveCode(source.getLoveCode());
    }
}

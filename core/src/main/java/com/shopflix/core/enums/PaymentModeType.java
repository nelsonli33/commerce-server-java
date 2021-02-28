package com.shopflix.core.enums;

import com.shopflix.core.converters.AbstractStringEnumConverter;

public enum PaymentModeType implements PersistableStringEnum
{
    CREDITCARDONCE("CreditCardOnce"),

    CREDITCARDINSTALLMENT("CreditCardInstallment"),

    COD("CashOnDelivery");

    private String code;

    PaymentModeType(String code)
    {
        this.code = code;
    }

    @Override
    public String getCode()
    {
        return code;
    }

    public static PaymentModeType from(String code) {
        for(final PaymentModeType e : PaymentModeType.values()) {
            if (e.getCode().equalsIgnoreCase(code)) {
                return e;
            }
        }
        return null;
    }

    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractStringEnumConverter<PaymentModeType, String>
    {
        public Converter() {
            super(PaymentModeType.class);
        }
    }
}

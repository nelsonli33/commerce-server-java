package com.shopflix.core.enums;

import com.shopflix.core.converters.AbstractStringEnumConverter;

public enum PaymentStatus implements PersistableStringEnum
{
    UNPAID("UNPAID"),

    PAID_OVER_TIME("PAID_OVER_TIME"), // 超過付款時間

    PAID("PAID"),

    REFUNDED("REFUNDED"),

    VOIDED("VOIDED"); // 已作廢

    private String code;

    PaymentStatus(String code)
    {
        this.code = code;
    }

    @Override
    public String getCode()
    {
        return code;
    }

    public static PaymentStatus from(String code) {
        for(final PaymentStatus e : PaymentStatus.values()) {
            if (e.getCode().equalsIgnoreCase(code)) {
                return e;
            }
        }
        return null;
    }

    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractStringEnumConverter<PaymentStatus, String>
    {
        public Converter() {
            super(PaymentStatus.class);
        }
    }
}

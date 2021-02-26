package com.shopflix.storefront.forms.validation;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.shopflix.storefront.forms.AddressForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("addressValidator")
public class AddressValidator implements Validator
{
    private static final int MAX_FIELD_LENGTH = 255;
    private static final int MAX_ZIPCODE_LENGTH = 10;

    @Override
    public boolean supports(Class<?> clazz)
    {
        return AddressForm.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors)
    {
        final AddressForm addressForm = (AddressForm) object;
        validateStandardFields(addressForm, errors);
        validatePhoneField(addressForm.getPhone(), errors);
    }


    protected void validateStandardFields(final AddressForm addressForm, final Errors errors)
    {
        validateStringField(addressForm.getName(), AddressField.NAME, MAX_FIELD_LENGTH, errors);
        validateStringField(addressForm.getCity(), AddressField.CITY, MAX_FIELD_LENGTH, errors);
        validateStringField(addressForm.getDistrict(), AddressField.DISTRICT, MAX_FIELD_LENGTH, errors);
        validateStringField(addressForm.getZipcode(), AddressField.ZIPCODE, MAX_ZIPCODE_LENGTH, errors);
        validateStringField(addressForm.getAddress(), AddressField.ADDRESS, MAX_FIELD_LENGTH, errors);
    }

    protected void validateStringField(final String addressField, final AddressField fieldType,
                                       final int maxFieldLength, final Errors errors)
    {
        if (StringUtils.isEmpty(addressField) || StringUtils.length(addressField) > maxFieldLength)
        {
            errors.rejectValue(fieldType.getFieldKey(), fieldType.getErrorKey());
        }
    }

    protected void validatePhoneField(final String cellphone, final Errors errors)
    {


        if (StringUtils.isEmpty(cellphone))
        {
            errors.rejectValue("phone", "address.phone.not.found");
        }

        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

        try
        {
            final Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(cellphone, LocaleContextHolder.getLocale().getCountry());
            final PhoneNumberUtil.PhoneNumberType numberType = phoneNumberUtil.getNumberType(phoneNumber);
            if (numberType != PhoneNumberUtil.PhoneNumberType.MOBILE || !phoneNumberUtil.isValidNumber(phoneNumber))
            {
                errors.rejectValue("phone", "address.phone.invalid");
            }
        }
        catch (NumberParseException e)
        {
            errors.rejectValue("phone", "address.phone.invalid");
            e.printStackTrace();
        }

    }


    protected enum AddressField
    {
        NAME("name", "address.name.invalid"),
        CITY("city", "address.city.invalid"),
        DISTRICT("district", "address.district.invalid"),
        ZIPCODE("zipcode", "address.zipcode.invalid"),
        ADDRESS("address", "address.address.invalid");

        private String fieldKey;
        private String errorKey;

        AddressField(final String fieldKey, final String errorKey)
        {
            this.fieldKey = fieldKey;
            this.errorKey = errorKey;
        }

        public String getFieldKey()
        {
            return fieldKey;
        }

        public String getErrorKey()
        {
            return errorKey;
        }
    }
}

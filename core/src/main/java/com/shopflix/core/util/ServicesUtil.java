package com.shopflix.core.util;

import com.google.common.base.Preconditions;


public class ServicesUtil {
    public ServicesUtil() {
    }


    public static void validateParameterNotNull(Object parameter, String nullMessage) {
        Preconditions.checkArgument(parameter != null, nullMessage);
    }

    public static void validateParameterNotNullStandardMessage(String parameter, Object parameterValue) {
        validateParameterNotNull(parameterValue, "Parameter " + parameter + " can not be null");
    }
}

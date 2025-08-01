package com.fiap.pj.core.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class SpecificationUtils {
    public static String likeTerm(String term) {
        return '%' + term + '%';
    }
}

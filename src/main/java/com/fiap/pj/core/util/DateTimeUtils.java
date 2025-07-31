package com.fiap.pj.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateTimeUtils {
    public static ZonedDateTime getNow() {
        return ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC).withNano(0);
    }
}
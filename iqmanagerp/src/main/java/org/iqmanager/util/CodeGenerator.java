package org.iqmanager.util;

import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@NoArgsConstructor
public class CodeGenerator {

    /** Сгенерировать 4-х значный код */
    public static String generate() {
        int code = ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
        return "" + code ;
    }

}

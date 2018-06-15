package com.zhuoyue.common.util;

import java.util.UUID;

public class UUIDUtil {


    public static String buildUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(UUIDUtil.buildUUID());
        }
    }

}

package io.dcloud.uts.gson;

import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Field;
import java.util.Locale;

/* loaded from: classes3.dex */
public enum FieldNamingPolicy implements FieldNamingStrategy {
    IDENTITY { // from class: io.dcloud.uts.gson.FieldNamingPolicy.1
        @Override // io.dcloud.uts.gson.FieldNamingStrategy
        public String translateName(Field field) {
            return field.getName();
        }
    },
    UPPER_CAMEL_CASE { // from class: io.dcloud.uts.gson.FieldNamingPolicy.2
        @Override // io.dcloud.uts.gson.FieldNamingStrategy
        public String translateName(Field field) {
            return upperCaseFirstLetter(field.getName());
        }
    },
    UPPER_CAMEL_CASE_WITH_SPACES { // from class: io.dcloud.uts.gson.FieldNamingPolicy.3
        @Override // io.dcloud.uts.gson.FieldNamingStrategy
        public String translateName(Field field) {
            return upperCaseFirstLetter(separateCamelCase(field.getName(), Operators.SPACE_STR));
        }
    },
    LOWER_CASE_WITH_UNDERSCORES { // from class: io.dcloud.uts.gson.FieldNamingPolicy.4
        @Override // io.dcloud.uts.gson.FieldNamingStrategy
        public String translateName(Field field) {
            return separateCamelCase(field.getName(), "_").toLowerCase(Locale.ENGLISH);
        }
    },
    LOWER_CASE_WITH_DASHES { // from class: io.dcloud.uts.gson.FieldNamingPolicy.5
        @Override // io.dcloud.uts.gson.FieldNamingStrategy
        public String translateName(Field field) {
            return separateCamelCase(field.getName(), Operators.SUB).toLowerCase(Locale.ENGLISH);
        }
    },
    LOWER_CASE_WITH_DOTS { // from class: io.dcloud.uts.gson.FieldNamingPolicy.6
        @Override // io.dcloud.uts.gson.FieldNamingStrategy
        public String translateName(Field field) {
            return separateCamelCase(field.getName(), Operators.DOT_STR).toLowerCase(Locale.ENGLISH);
        }
    };

    static String separateCamelCase(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (Character.isUpperCase(cCharAt) && sb.length() != 0) {
                sb.append(str2);
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    static String upperCaseFirstLetter(String str) {
        int length = str.length() - 1;
        int i = 0;
        while (!Character.isLetter(str.charAt(i)) && i < length) {
            i++;
        }
        char cCharAt = str.charAt(i);
        if (Character.isUpperCase(cCharAt)) {
            return str;
        }
        char upperCase = Character.toUpperCase(cCharAt);
        if (i == 0) {
            return upperCase + str.substring(1);
        }
        return str.substring(0, i) + upperCase + str.substring(i + 1);
    }
}

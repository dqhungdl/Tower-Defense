package com.uet.towerdefense.common.util;

import java.util.Collection;
import java.util.Map;

public class CompareUtil {

    public static boolean isEqualsNull(Object obj) {
        return obj == null;
    }

    public static boolean isEqualsNullOrEmpty(String obj) {
        return obj == null || obj.isEmpty();
    }

    public static boolean isEqualsNullOrEmpty(Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isEqualsNullOrEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isEquals(Object obj1, Object obj2) {
        boolean rs = false;
        if (obj1 != null) {
            rs = obj1.equals(obj2);
        } else {
            rs = obj1 == obj2;
        }
        return rs;
    }

    public static boolean isInCollection(Object value, Collection<?> coll) {
        boolean rs = false;
        if (!isEqualsNullOrEmpty(coll) && !isEqualsNull(value)) {
            for (Object elem : coll) {
                if (isEquals(elem, value)) {
                    rs = true;
                    break;
                }
            }
        }
        return rs;
    }

    public static boolean isInStringArray(String value, String[] strArr) {
        boolean rs = false;
        if (!isEqualsNull(strArr) && !isEqualsNull(value) && strArr.length > 0) {
            for (String elem : strArr) {
                if (isEquals(elem, value)) {
                    rs = true;
                    break;
                }
            }
        }
        return rs;
    }
}

package com.colorone.common.utils.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2022/11/29
 */
public class MapUtils {

    /**
     * 根据条件过滤map
     *
     * @param mapList
     * @param key
     * @param value
     * @return
     */
    public static List<Map> toFilter(List<Map> mapList, String key, Object value) {
        List<Map> list = new ArrayList<>();
        for (Map noMap : mapList) {
            if (noMap.containsKey(key) && noMap.get(key).equals(value)) {
                list.add(noMap);
            }
        }
        return list;
    }

    /**
     * 根据条件过滤map
     *
     * @param mapList
     * @param key
     * @param value
     * @return
     */
    public static List<Object> toFilterKey(List<Map> mapList, String key, Object value) {
        List<Object> list = new ArrayList<>();
        for (Map noMap : mapList) {
            if (noMap.get(key).equals(value)) {
                list.add(noMap);
            }
        }
        return list;
    }

}

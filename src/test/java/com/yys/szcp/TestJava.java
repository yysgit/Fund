package com.yys.szcp;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class TestJava {


    public static void main(String[] args) {
        String[][] str = {
                {"a", "b", "c", "d", "e"},
                {"a", "b", "c", "d", "j"},
                {"a", "b", "c", "e", "f"},
                {"b", "c", "d", "e", "f"}
        };
        Map map = getMapData(str, new HashMap(), 0, 0 );
        System.out.println(JSON.toJSONString(map));
    }
    public static Map getMapData(String[][] str, Map map, int x, int y ) {
        int x1 = x;
        int y1 = y;
        if (y < str[x].length) {
            map.put(str[x][y], getMapData(str, new HashMap(), x1, y1 + 1));
            if ( !getString(str, x, y, str[x][y])) {
                map.put(getString1(str, x, y, str[x][y]), getMapData(str, new HashMap(),getStringx(str, x, y, str[x][y]), y1 + 1));
            }
        }
        return map;
    }

    public static boolean getString(String[][] str, int i, int j, String strs) {
        for (int i1 = i + 1; i1 < str.length; i1++) {
            String[] strings = str[i1];
            if (!getB(str, i1, i, j, strs) && !strs.equals(strings[j]) ) {
                return false;
            }
        }
        return true;
    }
    public static boolean getB(String[][] str, int i1, int i, int j, String strs) {
        for (int zz = 0; zz < j; zz++) {
            if (!str[i][zz].equals(str[i1][zz])) {
                return true;
            }
        }
        return false;
    }
    public static String getString1(String[][] str, int i, int j, String strs) {
        for (int i1 = i + 1; i1 < str.length; i1++) {
            String[] strings = str[i1];
            if (!strs.equals(strings[j])) {
                return strings[j];
            }
        }
        return null;
    }
    public static int getStringx(String[][] str, int i, int j, String strs) {
        for (int i1 = i + 1; i1 < str.length; i1++) {
            String[] strings = str[i1];
            if (!strs.equals(strings[j])) {
                return i1;
            }
        }
        return 0;
    }
}

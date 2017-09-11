package com.example.william_zhang.william_zhang.util;

import android.graphics.Color;

import java.util.regex.Pattern;

/**
 * Created by william_zhang on 2017/9/6.
 */

public class ColorUtil {
    /**
     * #555555  ->int 类型
     * @param color
     * @return
     */
    public static int color2Int(String color){
        // #ff00CCFF
        String reg = "#[a-f0-9A-F]{8}";
        if (!Pattern.matches(reg, color)) {
            color = "#00ffffff";
        }

        return Color.parseColor(color);
    }

    /**
     * 16  ->0x00000010
     * @param color
     * @return
     */
    public static String int2ColorString(int color){
        String s=Integer.toHexString(color);
        int length=s.length();
        if(s.length()<8&&s.length()>0){
            for(int i=0;i<8-length;i++){
                if(i==8-length-1||i==8-length-2)
                    s="f"+s;
                else
                s="0"+s;
            }
        }
        return  "0x"+s;
    }
}

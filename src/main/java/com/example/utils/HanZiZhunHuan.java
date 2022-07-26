package com.example.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

/**
 * 汉字转为拼音
 */

public class HanZiZhunHuan{
    /**
     * 汉字转简拼
     * @param str
     * @return String
     */
    @Transactional
    public String getPinYinHeadChar(String str) {
        String convert = "";
        if (str== null || str.length()==0) {
            return convert;
        }
            char word = str.charAt(0);
            // 提取汉字的首字母
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            convert += pinyinArray[0].charAt(0);

        return convert.toUpperCase();
    }

}

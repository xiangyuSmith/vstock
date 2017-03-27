package com.vstock.ext.util;

import org.apache.commons.lang3.*;
import org.apache.commons.lang3.text.WordUtils;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static final String[] EARR_STRING = new String[0];
    public static final String[] ENCA_UTF8 = new String[]{"encoding:UTF-8"};

    public static final String EMPTY = "";
    public static final int INDEX_NOT_FOUND = -1;
    private static final int PAD_LIMIT = 8192;
    private static final String DEFAULT = "GB2312";
    private static final int CHUNK_SIZE = 2000;
    private static Pattern metaPattern = Pattern.compile("<meta\\s+([^>]*http-equiv=\"?content-type\"?[^>]*)>", 2);
    private static Pattern charsetPattern = Pattern.compile("charset=\\s*([a-z][_\\-0-9a-z]*)", 2);

    public static boolean isNum(String str) {
        if ((str == null) || (str.equals(""))) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static boolean isIPAddr(String addr) {
        if (isEmpty(addr)) {
            return false;
        }
        String[] ips = split(addr, '.');
        if (ips.length != 4) {
            return false;
        }
        try {
            int ipa = Integer.parseInt(ips[0]);
            int ipb = Integer.parseInt(ips[1]);
            int ipc = Integer.parseInt(ips[2]);
            int ipd = Integer.parseInt(ips[3]);

            return (ipa >= 0) && (ipa <= 255) && (ipb >= 0) && (ipb <= 255) && (ipc >= 0) && (ipc <= 255) && (ipd >= 0) && (ipd <= 255);
        } catch (Exception localException) {
        }
        return false;
    }

    public static List arrayToList(String[] str) {
        List list = Arrays.asList(str);
        return list;
    }

    public static String[] listToArray(List list) {
        int size = list.size();
        String[] str = (String[]) list.toArray(new String[size]);
        return str;
    }

    public static String nullToStr(String s) {
        return nullToStr(s, "");
    }

    public static String nullToStr(String s, String def) {
        if ((s == null) || (s.length() < 1)) {
            return def;
        }
        return s;
    }

    public static String getDiamond(String url) {
        try {
            URL neturl = new URL(url);
            return neturl.getHost().replace("www.", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMainDiamond(String url) {
        return getMainDiamond(url, "");
    }

    public static String getMainDiamond(String url, String def) {
        try {
            Pattern p = Pattern.compile("(?<= http://|\\.)[^.]*?(\\.(com|cn|net|org|biz|gov|hk|info|cc|tv|it|dk|ie|co|uk|se|fr|in))+", 2);
            Matcher matcher = p.matcher(url);
            matcher.find();
            return matcher.group();
        } catch (Exception e) {
        }
        return def;
    }

    public static void main(String[] arg)
            throws UnsupportedEncodingException {
        System.err.println(getUTF8URLEncoder("拍"));
    }

    public static String getCharSetStr(String str, String oldCharSet, String newCharSet) {
        if ((str == "") || (str == null)) {
            return "";
        }
        try {
            str = new String(str.getBytes(oldCharSet), newCharSet);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getEncoding(String src, String srcCharset, String objCharset) {
        try {
            if (src == null) {
                return "";
            }
            if (srcCharset == null) {
                srcCharset = "ISO-8859-1";
            }
            if (objCharset == null) {
                objCharset = "UTF-8";
            }
            return new String(src.getBytes(srcCharset), objCharset);
        } catch (Exception e) {
        }
        return "";
    }

    public static String getUTF8URLDecoder(String src)
            throws UnsupportedEncodingException {
        return URLDecoder.decode(src, "utf-8");
    }

    public static String getUTF8URLEncoder(String src)
            throws UnsupportedEncodingException {
        return URLEncoder.encode(src, "utf-8");
    }

    public static String getUtf8Encoding(String scr) {
        return getEncoding(scr, null, null);
    }

    public static String nullToEmpty(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    public static long getTablename(int divisor, long dividend) {
        long i = dividend % divisor;
        return i;
    }

    public static String equalsIgnoreCase(String str, String objStr, String equalsStr, String noEqualsStr) {
        if (noEqualsStr == null) {
            noEqualsStr = "";
        }
        if (equalsStr == null) {
            equalsStr = "";
        }
        if (str == null) {
            str = "";
        }
        if (objStr == null) {
            objStr = "";
        }
        if (str.equalsIgnoreCase(objStr)) {
            return equalsStr;
        }
        return noEqualsStr;
    }

    public static String getString(String[] objs) {
        if ((objs == null) || (objs.length < 1)) {
            return "";
        }
        String ids = objs[0];
        for (int i = 1; i < objs.length; i++) {
            ids = ids + "," + objs[i];
        }
        return ids;
    }

    public static String getString(String orig, String[] objs) {
        if (objs.length < 1) {
            return orig;
        }
        for (int i = 0; i < objs.length; i++) {
            if (orig.indexOf(objs[i]) == -1) {
                orig = orig + objs[i] + ",";
            }
        }
        return orig.substring(0, orig.length() - 1);
    }

    public static boolean isEmpty(String str) {
        return (str == null) || (str.length() == 0);
    }

    public static boolean isNotEmpty(String str) {
        return !StringUtils.isEmpty(str);
    }

    public static boolean isBlank(String str) {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0)) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(String str) {
        return !StringUtils.isBlank(str);
    }

    /**
     * @deprecated
     */
    public static String clean(String str) {
        return str == null ? "" : str.trim();
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static String trimToNull(String str) {
        String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    public static String trimToEmpty(String str) {
        return str == null ? "" : str.trim();
    }

    public static String strip(String str) {
        return strip(str, null);
    }

    public static String stripToNull(String str) {
        if (str == null) {
            return null;
        }
        str = strip(str, null);
        return str.length() == 0 ? null : str;
    }

    public static String stripToEmpty(String str) {
        return str == null ? "" : strip(str, null);
    }

    public static String strip(String str, String stripChars) {
        if (isEmpty(str)) {
            return str;
        }
        str = stripStart(str, stripChars);
        return stripEnd(str, stripChars);
    }

    public static String stripStart(String str, String stripChars) {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0)) {
            return str;
        }
        int start = 0;
        if (stripChars == null) {
            do {
                start++;
                if (start == strLen) {
                    break;
                }
            } while (Character.isWhitespace(str.charAt(start)));
        } else {
            if (stripChars.length() == 0) {
                return str;
            }
            while ((start != strLen) && (stripChars.indexOf(str.charAt(start)) != -1)) {
                start++;
            }
        }
        return str.substring(start);
    }

    public static String stripEnd(String str, String stripChars) {
        int end;
        if ((str == null) || ((end = str.length()) == 0)) {
            return str;
        }
        if (stripChars == null) {
            do {
                end--;
                if (end == 0) {
                    break;
                }
            } while (Character.isWhitespace(str.charAt(end - 1)));
        } else {
            if (stripChars.length() == 0) {
                return str;
            }
            while ((end != 0) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
                end--;
            }
        }
        return str.substring(0, end);
    }

    public static String[] stripAll(String[] strs) {
        return stripAll(strs, null);
    }

    public static String[] stripAll(String[] strs, String stripChars) {
        int strsLen;
        if ((strs == null) || ((strsLen = strs.length) == 0)) {
            return strs;
        }
        String[] newArr = new String[strsLen];
        for (int i = 0; i < strsLen; i++) {
            newArr[i] = strip(strs[i], stripChars);
        }
        return newArr;
    }

    public static boolean equals(String str1, String str2) {
        return str1 == null ? false : str2 == null ? true : str1.equals(str2);
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 == null ? false : str2 == null ? true : str1.equalsIgnoreCase(str2);
    }

    public static int indexOf(String str, char searchChar) {
        if (isEmpty(str)) {
            return -1;
        }
        return str.indexOf(searchChar);
    }

    public static int indexOf(String str, char searchChar, int startPos) {
        if (isEmpty(str)) {
            return -1;
        }
        return str.indexOf(searchChar, startPos);
    }

    public static int indexOf(String str, String searchStr) {
        if ((str == null) || (searchStr == null)) {
            return -1;
        }
        return str.indexOf(searchStr);
    }

    public static int ordinalIndexOf(String str, String searchStr, int ordinal) {
        if ((str == null) || (searchStr == null) || (ordinal <= 0)) {
            return -1;
        }
        if (searchStr.length() == 0) {
            return 0;
        }
        int found = 0;
        int index = -1;
        do {
            index = str.indexOf(searchStr, index + 1);
            if (index < 0) {
                return index;
            }
            found++;
        } while (found < ordinal);
        return index;
    }

    public static int indexOf(String str, String searchStr, int startPos) {
        if ((str == null) || (searchStr == null)) {
            return -1;
        }
        if ((searchStr.length() == 0) && (startPos >= str.length())) {
            return str.length();
        }
        return str.indexOf(searchStr, startPos);
    }

    public static int lastIndexOf(String str, char searchChar) {
        if (isEmpty(str)) {
            return -1;
        }
        return str.lastIndexOf(searchChar);
    }

    public static int lastIndexOf(String str, char searchChar, int startPos) {
        if (isEmpty(str)) {
            return -1;
        }
        return str.lastIndexOf(searchChar, startPos);
    }

    public static int lastIndexOf(String str, String searchStr) {
        if ((str == null) || (searchStr == null)) {
            return -1;
        }
        return str.lastIndexOf(searchStr);
    }

    public static int lastIndexOf(String str, String searchStr, int startPos) {
        if ((str == null) || (searchStr == null)) {
            return -1;
        }
        return str.lastIndexOf(searchStr, startPos);
    }

    public static boolean contains(String str, char searchChar) {
        if (isEmpty(str)) {
            return false;
        }
        return str.indexOf(searchChar) >= 0;
    }

    public static boolean contains(String str, String searchStr) {
        if ((str == null) || (searchStr == null)) {
            return false;
        }
        return str.indexOf(searchStr) >= 0;
    }

    public static boolean containsIgnoreCase(String str, String searchStr) {
        if ((str == null) || (searchStr == null)) {
            return false;
        }
        return contains(str.toUpperCase(), searchStr.toUpperCase());
    }

    public static int indexOfAny(String str, char[] searchChars) {
        if ((isEmpty(str)) || (ArrayUtils.isEmpty(searchChars))) {
            return -1;
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            for (int j = 0; j < searchChars.length; j++) {
                if (searchChars[j] == ch) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int indexOfAny(String str, String searchChars) {
        if ((isEmpty(str)) || (isEmpty(searchChars))) {
            return -1;
        }
        return indexOfAny(str, searchChars.toCharArray());
    }

    public static int indexOfAnyBut(String str, char[] searchChars) {
        if ((isEmpty(str)) || (ArrayUtils.isEmpty(searchChars))) {
            return -1;
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int j = 0;
            while (searchChars[j] != ch) {
                j++;
                if (j >= searchChars.length) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int indexOfAnyBut(String str, String searchChars) {
        if ((isEmpty(str)) || (isEmpty(searchChars))) {
            return -1;
        }
        for (int i = 0; i < str.length(); i++) {
            if (searchChars.indexOf(str.charAt(i)) < 0) {
                return i;
            }
        }
        return -1;
    }

    public static boolean containsOnly(String str, char[] valid) {
        if ((valid == null) || (str == null)) {
            return false;
        }
        if (str.length() == 0) {
            return true;
        }
        if (valid.length == 0) {
            return false;
        }
        return indexOfAnyBut(str, valid) == -1;
    }

    public static boolean containsOnly(String str, String validChars) {
        if ((str == null) || (validChars == null)) {
            return false;
        }
        return containsOnly(str, validChars.toCharArray());
    }

    public static boolean containsNone(String str, char[] invalidChars) {
        if ((str == null) || (invalidChars == null)) {
            return true;
        }
        int strSize = str.length();
        int validSize = invalidChars.length;
        for (int i = 0; i < strSize; i++) {
            char ch = str.charAt(i);
            for (int j = 0; j < validSize; j++) {
                if (invalidChars[j] == ch) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int indexOfAny(String str, String[] searchStrs) {
        if ((str == null) || (searchStrs == null)) {
            return -1;
        }
        int sz = searchStrs.length;


        int ret = 2147483647;

        int tmp = 0;
        for (int i = 0; i < sz; i++) {
            String search = searchStrs[i];
            if (search != null) {
                tmp = str.indexOf(search);
                if (tmp != -1) {
                    if (tmp < ret) {
                        ret = tmp;
                    }
                }
            }
        }
        return ret == 2147483647 ? -1 : ret;
    }

    public static int lastIndexOfAny(String str, String[] searchStrs) {
        if ((str == null) || (searchStrs == null)) {
            return -1;
        }
        int sz = searchStrs.length;
        int ret = -1;
        int tmp = 0;
        for (int i = 0; i < sz; i++) {
            String search = searchStrs[i];
            if (search != null) {
                tmp = str.lastIndexOf(search);
                if (tmp > ret) {
                    ret = tmp;
                }
            }
        }
        return ret;
    }

    public static String[] substringsBetween(String str, String open, String close) {
        if ((str == null) || (isEmpty(open)) || (isEmpty(close))) {
            return null;
        }
        int strLen = str.length();
        if (strLen == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        int closeLen = close.length();
        int openLen = open.length();
        List list = new ArrayList();
        int pos = 0;
        while (pos < strLen - closeLen) {
            int start = str.indexOf(open, pos);
            if (start < 0) {
                break;
            }
            start += openLen;
            int end = str.indexOf(close, start);
            if (end < 0) {
                break;
            }
            list.add(str.substring(start, end));
            pos = end + closeLen;
        }
        if (list.size() > 0) {
            return (String[]) list.toArray(new String[list.size()]);
        }
        return null;
    }

    /**
     * @deprecated
     */
    public static String getNestedString(String str, String tag) {
        return substringBetween(str, tag, tag);
    }

    /**
     * @deprecated
     */
    public static String getNestedString(String str, String open, String close) {
        return substringBetween(str, open, close);
    }

    public static String[] split(String str) {
        return split(str, null, -1);
    }

    public static String[] split(String str, char separatorChar) {
        return splitWorker(str, separatorChar, false);
    }

    public static String[] split(String str, String separatorChars) {
        return splitWorker(str, separatorChars, -1, false);
    }

    public static String[] split(String str, String separatorChars, int max) {
        return splitWorker(str, separatorChars, max, false);
    }

    public static String[] splitByWholeSeparator(String str, String separator) {
        return splitByWholeSeparator(str, separator, -1);
    }

    public static String[] splitByWholeSeparator(String str, String separator, int max) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        if ((separator == null) || ("".equals(separator))) {
            return split(str, null, max);
        }
        int separatorLength = separator.length();

        ArrayList substrings = new ArrayList();
        int numberOfSubstrings = 0;
        int beg = 0;
        int end = 0;
        while (end < len) {
            end = str.indexOf(separator, beg);
            if (end > -1) {
                if (end > beg) {
                    numberOfSubstrings++;
                    if (numberOfSubstrings == max) {
                        end = len;
                        substrings.add(str.substring(beg));
                    } else {
                        substrings.add(str.substring(beg, end));


                        beg = end + separatorLength;
                    }
                } else {
                    beg = end + separatorLength;
                }
            } else {
                substrings.add(str.substring(beg));
                end = len;
            }
        }
        return (String[]) substrings.toArray(new String[substrings.size()]);
    }

    public static String[] splitPreserveAllTokens(String str) {
        return splitWorker(str, null, -1, true);
    }

    public static String[] splitPreserveAllTokens(String str, char separatorChar) {
        return splitWorker(str, separatorChar, true);
    }

    private static String[] splitWorker(String str, char separatorChar, boolean preserveAllTokens) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        List list = new ArrayList();
        int i = 0;
        int start = 0;
        boolean match = false;
        boolean lastMatch = false;
        while (i < len) {
            if (str.charAt(i) == separatorChar) {
                if ((match) || (preserveAllTokens)) {
                    list.add(str.substring(start, i));
                    match = false;
                    lastMatch = true;
                }
                i++;
                start = i;
            } else {
                lastMatch = false;

                match = true;
                i++;
            }
        }
        if ((match) || ((preserveAllTokens) && (lastMatch))) {
            list.add(str.substring(start, i));
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    public static String[] splitPreserveAllTokens(String str, String separatorChars) {
        return splitWorker(str, separatorChars, -1, true);
    }

    public static String[] splitPreserveAllTokens(String str, String separatorChars, int max) {
        return splitWorker(str, separatorChars, max, true);
    }

    private static String[] splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        List list = new ArrayList();
        int sizePlus1 = 1;
        int i = 0;
        int start = 0;
        boolean match = false;
        boolean lastMatch = false;
        if (separatorChars == null) {
            while (i < len) {
                if (Character.isWhitespace(str.charAt(i))) {
                    if ((match) || (preserveAllTokens)) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    i++;
                    start = i;
                } else {
                    lastMatch = false;

                    match = true;
                    i++;
                }
            }
        } else if (separatorChars.length() == 1) {
            final char sep = separatorChars.charAt(0);
            while (i < len) {
                if (str.charAt(i) == sep) {
                    if ((match) || (preserveAllTokens)) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    i++;
                    start = i;
                } else {
                    lastMatch = false;

                    match = true;
                    i++;
                }
            }
        } else {
            while (i < len) {
                char sep;
                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                    if ((match) || (preserveAllTokens)) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    i++;
                    start = i;
                } else {
                    lastMatch = false;

                    match = true;
                    i++;
                }
            }
        }
        if ((match) || ((preserveAllTokens) && (lastMatch))) {
            list.add(str.substring(start, i));
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * @deprecated
     */
    public static String concatenate(Object[] array) {
        return join(array, null);
    }

    public static String join(Object[] array) {
        return join(array, null);
    }

    public static String join(Object[] array, char separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    public static String join(Object[] array, char separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        int bufSize = endIndex - startIndex;
        if (bufSize <= 0) {
            return "";
        }
        bufSize *= ((array[startIndex] == null ? 16 : array[startIndex].toString().length()) + 1);
        StringBuffer buf = new StringBuffer(bufSize);
        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = "";
        }
        int bufSize = endIndex - startIndex;
        if (bufSize <= 0) {
            return "";
        }
        bufSize *= ((array[startIndex] == null ? 16 : array[startIndex].toString().length()) + separator.length());

        StringBuffer buf = new StringBuffer(bufSize);
        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    public static String join(Iterator iterator, char separator) {
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return "";
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return ObjectUtils.toString(first);
        }
        StringBuffer buf = new StringBuffer(256);
        if (first != null) {
            buf.append(first);
        }
        while (iterator.hasNext()) {
            buf.append(separator);
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    public static String join(Iterator iterator, String separator) {
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return "";
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return ObjectUtils.toString(first);
        }
        StringBuffer buf = new StringBuffer(256);
        if (first != null) {
            buf.append(first);
        }
        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    public static String join(Collection collection, char separator) {
        if (collection == null) {
            return null;
        }
        return join(collection.iterator(), separator);
    }

    public static String join(Collection collection, String separator) {
        if (collection == null) {
            return null;
        }
        return join(collection.iterator(), separator);
    }

    /**
     * @deprecated
     */
    public static String deleteSpaces(String str) {
        if (str == null) {
            return null;
        }
        return CharSetUtils.delete(str, new String[]{" \t\r\n\b"});
    }

    public static String removeStart(String str, String remove) {
        if ((isEmpty(str)) || (isEmpty(remove))) {
            return str;
        }
        if (str.startsWith(remove)) {
            return str.substring(remove.length());
        }
        return str;
    }

    public static String removeEnd(String str, String remove) {
        if ((isEmpty(str)) || (isEmpty(remove))) {
            return str;
        }
        if (str.endsWith(remove)) {
            return str.substring(0, str.length() - remove.length());
        }
        return str;
    }

    public static String remove(String str, String remove) {
        if ((isEmpty(str)) || (isEmpty(remove))) {
            return str;
        }
        return replace(str, remove, "", -1);
    }

    public static String remove(String str, char remove) {
        if ((isEmpty(str)) || (str.indexOf(remove) == -1)) {
            return str;
        }
        char[] chars = str.toCharArray();
        int pos = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != remove) {
                chars[(pos++)] = chars[i];
            }
        }
        return new String(chars, 0, pos);
    }


    /**
     * @deprecated
     */
    public static String overlayString(String text, String overlay, int start, int end) {
        return start + overlay.length() + text.length() - end + 1 + text.substring(0, start) + overlay +
                text.substring(end);
    }

    /**
     * @deprecated
     */
    public static String chompLast(String str) {
        return chompLast(str, "\n");
    }

    /**
     * @deprecated
     */
    public static String chompLast(String str, String sep) {
        if (str.length() == 0) {
            return str;
        }
        String sub = str.substring(str.length() - sep.length());
        if (sep.equals(sub)) {
            return str.substring(0, str.length() - sep.length());
        }
        return str;
    }

    /**
     * @deprecated
     */
    public static String getChomp(String str, String sep) {
        int idx = str.lastIndexOf(sep);
        if (idx == str.length() - sep.length()) {
            return sep;
        }
        if (idx != -1) {
            return str.substring(idx);
        }
        return "";
    }

    /**
     * @deprecated
     */
    public static String prechomp(String str, String sep) {
        int idx = str.indexOf(sep);
        if (idx != -1) {
            return str.substring(idx + sep.length());
        }
        return str;
    }

    /**
     * @deprecated
     */
    public static String getPrechomp(String str, String sep) {
        int idx = str.indexOf(sep);
        if (idx != -1) {
            return str.substring(0, idx + sep.length());
        }
        return "";
    }

    /**
     * @deprecated
     */
    public static String chopNewline(String str) {
        int lastIdx = str.length() - 1;
        if (lastIdx <= 0) {
            return "";
        }
        char last = str.charAt(lastIdx);
        if (last == '\n') {
            if (str.charAt(lastIdx - 1) == '\r') {
                lastIdx--;
            }
        } else {
            lastIdx++;
        }
        return str.substring(0, lastIdx);
    }

    /**
     * @deprecated
     */
    public static String escape(String str) {
        return StringEscapeUtils.escapeJava(str);
    }

    private static String padding(int repeat, char padChar)
            throws IndexOutOfBoundsException {
        if (repeat < 0) {
            throw new IndexOutOfBoundsException("Cannot pad a negative amount: " + repeat);
        }
        char[] buf = new char[repeat];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = padChar;
        }
        return new String(buf);
    }

    public static String rightPad(String str, int size) {
        return rightPad(str, size, ' ');
    }

    public static String rightPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            return str;
        }
        if (pads > PAD_LIMIT) {
            return rightPad(str, size, String.valueOf(padChar));
        }
        return str.concat(padding(pads, padChar));
    }

    public static String rightPad(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str;
        }
        if ((padLen == 1) && (pads <= PAD_LIMIT)) {
            return rightPad(str, size, padStr.charAt(0));
        }
        if (pads == padLen) {
            return str.concat(padStr);
        }
        if (pads < padLen) {
            return str.concat(padStr.substring(0, pads));
        }
        char[] padding = new char[pads];
        char[] padChars = padStr.toCharArray();
        for (int i = 0; i < pads; i++) {
            padding[i] = padChars[(i % padLen)];
        }
        return str.concat(new String(padding));
    }

    public static String leftPad(String str, int size) {
        return leftPad(str, size, ' ');
    }

    public static String leftPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            return str;
        }
        if (pads > PAD_LIMIT) {
            return leftPad(str, size, String.valueOf(padChar));
        }
        return padding(pads, padChar).concat(str);
    }

    public static String leftPad(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str;
        }
        if ((padLen == 1) && (pads <= PAD_LIMIT)) {
            return leftPad(str, size, padStr.charAt(0));
        }
        if (pads == padLen) {
            return padStr.concat(str);
        }
        if (pads < padLen) {
            return padStr.substring(0, pads).concat(str);
        }
        char[] padding = new char[pads];
        char[] padChars = padStr.toCharArray();
        for (int i = 0; i < pads; i++) {
            padding[i] = padChars[(i % padLen)];
        }
        return new String(padding).concat(str);
    }

    public static String upperCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase();
    }

    public static String lowerCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase();
    }

    public static String capitalize(String str) {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0)) {
            return str;
        }
        return strLen + Character.toTitleCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * @deprecated
     */
    public static String capitalise(String str) {
        return capitalize(str);
    }

    public static String uncapitalize(String str) {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0)) {
            return str;
        }
        return strLen + Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * @deprecated
     */
    public static String uncapitalise(String str) {
        return uncapitalize(str);
    }

    public static String swapCase(String str) {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0)) {
            return str;
        }
        StringBuffer buffer = new StringBuffer(strLen);

        char ch = '\000';
        for (int i = 0; i < strLen; i++) {
            ch = str.charAt(i);
            if (Character.isUpperCase(ch)) {
                ch = Character.toLowerCase(ch);
            } else if (Character.isTitleCase(ch)) {
                ch = Character.toLowerCase(ch);
            } else if (Character.isLowerCase(ch)) {
                ch = Character.toUpperCase(ch);
            }
            buffer.append(ch);
        }
        return buffer.toString();
    }

    /**
     * @deprecated
     */
    public static String capitaliseAllWords(String str) {
        return WordUtils.capitalize(str);
    }

    public static boolean isAlpha(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAlphaSpace(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if ((!Character.isLetter(str.charAt(i))) && (str.charAt(i) != ' ')) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAlphanumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isLetterOrDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAlphanumericSpace(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if ((!Character.isLetterOrDigit(str.charAt(i))) && (str.charAt(i) != ' ')) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAsciiPrintable(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (!CharUtils.isAsciiPrintable(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumericSpace(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if ((!Character.isDigit(str.charAt(i))) && (str.charAt(i) != ' ')) {
                return false;
            }
        }
        return true;
    }

    public static boolean isWhitespace(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String defaultString(String str) {
        return str == null ? "" : str;
    }

    public static String defaultString(String str, String defaultStr) {
        return str == null ? defaultStr : str;
    }

    public static String defaultIfEmpty(String str, String defaultStr) {
        return StringUtils.isEmpty(str) ? defaultStr : str;
    }

    /**
     * @deprecated
     */
    public static String reverseDelimitedString(String str, String separatorChars) {
        if (str == null) {
            return null;
        }
        String[] strs = split(str, separatorChars);
        ArrayUtils.reverse(strs);
        if (separatorChars == null) {
            return join(strs, ' ');
        }
        return join(strs, separatorChars);
    }

    /**
     * 检查字符串是是否不包含指定字符集合中的字符。
     * <p>
     * <p>
     * 如果字符串为<code>null</code>，则返回<code>false</code>。 如果字符集合为<code>null</code>则返回<code>true</code>。
     * 但是空字符串永远返回<code>true</code>.
     * </p>
     * <pre>
     * StringUtil.containsNone(null, *)       = true
     * StringUtil.containsNone(*, null)       = true
     * StringUtil.containsNone("", *)         = true
     * StringUtil.containsNone("ab", "")      = true
     * StringUtil.containsNone("abab", "xyz") = true
     * StringUtil.containsNone("ab1", "xyz")  = true
     * StringUtil.containsNone("abz", "xyz")  = false
     * </pre>
     *
     * @param str          要扫描的字符串
     * @param invalidChars 要查找的字符串
     * @return 如果找到，则返回<code>true</code>
     */
    public static boolean containsNone(String str, String invalidChars) {
        if ((str == null) || (invalidChars == null)) {
            return true;
        }

        return containsNone(str, invalidChars.toCharArray());
    }

    /**
     * 取得指定子串在字符串中出现的次数。
     * <p>
     * <p>
     * 如果字符串为<code>null</code>或空，则返回<code>0</code>。
     * <pre>
     * StringUtil.countMatches(null, *)       = 0
     * StringUtil.countMatches("", *)         = 0
     * StringUtil.countMatches("abba", null)  = 0
     * StringUtil.countMatches("abba", "")    = 0
     * StringUtil.countMatches("abba", "a")   = 2
     * StringUtil.countMatches("abba", "ab")  = 1
     * StringUtil.countMatches("abba", "xxx") = 0
     * </pre>
     * </p>
     *
     * @param str    要扫描的字符串
     * @param subStr 子字符串
     * @return 子串在字符串中出现的次数，如果字符串为<code>null</code>或空，则返回<code>0</code>
     */
    public static int countMatches(String str, String subStr) {
        if ((str == null) || (str.length() == 0) || (subStr == null) || (subStr.length() == 0)) {
            return 0;
        }

        int count = 0;
        int index = 0;

        while ((index = str.indexOf(subStr, index)) != -1) {
            count++;
            index += subStr.length();
        }

        return count;
    }

    /**
     * 取得最后一个的分隔子串之前的子串。
     * <p>
     * <p>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>或未找到该子串，则返回原字符串。
     * <pre>
     * StringUtil.substringBeforeLast(null, *)      = null
     * StringUtil.substringBeforeLast("", *)        = ""
     * StringUtil.substringBeforeLast("abcba", "b") = "abc"
     * StringUtil.substringBeforeLast("abc", "c")   = "ab"
     * StringUtil.substringBeforeLast("a", "a")     = ""
     * StringUtil.substringBeforeLast("a", "z")     = "a"
     * StringUtil.substringBeforeLast("a", null)    = "a"
     * StringUtil.substringBeforeLast("a", "")      = "a"
     * </pre>
     * </p>
     *
     * @param str       字符串
     * @param separator 要搜索的分隔子串
     * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
     */
    public static String substringBeforeLast(String str, String separator) {
        if ((str == null) || (separator == null) || (str.length() == 0)
                || (separator.length() == 0)) {
            return str;
        }

        int pos = str.lastIndexOf(separator);

        if (pos == -1) {
            return str;
        }

        return str.substring(0, pos);
    }

    /**
     * 取得指定分隔符的前两次出现之间的子串。
     * <p>
     * <p>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>，则返回<code>null</code>。
     * <pre>
     * StringUtil.substringBetween(null, *)            = null
     * StringUtil.substringBetween("", "")             = ""
     * StringUtil.substringBetween("", "tag")          = null
     * StringUtil.substringBetween("tagabctag", null)  = null
     * StringUtil.substringBetween("tagabctag", "")    = ""
     * StringUtil.substringBetween("tagabctag", "tag") = "abc"
     * </pre>
     * </p>
     *
     * @param str 字符串
     * @param tag 要搜索的分隔子串
     * @return 子串，如果原始串为<code>null</code>或未找到分隔子串，则返回<code>null</code>
     */
    public static String substringBetween(String str, String tag) {
        return substringBetween(str, tag, tag, 0);
    }

    /**
     * 取得两个分隔符之间的子串。
     * <p>
     * <p>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>，则返回<code>null</code>。
     * <pre>
     * StringUtil.substringBetween(null, *, *)          = null
     * StringUtil.substringBetween("", "", "")          = ""
     * StringUtil.substringBetween("", "", "tag")       = null
     * StringUtil.substringBetween("", "tag", "tag")    = null
     * StringUtil.substringBetween("yabcz", null, null) = null
     * StringUtil.substringBetween("yabcz", "", "")     = ""
     * StringUtil.substringBetween("yabcz", "y", "z")   = "abc"
     * StringUtil.substringBetween("yabczyabcz", "y", "z")   = "abc"
     * </pre>
     * </p>
     *
     * @param str   字符串
     * @param open  要搜索的分隔子串1
     * @param close 要搜索的分隔子串2
     * @return 子串，如果原始串为<code>null</code>或未找到分隔子串，则返回<code>null</code>
     */
    public static String substringBetween(String str, String open, String close) {
        return substringBetween(str, open, close, 0);
    }

    /**
     * 取得两个分隔符之间的子串。
     * <p>
     * <p>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>，则返回<code>null</code>。
     * <pre>
     * StringUtil.substringBetween(null, *, *)          = null
     * StringUtil.substringBetween("", "", "")          = ""
     * StringUtil.substringBetween("", "", "tag")       = null
     * StringUtil.substringBetween("", "tag", "tag")    = null
     * StringUtil.substringBetween("yabcz", null, null) = null
     * StringUtil.substringBetween("yabcz", "", "")     = ""
     * StringUtil.substringBetween("yabcz", "y", "z")   = "abc"
     * StringUtil.substringBetween("yabczyabcz", "y", "z")   = "abc"
     * </pre>
     * </p>
     *
     * @param str       字符串
     * @param open      要搜索的分隔子串1
     * @param close     要搜索的分隔子串2
     * @param fromIndex 从指定index处搜索
     * @return 子串，如果原始串为<code>null</code>或未找到分隔子串，则返回<code>null</code>
     */
    public static String substringBetween(String str, String open, String close, int fromIndex) {
        if ((str == null) || (open == null) || (close == null)) {
            return null;
        }

        int start = str.indexOf(open, fromIndex);

        if (start != -1) {
            int end = str.indexOf(close, start + open.length());

            if (end != -1) {
                return str.substring(start + open.length(), end);
            }
        }

        return null;
    }

  /* ============================================================================ */
  /*  删除字符。                                                                  */
  /* ============================================================================ */

    /**
     * 删除所有在<code>Character.isWhitespace(char)</code>中所定义的空白。
     * <pre>
     * StringUtil.deleteWhitespace(null)         = null
     * StringUtil.deleteWhitespace("")           = ""
     * StringUtil.deleteWhitespace("abc")        = "abc"
     * StringUtil.deleteWhitespace("   ab  c  ") = "abc"
     * </pre>
     *
     * @param str 要处理的字符串
     * @return 去空白后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String deleteWhitespace(String str) {
        if (str == null) {
            return null;
        }

        int sz = str.length();
        StringBuffer buffer = new StringBuffer(sz);

        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                buffer.append(str.charAt(i));
            }
        }

        return buffer.toString();
    }

  /* ============================================================================ */
  /*  替换子串。                                                                  */
  /* ============================================================================ */

    /**
     * 替换指定的子串，只替换第一个出现的子串。
     * <p>
     * <p>
     * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>，则返回原字符串。
     * <pre>
     * StringUtil.replaceOnce(null, *, *)        = null
     * StringUtil.replaceOnce("", *, *)          = ""
     * StringUtil.replaceOnce("aba", null, null) = "aba"
     * StringUtil.replaceOnce("aba", null, null) = "aba"
     * StringUtil.replaceOnce("aba", "a", null)  = "aba"
     * StringUtil.replaceOnce("aba", "a", "")    = "ba"
     * StringUtil.replaceOnce("aba", "a", "z")   = "zba"
     * </pre>
     * </p>
     *
     * @param text 要扫描的字符串
     * @param repl 要搜索的子串
     * @param with 替换字符串
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replaceOnce(String text, String repl, String with) {
        return replace(text, repl, with, 1);
    }

    /**
     * 替换指定的子串，替换所有出现的子串。
     * <p>
     * <p>
     * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>，则返回原字符串。
     * <pre>
     * StringUtil.replace(null, *, *)        = null
     * StringUtil.replace("", *, *)          = ""
     * StringUtil.replace("aba", null, null) = "aba"
     * StringUtil.replace("aba", null, null) = "aba"
     * StringUtil.replace("aba", "a", null)  = "aba"
     * StringUtil.replace("aba", "a", "")    = "b"
     * StringUtil.replace("aba", "a", "z")   = "zbz"
     * </pre>
     * </p>
     *
     * @param text 要扫描的字符串
     * @param repl 要搜索的子串
     * @param with 替换字符串
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replace(String text, String repl, String with) {
        return replace(text, repl, with, -1);
    }

    /**
     * 替换指定的子串，替换指定的次数。
     * <p>
     * <p>
     * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>，则返回原字符串。
     * <pre>
     * StringUtil.replace(null, *, *, *)         = null
     * StringUtil.replace("", *, *, *)           = ""
     * StringUtil.replace("abaa", null, null, 1) = "abaa"
     * StringUtil.replace("abaa", null, null, 1) = "abaa"
     * StringUtil.replace("abaa", "a", null, 1)  = "abaa"
     * StringUtil.replace("abaa", "a", "", 1)    = "baa"
     * StringUtil.replace("abaa", "a", "z", 0)   = "abaa"
     * StringUtil.replace("abaa", "a", "z", 1)   = "zbaa"
     * StringUtil.replace("abaa", "a", "z", 2)   = "zbza"
     * StringUtil.replace("abaa", "a", "z", -1)  = "zbzz"
     * </pre>
     * </p>
     *
     * @param text 要扫描的字符串
     * @param repl 要搜索的子串
     * @param with 替换字符串
     * @param max  maximum number of values to replace, or <code>-1</code> if no maximum
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replace(String text, String repl, String with, int max) {
        if ((text == null) || (repl == null) || (with == null) || (repl.length() == 0)
                || (max == 0)) {
            return text;
        }

        StringBuffer buf = new StringBuffer(text.length());
        int start = 0;
        int end = 0;

        while ((end = text.indexOf(repl, start)) != -1) {
            buf.append(text.substring(start, end)).append(with);
            start = end + repl.length();

            if (--max == 0) {
                break;
            }
        }

        buf.append(text.substring(start));
        return buf.toString();
    }

    /**
     * 将字符串中所有指定的字符，替换成另一个。
     * <p>
     * <p>
     * 如果字符串为<code>null</code>则返回<code>null</code>。
     * <pre>
     * StringUtil.replaceChars(null, *, *)        = null
     * StringUtil.replaceChars("", *, *)          = ""
     * StringUtil.replaceChars("abcba", 'b', 'y') = "aycya"
     * StringUtil.replaceChars("abcba", 'z', 'y') = "abcba"
     * </pre>
     * </p>
     *
     * @param str         要扫描的字符串
     * @param searchChar  要搜索的字符
     * @param replaceChar 替换字符
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replaceChars(String str, char searchChar, char replaceChar) {
        if (str == null) {
            return null;
        }

        return str.replace(searchChar, replaceChar);
    }

    /**
     * 将字符串中所有指定的字符，替换成另一个。
     * <p>
     * <p>
     * 如果字符串为<code>null</code>则返回<code>null</code>。如果搜索字符串为<code>null</code>或空，则返回原字符串。
     * </p>
     * <p>
     * <p>
     * 例如： <code>replaceChars(&quot;hello&quot;, &quot;ho&quot;, &quot;jy&quot;) = jelly</code>。
     * </p>
     * <p>
     * <p>
     * 通常搜索字符串和替换字符串是等长的，如果搜索字符串比替换字符串长，则多余的字符将被删除。 如果搜索字符串比替换字符串短，则缺少的字符将被忽略。
     * <pre>
     * StringUtil.replaceChars(null, *, *)           = null
     * StringUtil.replaceChars("", *, *)             = ""
     * StringUtil.replaceChars("abc", null, *)       = "abc"
     * StringUtil.replaceChars("abc", "", *)         = "abc"
     * StringUtil.replaceChars("abc", "b", null)     = "ac"
     * StringUtil.replaceChars("abc", "b", "")       = "ac"
     * StringUtil.replaceChars("abcba", "bc", "yz")  = "ayzya"
     * StringUtil.replaceChars("abcba", "bc", "y")   = "ayya"
     * StringUtil.replaceChars("abcba", "bc", "yzx") = "ayzya"
     * </pre>
     * </p>
     *
     * @param str          要扫描的字符串
     * @param searchChars  要搜索的字符串
     * @param replaceChars 替换字符串
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replaceChars(String str, String searchChars, String replaceChars) {
        if ((str == null) || (str.length() == 0) || (searchChars == null)
                || (searchChars.length() == 0)) {
            return str;
        }

        char[] chars = str.toCharArray();
        int len = chars.length;
        boolean modified = false;

        for (int i = 0, isize = searchChars.length(); i < isize; i++) {
            char searchChar = searchChars.charAt(i);

            if ((replaceChars == null) || (i >= replaceChars.length())) {
                // 删除
                int pos = 0;

                for (int j = 0; j < len; j++) {
                    if (chars[j] != searchChar) {
                        chars[pos++] = chars[j];
                    } else {
                        modified = true;
                    }
                }

                len = pos;
            } else {
                // 替换
                for (int j = 0; j < len; j++) {
                    if (chars[j] == searchChar) {
                        chars[j] = replaceChars.charAt(i);
                        modified = true;
                    }
                }
            }
        }

        if (!modified) {
            return str;
        }

        return new String(chars, 0, len);
    }


    /**
     * 删除字符串末尾的指定字符串。如果字符串不以该字符串结尾，则什么也不做。
     * <pre>
     * StringUtil.chomp(null, *)         = null
     * StringUtil.chomp("", *)           = ""
     * StringUtil.chomp("foobar", "bar") = "foo"
     * StringUtil.chomp("foobar", "baz") = "foobar"
     * StringUtil.chomp("foo", "foo")    = ""
     * StringUtil.chomp("foo ", "foo")   = "foo "
     * StringUtil.chomp(" foo", "foo")   = " "
     * StringUtil.chomp("foo", "foooo")  = "foo"
     * StringUtil.chomp("foo", "")       = "foo"
     * StringUtil.chomp("foo", null)     = "foo"
     * </pre>
     *
     * @param str       要处理的字符串
     * @param separator 要删除的字符串
     * @return 不以指定字符串结尾的字符串，如果原始字串为<code>null</code>，则返回<code>null</code>
     */
    public static String chomp(String str, String separator) {
        if ((str == null) || (str.length() == 0) || (separator == null)) {
            return str;
        }

        if (str.endsWith(separator)) {
            return str.substring(0, str.length() - separator.length());
        }

        return str;
    }

    /**
     * 扩展并左对齐字符串，用空格<code>' '</code>填充右边。
     * <pre>
     * StringUtil.alignLeft(null, *)   = null
     * StringUtil.alignLeft("", 3)     = "   "
     * StringUtil.alignLeft("bat", 3)  = "bat"
     * StringUtil.alignLeft("bat", 5)  = "bat  "
     * StringUtil.alignLeft("bat", 1)  = "bat"
     * StringUtil.alignLeft("bat", -1) = "bat"
     * </pre>
     *
     * @param str  要对齐的字符串
     * @param size 扩展字符串到指定宽度
     * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String alignLeft(String str, int size) {
        return alignLeft(str, size, ' ');
    }

    /**
     * 扩展并左对齐字符串，用指定字符填充右边。
     * <pre>
     * StringUtil.alignLeft(null, *, *)     = null
     * StringUtil.alignLeft("", 3, 'z')     = "zzz"
     * StringUtil.alignLeft("bat", 3, 'z')  = "bat"
     * StringUtil.alignLeft("bat", 5, 'z')  = "batzz"
     * StringUtil.alignLeft("bat", 1, 'z')  = "bat"
     * StringUtil.alignLeft("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str     要对齐的字符串
     * @param size    扩展字符串到指定宽度
     * @param padChar 填充字符
     * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String alignLeft(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }

        int pads = size - str.length();

        if (pads <= 0) {
            return str;
        }

        return alignLeft(str, size, String.valueOf(padChar));
    }

    /**
     * 扩展并左对齐字符串，用指定字符串填充右边。
     * <pre>
     * StringUtil.alignLeft(null, *, *)      = null
     * StringUtil.alignLeft("", 3, "z")      = "zzz"
     * StringUtil.alignLeft("bat", 3, "yz")  = "bat"
     * StringUtil.alignLeft("bat", 5, "yz")  = "batyz"
     * StringUtil.alignLeft("bat", 8, "yz")  = "batyzyzy"
     * StringUtil.alignLeft("bat", 1, "yz")  = "bat"
     * StringUtil.alignLeft("bat", -1, "yz") = "bat"
     * StringUtil.alignLeft("bat", 5, null)  = "bat  "
     * StringUtil.alignLeft("bat", 5, "")    = "bat  "
     * </pre>
     *
     * @param str    要对齐的字符串
     * @param size   扩展字符串到指定宽度
     * @param padStr 填充字符串
     * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String alignLeft(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }

        if ((padStr == null) || (padStr.length() == 0)) {
            padStr = " ";
        }

        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;

        if (pads <= 0) {
            return str;
        }

        if (pads == padLen) {
            return str.concat(padStr);
        } else if (pads < padLen) {
            return str.concat(padStr.substring(0, pads));
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();

            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }

            return str.concat(new String(padding));
        }
    }

    /**
     * 扩展并右对齐字符串，用空格<code>' '</code>填充左边。
     * <pre>
     * StringUtil.alignRight(null, *)   = null
     * StringUtil.alignRight("", 3)     = "   "
     * StringUtil.alignRight("bat", 3)  = "bat"
     * StringUtil.alignRight("bat", 5)  = "  bat"
     * StringUtil.alignRight("bat", 1)  = "bat"
     * StringUtil.alignRight("bat", -1) = "bat"
     * </pre>
     *
     * @param str  要对齐的字符串
     * @param size 扩展字符串到指定宽度
     * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String alignRight(String str, int size) {
        return alignRight(str, size, ' ');
    }

    /**
     * 扩展并右对齐字符串，用指定字符填充左边。
     * <pre>
     * StringUtil.alignRight(null, *, *)     = null
     * StringUtil.alignRight("", 3, 'z')     = "zzz"
     * StringUtil.alignRight("bat", 3, 'z')  = "bat"
     * StringUtil.alignRight("bat", 5, 'z')  = "zzbat"
     * StringUtil.alignRight("bat", 1, 'z')  = "bat"
     * StringUtil.alignRight("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str     要对齐的字符串
     * @param size    扩展字符串到指定宽度
     * @param padChar 填充字符
     * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String alignRight(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }

        int pads = size - str.length();

        if (pads <= 0) {
            return str;
        }

        return alignRight(str, size, String.valueOf(padChar));
    }

    /**
     * 扩展并右对齐字符串，用指定字符串填充左边。
     * <pre>
     * StringUtil.alignRight(null, *, *)      = null
     * StringUtil.alignRight("", 3, "z")      = "zzz"
     * StringUtil.alignRight("bat", 3, "yz")  = "bat"
     * StringUtil.alignRight("bat", 5, "yz")  = "yzbat"
     * StringUtil.alignRight("bat", 8, "yz")  = "yzyzybat"
     * StringUtil.alignRight("bat", 1, "yz")  = "bat"
     * StringUtil.alignRight("bat", -1, "yz") = "bat"
     * StringUtil.alignRight("bat", 5, null)  = "  bat"
     * StringUtil.alignRight("bat", 5, "")    = "  bat"
     * </pre>
     *
     * @param str    要对齐的字符串
     * @param size   扩展字符串到指定宽度
     * @param padStr 填充字符串
     * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String alignRight(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }

        if ((padStr == null) || (padStr.length() == 0)) {
            padStr = " ";
        }

        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;

        if (pads <= 0) {
            return str;
        }

        if (pads == padLen) {
            return padStr.concat(str);
        } else if (pads < padLen) {
            return padStr.substring(0, pads).concat(str);
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();

            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }

            return new String(padding).concat(str);
        }
    }

    /**
     * 扩展并居中字符串，用空格<code>' '</code>填充两边。
     * <pre>
     * StringUtil.center(null, *)   = null
     * StringUtil.center("", 4)     = "    "
     * StringUtil.center("ab", -1)  = "ab"
     * StringUtil.center("ab", 4)   = " ab "
     * StringUtil.center("abcd", 2) = "abcd"
     * StringUtil.center("a", 4)    = " a  "
     * </pre>
     *
     * @param str  要对齐的字符串
     * @param size 扩展字符串到指定宽度
     * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String center(String str, int size) {
        return center(str, size, ' ');
    }

    /**
     * 扩展并居中字符串，用指定字符填充两边。
     * <pre>
     * StringUtil.center(null, *, *)     = null
     * StringUtil.center("", 4, ' ')     = "    "
     * StringUtil.center("ab", -1, ' ')  = "ab"
     * StringUtil.center("ab", 4, ' ')   = " ab "
     * StringUtil.center("abcd", 2, ' ') = "abcd"
     * StringUtil.center("a", 4, ' ')    = " a  "
     * StringUtil.center("a", 4, 'y')    = "yayy"
     * </pre>
     *
     * @param str     要对齐的字符串
     * @param size    扩展字符串到指定宽度
     * @param padChar 填充字符
     * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String center(String str, int size, char padChar) {
        if ((str == null) || (size <= 0)) {
            return str;
        }

        int strLen = str.length();
        int pads = size - strLen;

        if (pads <= 0) {
            return str;
        }

        str = alignRight(str, strLen + (pads / 2), padChar);
        str = alignLeft(str, size, padChar);
        return str;
    }

    /**
     * 扩展并居中字符串，用指定字符串填充两边。
     * <pre>
     * StringUtil.center(null, *, *)     = null
     * StringUtil.center("", 4, " ")     = "    "
     * StringUtil.center("ab", -1, " ")  = "ab"
     * StringUtil.center("ab", 4, " ")   = " ab "
     * StringUtil.center("abcd", 2, " ") = "abcd"
     * StringUtil.center("a", 4, " ")    = " a  "
     * StringUtil.center("a", 4, "yz")   = "yayz"
     * StringUtil.center("abc", 7, null) = "  abc  "
     * StringUtil.center("abc", 7, "")   = "  abc  "
     * </pre>
     *
     * @param str    要对齐的字符串
     * @param size   扩展字符串到指定宽度
     * @param padStr 填充字符串
     * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String center(String str, int size, String padStr) {
        if ((str == null) || (size <= 0)) {
            return str;
        }

        if ((padStr == null) || (padStr.length() == 0)) {
            padStr = " ";
        }

        int strLen = str.length();
        int pads = size - strLen;

        if (pads <= 0) {
            return str;
        }

        str = alignRight(str, strLen + (pads / 2), padStr);
        str = alignLeft(str, size, padStr);
        return str;
    }

  /* ============================================================================ */
  /*  反转字符串。                                                                */
  /* ============================================================================ */

    /**
     * 反转字符串中的字符顺序。
     * <p>
     * <p>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。
     * </p>
     * <pre>
     * StringUtil.reverse(null)  = null
     * StringUtil.reverse("")    = ""
     * StringUtil.reverse("bat") = "tab"
     * </pre>
     *
     * @param str 要反转的字符串
     * @return 反转后的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String reverse(String str) {
        if ((str == null) || (str.length() == 0)) {
            return str;
        }

        return new StringBuffer(str).reverse().toString();
    }

 /* ============================================================================ */
 /*  取得字符串的缩略。                                                          */
 /* ============================================================================ */

    /**
     * 将字符串转换成指定长度的缩略，例如： 将"Now is the time for all good men"转换成"Now is the time for..."。
     * <p>
     * <ul>
     * <li>
     * 如果<code>str</code>比<code>maxWidth</code>短，直接返回；
     * </li>
     * <li>
     * 否则将它转换成缩略：<code>substring(str, 0, max-3) + "..."</code>；
     * </li>
     * <li>
     * 如果<code>maxWidth</code>小于<code>4</code>抛出<code>IllegalArgumentException</code>；
     * </li>
     * <li>
     * 返回的字符串不可能长于指定的<code>maxWidth</code>。
     * </li>
     * </ul>
     * <p>
     * <pre>
     * StringUtil.abbreviate(null, *)      = null
     * StringUtil.abbreviate("", 4)        = ""
     * StringUtil.abbreviate("abcdefg", 6) = "abc..."
     * StringUtil.abbreviate("abcdefg", 7) = "abcdefg"
     * StringUtil.abbreviate("abcdefg", 8) = "abcdefg"
     * StringUtil.abbreviate("abcdefg", 4) = "a..."
     * StringUtil.abbreviate("abcdefg", 3) = IllegalArgumentException
     * </pre>
     *
     * @param str      要检查的字符串
     * @param maxWidth 最大长度，不小于<code>4</code>，如果小于<code>4</code>，则看作<code>4</code>
     * @return 字符串缩略，如果原始字符串为<code>null</code>则返回<code>null</code>
     */
    public static String abbreviate(String str, int maxWidth) {
        return abbreviate(str, 0, maxWidth);
    }

    /**
     * 4139      * 将字符串转换成指定长度的缩略，例如： 将"Now is the time for all good men"转换成"...is the time for..."。
     * 4140      *
     * 4141      * <p>
     * 4142      * 和<code>abbreviate(String, int)</code>类似，但是增加了一个“左边界”偏移量。
     * 4143      * 注意，“左边界”处的字符未必出现在结果字符串的最左边，但一定出现在结果字符串中。
     * </p>
     * <p>
     * <p>
     * 返回的字符串不可能长于指定的<code>maxWidth</code>。
     * <pre>
     * StringUtil.abbreviate(null, *, *)                = null
     * StringUtil.abbreviate("", 0, 4)                  = ""
     * StringUtil.abbreviate("abcdefghijklmno", -1, 10) = "abcdefg..."
     * StringUtil.abbreviate("abcdefghijklmno", 0, 10)  = "abcdefg..."
     * StringUtil.abbreviate("abcdefghijklmno", 1, 10)  = "abcdefg..."
     * StringUtil.abbreviate("abcdefghijklmno", 4, 10)  = "abcdefg..."
     * StringUtil.abbreviate("abcdefghijklmno", 5, 10)  = "...fghi..."
     * StringUtil.abbreviate("abcdefghijklmno", 6, 10)  = "...ghij..."
     * StringUtil.abbreviate("abcdefghijklmno", 8, 10)  = "...ijklmno"
     * StringUtil.abbreviate("abcdefghijklmno", 10, 10) = "...ijklmno"
     * StringUtil.abbreviate("abcdefghijklmno", 12, 10) = "...ijklmno"
     * StringUtil.abbreviate("abcdefghij", 0, 3)        = IllegalArgumentException
     * StringUtil.abbreviate("abcdefghij", 5, 6)        = IllegalArgumentException
     * </pre>
     * </p>
     *
     * @param str      要检查的字符串
     * @param offset   左边界偏移量
     * @param maxWidth 最大长度，不小于<code>4</code>，如果小于<code>4</code>，则看作<code>4</code>
     * @return 字符串缩略，如果原始字符串为<code>null</code>则返回<code>null</code>
     */
    public static String abbreviate(String str, int offset, int maxWidth) {
        if (str == null) {
            return null;
        }

        // 调整最大宽度
        if (maxWidth < 4) {
            maxWidth = 4;
        }

        if (str.length() <= maxWidth) {
            return str;
        }

        if (offset > str.length()) {
            offset = str.length();
        }

        if ((str.length() - offset) < (maxWidth - 3)) {
            offset = str.length() - (maxWidth - 3);
        }

        if (offset <= 4) {
            return str.substring(0, maxWidth - 3) + "...";
        }

        // 调整最大宽度
        if (maxWidth < 7) {
            maxWidth = 7;
        }

        if ((offset + (maxWidth - 3)) < str.length()) {
            return "..." + abbreviate(str.substring(offset), maxWidth - 3);
        }

        return "..." + str.substring(str.length() - (maxWidth - 3));
    }

    /**
     * 比较两个字符串，取得两字符串开始不同的索引值。
     * <pre>
     * StringUtil.indexOfDifference("i am a machine", "i am a robot")   = 7
     * StringUtil.indexOfDifference(null, null)                         = -1
     * StringUtil.indexOfDifference("", null)                           = -1
     * StringUtil.indexOfDifference("", "")                             = -1
     * StringUtil.indexOfDifference("", "abc")                          = 0
     * StringUtil.indexOfDifference("abc", "")                          = 0
     * StringUtil.indexOfDifference("abc", "abc")                       = -1
     * StringUtil.indexOfDifference("ab", "abxyz")                      = 2
     * StringUtil.indexOfDifference("abcde", "abxyz")                   = 2
     * StringUtil.indexOfDifference("abcde", "xyz")                     = 0
     * </pre>
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 两字符串开始产生差异的索引值，如果两字符串相同，则返回<code>-1</code>
     */
    public static int indexOfDifference(String str1, String str2) {
        if ((str1 == str2) || (str1 == null) || (str2 == null)) {
            return -1;
        }

        int i;

        for (i = 0; (i < str1.length()) && (i < str2.length()); ++i) {
            if (str1.charAt(i) != str2.charAt(i)) {
                break;
            }
        }

        if ((i < str2.length()) || (i < str1.length())) {
            return i;
        }

        return -1;
    }

    /**
     * 取得最小数。
     *
     * @param a 整数1
     * @param b 整数2
     * @param c 整数3
     * @return 三个数中的最小值
     */
    private static int minx(int a, int b, int c) {
        if (b < a) {
            a = b;
        }

        if (c < a) {
            a = c;
        }

        return a;
    }

    /**
     * 匹配字符串的相识度
     *
     * @param str
     * @param target
     * @return
     * @Date 2016.03.28 下午 15:25:25
     */
    private static int compare(String str, String target) {
        int d[][]; // 矩阵
        int n = str.length();
        int m = target.length();
        int i; // 遍历str的
        int j; // 遍历target的
        char ch1; // str的
        char ch2; // target的
        int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1

        if (n == 0) {
            return m;
        }

        if (m == 0) {
            return n;
        }

        d = new int[n + 1][m + 1];

        for (i = 0; i <= n; i++) { // 初始化第一列
            d[i][0] = i;
        }

        for (j = 0; j <= m; j++) { // 初始化第一行
            d[0][j] = j;
        }

        for (i = 1; i <= n; i++) { // 遍历str
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }

                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }

        return d[n][m];
    }

    private static int min(int one, int two, int three) {
        return (one = one < two ? one : two) < three ? one : three;
    }

    /**
     * 获取两字符串的相似度
     *
     * @param str
     * @param target
     * @return
     */

    public static float getSimilarityRatio(String str, String target) {
        return 1 - (float) compare(str, target) / Math.max(str.length(), target.length());

    }

    /**
     * 循环遍历查询最后一个字母的位置
     *
     * @param str
     * @return
     * @Date 2016.06.28 下午 16:03:00
     */
    public static int finalletter(String str) {
        int step = 0;        //记录字符的位置
        for (int i = 0; i < str.length(); i++) {    //遍历整个字符串
            char c = str.charAt(i);        //字符串中每个字符
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c < 'Z')) {    //英文字符
                step = i;        //记录英文字符的位置
            }
        }
        return step;
    }

    /**
     * 循环遍历查询最后一个汉字的位置
     *
     * @param str
     * @return
     * @Date 2016.06.28 下午 16:05:25
     */
    public static int lastcharacter(String str) {
        int y = 0;
        for (int index = 0; index < str.length() - 1; index++) {
            // 将每一个角标的字符(字母 汉字 空格等)一次转换成字符串
            String w = str.substring(index, index + 1);
            //  而java采用unicode编码，汉字的范围是 "\u4e00"（一）到"\u9fa5"（龥）
            if (w.compareTo("\u4e00") > 0 && w.compareTo("\u9fa5") < 0) {
                y = index;
                // 获取第一个汉字索引位置 跳出循环
            }
        }
        return y;
    }

    /**
     * 获取第一个空格的位置
     *
     * @param str
     * @return
     * @Date 2016.06.28 下午 16:53:25
     */
    public static int firstspace(String str) {
        int a = str.length();
        for (int z = 0; z < a; z++) {
            a = str.indexOf(" ", z);
            break;
        }
        return a;
    }

    /**
     * 判断第一个字符是否为空，为空者去后面的所有的值。
     * 判断最后一个字母和最后一个汉字的下标大小
     * 大于：获取第一个到最后一个字母的值
     * 小于：则反之
     *
     * @param str
     * @return
     * @Date 2016.06.28 下午 17:31:00
     */
    public static String takeIn(String str) {
        String string = new String();
        //获取商品名称的第一个字符
        string = str.subSequence(0, 1).toString();
        //判断是否为空
        if (string.equals(" ")) {//为空
            //获取第一个字符之后的所有值
            string = str.substring(2);
        } else {//不为空
            //获取当前值
            string = str;
        }
        //获取最后一个字母的下标
        int i = StringUtil.finalletter(string);
        //获取最后一个汉字的下标
        int x = StringUtil.lastcharacter(string);
        //获取第一个空格的位置
        int y = StringUtil.firstspace(string);
        //判断最后一个字母的下标是否大于最后一个汉字的下标
        if (i > x) {//大于
            //获取第一个到最后字母的值
            string = string.substring(y, i);
        } else {//小于
            //获取第一个到最后汉字的值
            string = string.substring(y, x);
        }
        string = string.substring(0, indexOfUtf(string) - 1);
        //从第一个字符开始截取至汉字之前
        return string;
    }

    /**
     * 获取出现的第一个中文汉字的位置
     *
     * @param string
     * @return
     */
    public static int indexOfUtf(String string) {
        String reg = "[\u4e00-\u9fa5]";
        int index = -1;
        if (string.matches(".*" + reg + ".*")) {
            index = string.split(reg)[0].length();
        }
        return index;
    }

    //去除数组中重复的记录
    public static String[] array_unique(String[] a) {
        // array_unique
        List<String> list = new LinkedList<String>();
        for (int i = 0; i < a.length; i++) {
            if (!list.contains(a[i])) {
                list.add(a[i]);
            }
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * 传入快递名称获取对应快递100接口的拼音字段
     * @param name
     * @return
     */
    public static String expressName(String name){
        String bindName = "顺丰快递:shunfeng,ems:ems,中国邮政:youzhengguonei,百世快递:huitongkuaidi,天天快递:tiantian," +
                "国通快递:guotongkuaidi,快捷快递:kuaijiesudi,申通快递:shentong,圆通快递:yuantong,韵达快递:yunda," +
                "中通快递:zhongtong,全峰快递:quanfengkuaidi,宅急送:zhaijisong,如风达:rufengda,速尔快递:suer,德邦物流:debangwuliu," +
                "佳吉快运:jiajiwuliu";
        String[] str =  bindName.split(",");
        for (String pinyinName : str){
            String[] kuaidi = pinyinName.split(":");
            if (kuaidi[0].equals(name)){
                return kuaidi[1];
            }
        }
        return name;
    }

}

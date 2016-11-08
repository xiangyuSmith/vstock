package com.vstock.admin.base.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by xiangyu on 2016/6/30.
 */
public class StringsTest {
    public static void main(String[] args){
        String sss = delspecialsign("Adidas EQT Guidance '93 Pusha T \"King Push\"");
        System.out.print(sss);
    }
    public static String delspecialsign(String str){
        if(str.indexOf("?")!=-1){
            str = str.replaceAll("[?]", "");
        }
        if(str.indexOf("*")!=-1){
            str = str.replaceAll("[*]", "");
        }
        if(str.indexOf("\\")!=-1){
            str = str.replaceAll("\\\\", "");//去掉\
        }
        if(str.indexOf("/")!=-1){
            str = str.replaceAll("/", "");
        }
        if(str.indexOf(":")!=-1){
            str = str.replaceAll(":", "");
        }
        if(str.indexOf("\"")!=-1){
            str = str.replaceAll("\"", "");
        }
        if(str.indexOf("<")!=-1){
            str = str.replaceAll("<", "");
        }
        if(str.indexOf(">")!=-1){
            str = str.replaceAll(">", "");
        }
        if(str.indexOf("|")!=-1){
            str = str.replaceAll("[|]", "");
        }
        str = str.replaceAll(" ", "");
        return str;
    }
}

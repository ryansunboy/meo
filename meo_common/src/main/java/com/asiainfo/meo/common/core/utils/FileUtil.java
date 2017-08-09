package com.asiainfo.meo.common.core.utils;

import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

public class FileUtil
{
    public static final Integer LOCALE_CN=0;
    public static final Integer LOCALE_ES=1;
    public static final Integer LOCALE_TH=2;
    static Locale locale_cn = new Locale("zh","CN");
    static Locale locale_es = new Locale("en","ES");
    
   

    public static String getProperties(Integer locale,String code){
        String propertiesName="config/properties/errorMessage";
        if(locale==LOCALE_CN){
            ResourceBundle resource_cn = ResourceBundle.getBundle(propertiesName,locale_cn);
            return resource_cn.getString(code);
        }else if (locale==LOCALE_ES) {
            ResourceBundle resource_es = ResourceBundle.getBundle(propertiesName,locale_es);
            return resource_es.getString(code);
        }else {
            ResourceBundle resource_es = ResourceBundle.getBundle(propertiesName);
            return resource_es.getString(code);
        }
        


       
    }

    public static String parseErrorMessage(Map<String, Object> params, String errorCode)
    {
        String message = getProperties(null, errorCode);
        if(ValidateUtil.isNotEmpty(params)){
            for (Entry<String, Object> entry : params.entrySet()) {
                if(ValidateUtil.isNotEmpty(entry.getValue())){
                    message = message.replace("{"+entry.getKey()+"}",entry.getValue().toString());
                    System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                }
               
               }
            
        }
        return message;
    }
}

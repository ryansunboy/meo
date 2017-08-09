/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */
package com.asiainfo.meo.common.core.utils;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Company: Asiainfo Technologies(China),Inc. Hangzhou
 * @Author AI
 * @Date 2015-3-31
 */
public final class StringUtil
{
    private StringUtil()
    {
    }
    
    public static boolean isBlank(String str)
    {
        int strLen = 0;
        if ((str == null) || ((strLen = str.length()) == 0))
        {
            return true;
        }
        for (int i = 0; i < strLen; i++ )
        {
            if (!Character.isWhitespace(str.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isNotBlank(String str)
    {
        return !isBlank(str);
    }
}

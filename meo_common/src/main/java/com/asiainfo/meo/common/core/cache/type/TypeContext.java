/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */                                                                              
 package com.asiainfo.meo.common.core.cache.type;                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                              
 /**@Description: TODO(这里用一句话描述这个类的作用)                   
 * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
 * @Author AI                                                                                                                                                                                                                                                                           
 * @Date 2015-4-8                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
 */
public class TypeContext
{
    private static final ThreadLocal<Class<?>> TYPE = new ThreadLocal<Class<?>>();
    
    public static void setType(Class<?> type)
    {
         TYPE.set(type);
    }
    
    public static void remove()
    {
        TYPE.remove();
    }
    
    public static Class<?> get()
    {
        return TYPE.get();
    }
}

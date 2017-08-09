package com.asiainfo.meo.system.common.app.model.vo;

public class EnumDefine
{
    
    public static final String MNO_TYPE             = "1";
    
    public static final String CAMPAGIN_TYPE        = "5";
    
    public static final String RESOURCE_TYPE        = "6";
    
    public static final String GENDER_TYPE          = "16";
    
    public static final String PARTNER_TYPE         = "17";
    
    public static final String CHARGE_UNIT          = "19";
    
    public static final String PRODUCT_TYPE         = "20";
    
    public static final String PRODUCT_STATUS_TYPE  = "21";
    
    public static final String PRODUCT_CYCLE_TYPE   = "22";
    
    public static final String SERVICE_TYPE         = "23";
    
    public static final String DENOMINATION_UNIT    = "24";
    
    public static final String CAMPAGIN_STATUS_TYPE = "25";
    
    public static final String PRICE_TYPE           = "26";
    
    public static final String SYSTEM_TYPE          = "32";
    
    public static final String MODULE_TYPE          = "33";
    
    public static final String NOTIFACTION_TYPE     = "34";
    
    public static final String OS_TYPE              = "35";
    
    public static final String DISCOUNT_TYPE        = "36";
    
    private Long               enumId;
    
    private String             enumCode;
    
    private Integer            enumType;
    
    private String             enumName;
    
    private String             enumDipName;
    
    private Integer            operatorId;
    
    public Long getEnumId()
    {
        return enumId;
    }
    
    public void setEnumId(Long enumId)
    {
        this.enumId = enumId;
    }
    
    public String getEnumCode()
    {
        return enumCode;
    }
    
    public void setEnumCode(String enumCode)
    {
        this.enumCode = enumCode;
    }
    
    public String getEnumName()
    {
        return enumName;
    }
    
    public void setEnumName(String enumName)
    {
        this.enumName = enumName;
    }
    
    public String getEnumDipName()
    {
        return enumDipName;
    }
    
    public void setEnumDipName(String enumDipName)
    {
        this.enumDipName = enumDipName;
    }
    
    public Integer getEnumType()
    {
        return enumType;
    }
    
    public void setEnumType(Integer enumType)
    {
        this.enumType = enumType;
    }
    
    public Integer getOperatorId()
    {
        return operatorId;
    }
    
    public void setOperatorId(Integer operatorId)
    {
        this.operatorId = operatorId;
    }
    
}

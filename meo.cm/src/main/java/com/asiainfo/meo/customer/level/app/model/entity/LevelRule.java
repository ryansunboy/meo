package com.asiainfo.meo.customer.level.app.model.entity;



import java.sql.Timestamp;

import javax.validation.constraints.NotNull;
import com.asiainfo.meo.common.core.validate.group.Update;
/**
 * CmLevelRule generated by hbm2java
 */
public class LevelRule implements java.io.Serializable
{
    
    /**
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
    */ 
    private static final long serialVersionUID = 1L;
    private int       levelRuleId;
    private String    levelCode;
    private String    levelCodeDesc;
    private Integer   levelValue;
    private Integer   levelValueUnit;
    private Timestamp createDate;
    @NotNull(groups={Update.class})
    private Timestamp modifyDate;
    private Long      operaterId;
    private Integer   sts;
    
    public LevelRule()
    {
    }
    
    public LevelRule(int levelRuleId, Timestamp modifyDate)
    {
        this.levelRuleId = levelRuleId;
        this.modifyDate = modifyDate;
    }
    
    public LevelRule(int levelRuleId, String levelCode, String levelCodeDesc, Integer levelValue, Integer levelValueUnit,
            Timestamp createDate, Timestamp modifyDate, Long operaterId, Integer sts)
    {
        this.levelRuleId = levelRuleId;
        this.levelCode = levelCode;
        this.levelCodeDesc = levelCodeDesc;
        this.levelValue = levelValue;
        this.levelValueUnit = levelValueUnit;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.operaterId = operaterId;
        this.sts = sts;
    }
    
    public int getLevelRuleId()
    {
        return this.levelRuleId;
    }
    
    public void setLevelRuleId(int levelRuleId)
    {
        this.levelRuleId = levelRuleId;
    }
    
    public String getLevelCode()
    {
        return this.levelCode;
    }
    
    public void setLevelCode(String levelCode)
    {
        this.levelCode = levelCode;
    }
    
    public String getLevelCodeDesc()
    {
        return this.levelCodeDesc;
    }
    
    public void setLevelCodeDesc(String levelCodeDesc)
    {
        this.levelCodeDesc = levelCodeDesc;
    }
    
    public Integer getLevelValue()
    {
        return this.levelValue;
    }
    
    public void setLevelValue(Integer levelValue)
    {
        this.levelValue = levelValue;
    }
    
    public Integer getLevelValueUnit()
    {
        return this.levelValueUnit;
    }
    
    public void setLevelValueUnit(Integer levelValueUnit)
    {
        this.levelValueUnit = levelValueUnit;
    }
    
    public Timestamp getCreateDate()
    {
        return this.createDate;
    }
    
    public void setCreateDate(Timestamp createDate)
    {
        this.createDate = createDate;
    }
    
    public Timestamp getModifyDate()
    {
        return this.modifyDate;
    }
    
    public void setModifyDate(Timestamp modifyDate)
    {
        this.modifyDate = modifyDate;
    }
    
    public Long getOperaterId()
    {
        return this.operaterId;
    }
    
    public void setOperaterId(Long operaterId)
    {
        this.operaterId = operaterId;
    }
    
    public Integer getSts()
    {
        return this.sts;
    }
    
    public void setSts(Integer sts)
    {
        this.sts = sts;
    }
    
}

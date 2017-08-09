package com.asiainfo.meo.web.notification.model.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

public class UITimeSegDefVO implements Serializable
{

    /**                                                                           
      * @Fields serialVersionUID : (用一句话描述这个变量表示什么)                                                                                                                                                                                                                                  
      */
    private static final long serialVersionUID = 1265025448141912833L;

    @Valid
    @NotNull(groups={Insert.class, Update.class})
    private Integer timeMode;
    
    @Valid
    @NotEmpty(groups={Insert.class, Update.class})
    private String segName;
    
    private String description;
    
    private List<UITimeSegDtlVO> segmentDtlList;
    
    @Valid
    @NotNull(groups={Update.class})
    private Long segId;
    
    private String timeModeName;
    
    

    public Integer getTimeMode()
    {
        return timeMode;
    }

    public void setTimeMode(Integer timeMode)
    {
        this.timeMode = timeMode;
    }

    public String getSegName()
    {
        return segName;
    }

    public void setSegName(String segName)
    {
        this.segName = segName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<UITimeSegDtlVO> getSegmentDtlList()
    {
        return segmentDtlList;
    }

    public void setSegmentDtlList(List<UITimeSegDtlVO> segmentDtlList)
    {
        this.segmentDtlList = segmentDtlList;
    }

    public Long getSegId()
    {
        return segId;
    }

    public void setSegId(Long segId)
    {
        this.segId = segId;
    }

    public String getTimeModeName()
    {
        return timeModeName;
    }

    public void setTimeModeName(String timeModeName)
    {
        this.timeModeName = timeModeName;
    }
}

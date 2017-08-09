package com.asiainfo.meo.web.notification.model.vo;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.asiainfo.meo.common.core.validate.group.Insert;
import com.asiainfo.meo.common.core.validate.group.Update;

public class UITimeSegDtlVO implements Serializable
{

    /**                                                                           
      * @Fields serialVersionUID : (用一句话描述这个变量表示什么)                                                                                                                                                                                                                                  
      */
    private static final long serialVersionUID = 4257983740058198516L;
    
    @Valid
    @NotNull(groups={Update.class})
    private Long dtlId;

    private UITimeVO startVal;
    
    private UITimeVO endVal;

    public Long getDtlId()
    {
        return dtlId;
    }

    public void setDtlId(Long dtlId)
    {
        this.dtlId = dtlId;
    }

    public UITimeVO getStartVal()
    {
        return startVal;
    }

    public void setStartVal(UITimeVO startVal)
    {
        this.startVal = startVal;
    }

    public UITimeVO getEndVal()
    {
        return endVal;
    }

    public void setEndVal(UITimeVO endVal)
    {
        this.endVal = endVal;
    }

    
}

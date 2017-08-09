package com.asiainfo.meo.web.partner.model.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


public class UIContractInfoVO
{
    @NotNull
    private UIContractProfileVO contractInfo;
    
    @NotNull
    @Valid
    private List<UIPriceInfoVO> contractPrices = new ArrayList<UIPriceInfoVO>();

    public UIContractProfileVO getContractInfo()
    {
        return contractInfo;
    }

    public void setContractInfo(UIContractProfileVO contractInfo)
    {
        this.contractInfo = contractInfo;
    }

    public List<UIPriceInfoVO> getContractPrices()
    {
        return contractPrices;
    }

    public void setContractPrices(List<UIPriceInfoVO> contractPrices)
    {
        this.contractPrices = contractPrices;
    }

}

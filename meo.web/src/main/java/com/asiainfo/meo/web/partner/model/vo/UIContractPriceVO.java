package com.asiainfo.meo.web.partner.model.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


public class UIContractPriceVO
{
    @NotNull
    private UIContractVO contract;
    
    @NotNull
    @Valid
    private List<UIPriceVO> contractPrices = new ArrayList<UIPriceVO>();

    public UIContractVO getContract()
    {
        return contract;
    }

    public void setContract(UIContractVO contract)
    {
        this.contract = contract;
    }

    public List<UIPriceVO> getContractPrices()
    {
        return contractPrices;
    }

    public void setContractPrices(List<UIPriceVO> contractPrices)
    {
        this.contractPrices = contractPrices;
    }

}

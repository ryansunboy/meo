package com.asiainfo.meo.prm.contract.app.model.vo;

import java.util.ArrayList;
import java.util.List;

import com.asiainfo.meo.prm.contract.app.model.entity.Contract;
import com.asiainfo.meo.prm.contract.app.model.entity.Price;
/**
  * @Description:  合同和合同价格VO
  * @Description: contract and contract price VO                 
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author zhaozx                                                                                                                                                                                                                                                                           
  * @Date 2015-3-16 上午11:37:38 
  * @version 1.0                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
  */
public class ContractPriceVO
{
    /**
     * @Fields contract : contract price
     */
    private Contract            contract;
    
    /**
     * @Fields contractPricelList : contract price relation list
     */
    
    private List<Price> prices = new ArrayList<Price>();

    public Contract getContract()
    {
        return contract;
    }

    public void setContract(Contract contract)
    {
        this.contract = contract;
    }

    public List<Price> getPrices()
    {
        return prices;
    }

    public void setPrices(List<Price> prices)
    {
        this.prices = prices;
    }
    
}

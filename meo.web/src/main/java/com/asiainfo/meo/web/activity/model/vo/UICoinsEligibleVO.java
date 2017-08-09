package com.asiainfo.meo.web.activity.model.vo;
/**
 * 
  * @Description: Contain a result of coins eligibility when called joined campaign API              
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author Thanapol                                                                                                                                                                                                                                                                           
  * @Date Aug 5, 2015 9:57:29 AM 
  * @version 1.0
 */
public class UICoinsEligibleVO
{
    private Boolean coinsEligible;
    private String coinsEligibleDescription;

    public Boolean getCoinsEligible()
    {
        return coinsEligible;
    }

    public void setCoinsEligible(Boolean coinsEligible)
    {
        this.coinsEligible = coinsEligible;
    }

    public String getCoinsEligibleDescription()
    {
        return coinsEligibleDescription;
    }

    public void setCoinsEligibleDescription(String coinsEligibleDescription)
    {
        this.coinsEligibleDescription = coinsEligibleDescription;
    }
}

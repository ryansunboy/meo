package com.asiainfo.meo.prm.contract.app.constant;

public final class PartnerContractSQLConstant
{
    public final static String GET_CONTRACT_BY_CONTRACTID_PRICEID = "SELECT ID,PRICE_ID,CONTRACT_ID,VALID_DATE,EXPIRED_DATE FROM CM_CONTRACT_PRICE_REL WHERE  EXPIRED_DATE > CURRENT_TIMESTAMP AND CONTRACT_ID = ? AND PRICE_ID = ? ";
}

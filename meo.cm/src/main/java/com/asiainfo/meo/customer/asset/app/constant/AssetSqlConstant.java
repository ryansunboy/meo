package com.asiainfo.meo.customer.asset.app.constant;

public class AssetSqlConstant
{
  public static final String QUERY_ASSET_BY_CUSTID =
     // "getAsset";
     "SELECT assetId, assetType, amount, createDate, modifyDate, custId, unit FROM Asset WHERE CUST_ID=? AND ASSET_TYPE=? ";

}

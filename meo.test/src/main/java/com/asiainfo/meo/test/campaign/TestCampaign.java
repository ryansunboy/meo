package com.asiainfo.meo.test.campaign;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.SignUtil;
import com.asiainfo.meo.test.utils.HttpClientUtil;
import com.asiainfo.meo.test.utils.JsonUtil;
import com.asiainfo.meo.test.utils.TestUtil;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignBasicVO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignPriceVO;
import com.asiainfo.meo.web.campaign.model.vo.UIReNewCampaignCommonVO;
import com.asiainfo.meo.web.core.Message;
import com.asiainfo.meo.web.partner.model.vo.UIContractQueryVO;

public class TestCampaign
{
    private static final String CAMPAIGN_URL  = "https://localhost:8443/meo/rest/campaign/partner";
    
    private static final String CAMPAIGN_URL2 = "http://localhost:8080/meo/rest/campaign/partner";
    
    private static final Log    LOG           = LogFactory.getLog(TestCampaign.class);
    
    /********************************************************/
    
    @Test
    public void getHotCampaignPOST() throws Exception
    {
        String queryString = CAMPAIGN_URL+ "?method=getHotCampaign&timestamp="+ DateTimeUtil.getCurrentTimeMillis()
                + "&format=json&v=1&accessToken=0c1ae17001d14bc98eca445340d571d7&categoryId=1&pageSize=10&pageNo=1";
        LOG.debug(queryString);
        String response = HttpClientUtil.post(queryString, "");
        LOG.debug("response:"+ response);
        Message<?> message = JsonUtil.fromJson(response, Message.class);
        LOG.debug("message body:"+ message.getBody());
    }
    
    @Test
    public void getHotCampaignGET() throws Exception
    {
        String queryString = "method=getHotCampaign&timestamp="+ DateTimeUtil.getCurrentTimeMillis()
                + "&format=json&v=1&accessToken=3d43a665a5cd4a2f87c4ed4d8795f6f4&categoryId=1&pageSize=10&pageNo=1";
        String signature = SignUtil.md5Signature(TestUtil.stringToMap(queryString), "aqg6t1dq", null);
        String url = CAMPAIGN_URL2+ "?"+ queryString+ "&signature="+ signature;
        LOG.debug(url);
        String response = HttpClientUtil.get(url);
        LOG.debug("response:"+ response);
        Message<?> message = JsonUtil.fromJson(response, Message.class);
        LOG.debug("message body:"+ message.getBody());
    }
    
    /********************************************************/
    @Test
    public void getNewCampaignGET() throws Exception
    {
        String queryString = "method=getNewCampaign&timestamp="+ DateTimeUtil.getCurrentTimeMillis()
                + "&format=json&v=1&accessToken=3d43a665a5cd4a2f87c4ed4d8795f6f4&categoryId=1&pageSize=10&pageNo=1";
        String signature = SignUtil.md5Signature(TestUtil.stringToMap(queryString), "aqg6t1dq", null);
        String url = CAMPAIGN_URL2+ "?"+ queryString+ "&signature="+ signature;
        LOG.debug(url);
        String response = HttpClientUtil.get(url);
        LOG.debug("response:"+ response);
        Message<?> message = JsonUtil.fromJson(response, Message.class);
        LOG.debug("message body:"+ message.getBody());
    }
    
    @Test
    public void getNewCampaignPOST() throws Exception
    {
        String queryString = CAMPAIGN_URL+ "?method=getNewCampaign&timestamp="+ DateTimeUtil.getCurrentTimeMillis()
                + "&format=json&v=1&accessToken=3d43a665a5cd4a2f87c4ed4d8795f6f4&categoryId=1&pageSize=10&pageNo=1";
        LOG.debug(queryString);
        String response = HttpClientUtil.post(queryString, "");
        LOG.debug("response:"+ response);
        Message<?> message = JsonUtil.fromJson(response, Message.class);
        LOG.debug("message body:"+ message.getBody());
    }
    
    /********************************************************/
    
    @Test
    public void getActivityHistoryPOST() throws Exception
    {
        String queryString = CAMPAIGN_URL+ "?method=getActivityHistory&timestamp="+ DateTimeUtil.getCurrentTimeMillis()
                + "&format=json&v=1&accessToken=2d173c164bb940b595ca0055733e6961&pageSize=10&pageNo=1";
        LOG.debug(queryString);
        String response = HttpClientUtil.post(queryString, "");
        LOG.debug("response:"+ response);
        Message<?> message = JsonUtil.fromJson(response, Message.class);
        LOG.debug("message body:"+ message.getBody());
    }
    
    @Test
    public void getActivityHistoryGET() throws Exception
    {
        String queryString = "method=getActivityHistory&timestamp="+ DateTimeUtil.getCurrentTimeMillis()
                + "&format=json&v=1&accessToken=ebc9a30badf54858bbbb25db6cef55c4&pageSize=10&pageNo=1";
        String signature = SignUtil.md5Signature(TestUtil.stringToMap(queryString), "aisqzy28", null);
        String url = CAMPAIGN_URL2+ "?"+ queryString+ "&signature="+ signature;
        LOG.debug(url);
        String response = HttpClientUtil.get(url);
        LOG.debug("response:"+ response);
        Message<?> message = JsonUtil.fromJson(response, Message.class);
        LOG.debug("message body:"+ message.getBody());
    }
    
    /********************************************************/
    public UIContractQueryVO createUIContractQueryVO()
    {
        UIContractQueryVO iIContractQueryVO = new UIContractQueryVO();
        return iIContractQueryVO;
    }
    
    public static void main(String args[])
    {
        // LOG.debug(ValidateUtil.isEmpty(""));
        System.out.println(DateTimeUtil.getCurrentTimeMillis());
        
        // System.out.println(DateTimeUtil.getDateTime("2015-04-09 14:36:07").getTime());
        // System.out.println(DateTimeUtil.getDateTime("2015-05-08 14:36:29").getTime());
        // System.out.println(new Date("2015-05-08").getTime());
        UICampaignBasicVO vo = new UICampaignBasicVO();
        vo.setPartnerId(100011001L);
        vo.setCampaignType(1);
        vo.setCampaignNo("10000");
        vo.setCampaignName("t1");
        vo.setValidDate(1431066989000L);
        vo.setExpiredDate(1451025371000L);
        
        String bodyString = com.asiainfo.meo.common.core.utils.JsonUtil.writeObjectAsString(vo);
        System.out.println(bodyString);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 小写的mm表示的是分钟
        java.util.Date date = null;
        java.util.Date date2 = null;
        try
        {
            date = sdf.parse("2015-12-27 14:36:11");
            date2 = sdf.parse("2015-05-08 14:36:29");
        }
        catch(ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(date.getTime());
        System.out.println(date2.getTime());
        
    }
    
    
    @Test
    public void testRenewPublishCampaign(){
        String queryString = CAMPAIGN_URL+ "?method=meo.campaign.partner.publish.renew&timestamp="+ DateTimeUtil.getCurrentTimeMillis()
                + "&format=json&v=1&accessToken=3b75bba7cb8145a790eedde3eb9bc400";
        System.out.println(queryString);
        UIReNewCampaignCommonVO renewCampaignCommon = new UIReNewCampaignCommonVO();
        renewCampaignCommon.setCampaignId(1001001001L);
        renewCampaignCommon.setEndDate(DateTimeUtil.getNow().getTime());
        renewCampaignCommon.setParticipant(1111);
        renewCampaignCommon.setValidDate(DateTimeUtil.getNow().getTime());
        
        UICampaignPriceVO priceVO = new UICampaignPriceVO();
        priceVO.setChargeUnit(1);
        priceVO.setPriceId(11L);
        priceVO.setPriceType(1);
        priceVO.setUnitPrice(1L);
        
        List<UICampaignPriceVO> pricesVO = new ArrayList<UICampaignPriceVO>();
        pricesVO.add(priceVO);
        renewCampaignCommon.setCampaignPrices(pricesVO);
        
        String bodyString = com.asiainfo.meo.common.core.utils.JsonUtil.writeObjectAsString(renewCampaignCommon);
        System.out.println(bodyString);
    }
}

package com.asiainfo.meo.test.campaign;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.JsonUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.common.core.utils.SignUtil;
import com.asiainfo.meo.customer.device.app.bo.DeviceInfoBO;
import com.asiainfo.meo.system.common.app.model.entity.SysEnumGroupDef;
import com.asiainfo.meo.system.service.provide.SystemPserviceBO;
import com.asiainfo.meo.web.campaign.model.vo.UICampaignPropertyDefineVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIMenuPrivilegeVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIMenuVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIOperPrivilegeVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIOperaterVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIPrivilegeVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UIRolePrivilegeVO;
import com.asiainfo.meo.web.passport.privilege.model.vo.UISysRoleVO;

public class CreateURL
{
    private static final String HttpURLS    = "https://localhost:8443/meo/rest/system/template/parameter"; // 119.46.160.70
    
    private static final String HttpURL     = "http://localhost:8080/meo/rest/product";
    
    private String              secretKey   = "";
    
    private String              accessToken = "3eae6f319ad94f31a3a885ab4da38d80"; //be9ee09b8af34493974b165d5e410bd8 //8742dfc313cd4d448404b1602a0a8090
    
    private String              methodName  = "meo.parameter.template.get";
    
    @Test
    public void testHTTPS() throws Exception
    {
        long currentTime = DateTimeUtil.getCurrentTimeMillis();
        String queryString = "method="+ methodName+ "&accessToken="+ accessToken+ "&timestamp="+ "${=new Date().getTime()}"+ "&format=json&v=1";
        System.out.println(HttpURLS+ "?"+ queryString);
    }
    
    @Test
    public void testHTTP() throws Exception
    {
        long currentTime = DateTimeUtil.getCurrentTimeMillis();
        String queryString = "method="+ methodName+ "&accessToken="+ accessToken+ "&timestamp="+ currentTime+ "&format=json&v=1";
        String signature = SignUtil.md5Signature(stringToMap(queryString), secretKey, null);
        String url = HttpURL+ "?"+ queryString+ "&signature="+ signature; // http
        System.out.println(url);
    }
    
    private TreeMap<String, String> stringToMap(String queryString)
    {
        TreeMap<String, String> params = new TreeMap<String, String>();
        String[] keyValues = queryString.split("&");
        for (String keyValue : keyValues)
        {
            String[] kv = keyValue.split("=");
            params.put(kv[0], kv[1]);
        }
        return params;
    }
    
    @Test
    public void createTime() throws Exception
    {
        // util.Date ---> String
        java.util.Date dd = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sdate = sdf.format(dd);
        System.out.println("dd : "+ dd.getTime()+ " sdate : "+ sdate);
        
        // String ---> util.Date
        java.util.Date d = DateFormat.getDateTimeInstance().parse("2015-09-20 11:59:44");
        System.out.println("-----------" + d.getTime());
        
        //long ---> string
        java.util.Date dd2 = new java.util.Date(1439964951615L);
        System.out.println(" sdate : "+ sdf.format(dd2));
    }
    
    @Test
    public void texust()
    {
        
        SystemPserviceBO systemPserviceBO = ServiceLocatorFactory.getService(SystemPserviceBO.class);
        List<SysEnumGroupDef> list = systemPserviceBO.getSysEnumGroupRelListByEnumId(15L);
        System.out.println(JsonUtil.writeObjectAsString(list));
        
    }
    
    @Test
    public void createObject()
    {
        UIRolePrivilegeVO uiRolePrivilegeVO = new UIRolePrivilegeVO();
        UISysRoleVO role = new UISysRoleVO();
        role.setRoleId(1L);
        
        List<UIMenuPrivilegeVO> menuPriveliges = new ArrayList<UIMenuPrivilegeVO>();
        // /-------------------------------1111111111---------------------------------------
        UIMenuPrivilegeVO uiMenuPrivilegeVO = new UIMenuPrivilegeVO();
        UIMenuVO menu = new UIMenuVO();
        menu.setMenuId(1L);
        uiMenuPrivilegeVO.setMenu(menu);
        
        List<UIOperPrivilegeVO> operPrivileges = new ArrayList<UIOperPrivilegeVO>();
        UIOperPrivilegeVO uiOperPrivilegeVO = new UIOperPrivilegeVO();
        UIPrivilegeVO privilege = new UIPrivilegeVO();
        privilege.setPrivilegeCode("1");
        privilege.setPrivilegeName("privilegeName");
        uiOperPrivilegeVO.setPrivilege(privilege);
        UIOperaterVO operater = new UIOperaterVO();
        operater.setOperaterId(1L);
        uiOperPrivilegeVO.setOperater(operater);
        operPrivileges.add(uiOperPrivilegeVO);
        
        UIOperPrivilegeVO uiOperPrivilegeVO2 = new UIOperPrivilegeVO();
        UIPrivilegeVO privilege2 = new UIPrivilegeVO();
        privilege2.setPrivilegeCode("2");
        privilege2.setPrivilegeName("privilegeName2");
        uiOperPrivilegeVO2.setPrivilege(privilege2);
        UIOperaterVO operater2 = new UIOperaterVO();
        operater2.setOperaterId(2L);
        uiOperPrivilegeVO2.setOperater(operater2);
        operPrivileges.add(uiOperPrivilegeVO2);
        
        uiMenuPrivilegeVO.setOperPrivileges(operPrivileges);
        // /-------------------------------11111111111---------------------------------------
        UIMenuPrivilegeVO uiMenuPrivilegeVO2 = new UIMenuPrivilegeVO();
        UIMenuVO menu2 = new UIMenuVO();
        menu2.setMenuId(2L);
        uiMenuPrivilegeVO2.setMenu(menu2);
        
        List<UIOperPrivilegeVO> operPrivileges2 = new ArrayList<UIOperPrivilegeVO>();
        UIOperPrivilegeVO uiOperPrivilegeVO3 = new UIOperPrivilegeVO();
        UIPrivilegeVO privilege3 = new UIPrivilegeVO();
        privilege3.setPrivilegeCode("3");
        privilege3.setPrivilegeName("privilegeName3");
        uiOperPrivilegeVO3.setPrivilege(privilege3);
        UIOperaterVO operater3 = new UIOperaterVO();
        operater3.setOperaterId(3L);
        uiOperPrivilegeVO3.setOperater(operater3);
        operPrivileges2.add(uiOperPrivilegeVO3);
        
        UIOperPrivilegeVO uiOperPrivilegeVO4 = new UIOperPrivilegeVO();
        UIPrivilegeVO privilege4 = new UIPrivilegeVO();
        privilege4.setPrivilegeCode("4");
        privilege4.setPrivilegeName("privilegeName4");
        uiOperPrivilegeVO4.setPrivilege(privilege4);
        UIOperaterVO operater4 = new UIOperaterVO();
        operater4.setOperaterId(4L);
        uiOperPrivilegeVO4.setOperater(operater4);
        operPrivileges2.add(uiOperPrivilegeVO4);
        
        uiMenuPrivilegeVO2.setOperPrivileges(operPrivileges2);
        // /---------------------------------222222222---------------------------------------------
        
        menuPriveliges.add(uiMenuPrivilegeVO);
        menuPriveliges.add(uiMenuPrivilegeVO2);
        uiRolePrivilegeVO.setRole(role);
        uiRolePrivilegeVO.setMenuPrivileges(menuPriveliges);
        System.out.println(JsonUtil.writeObjectAsString(uiRolePrivilegeVO));
        
    }
    
    @Test
    public void testfor()
    {
//        CampaignRepository campaignRepository = ServiceLocatorFactory.getService(CampaignRepository.class);
//        campaignRepository.getAppDownLoadTotalSize(null);
////        System.out.println(JsonUtil.writeObjectAsString(list));
        System.out.println(System.currentTimeMillis());
    }
    
    private List<Menu> createMenuTree(List<Menu> menuList) {
        List<Menu> nodeList = new ArrayList<Menu>();
        for (Menu menu1 : menuList) {
            boolean mark = false;
            for (Menu menu2 : menuList) {
                if (menu1.getParentId() == menu2.getId()) {
                    mark = true;
                    if (menu2.getChildren() == null) {
                        menu2.setChildren(new ArrayList<Menu>());
                    }
                    menu2.getChildren().add(menu1);
                    break;
                }
            }
            if (!mark) {
                nodeList.add(menu1);
            }
        }
        return nodeList;
    }
    
    @Test
    public void equals()
    {
        List<UICampaignPropertyDefineVO> list = new ArrayList<UICampaignPropertyDefineVO>();
        UICampaignPropertyDefineVO campaignPropertyDefine = new UICampaignPropertyDefineVO();
        campaignPropertyDefine.setCampaignTypeId(1);
        campaignPropertyDefine.setMandatory(1);
        campaignPropertyDefine.setPropertyCode("1");
        campaignPropertyDefine.setPropertyName("1");
        campaignPropertyDefine.setPropertyValueType(1);
        UICampaignPropertyDefineVO campaignPropertyDefine1 = new UICampaignPropertyDefineVO();
        campaignPropertyDefine1.setCampaignTypeId(2);
        campaignPropertyDefine1.setMandatory(2);
        campaignPropertyDefine1.setPropertyCode("2");
        campaignPropertyDefine1.setPropertyName("2");
        campaignPropertyDefine1.setPropertyValueType(2);
        list.add(campaignPropertyDefine);
        list.add(campaignPropertyDefine1);
        System.out.println(JsonUtil.writeObjectAsString(list));
    }
    
    
    @Test
    public void testPatt5ern()
    {  
        Boolean flag = false;
        List<Integer> dbProdPriceIdlist = new ArrayList<Integer>();
        List<Integer> inputProdPriceIdlist = new ArrayList<Integer>();
        dbProdPriceIdlist.add(3);
        dbProdPriceIdlist.add(2);
        inputProdPriceIdlist.add(2);
        inputProdPriceIdlist.add(3);
        flag = dbProdPriceIdlist.containsAll(inputProdPriceIdlist);
        System.out.println(flag + "\n");
    }
    
    @Test
    public void query(){
        String exp = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";
        Pattern p = Pattern.compile(exp);
        Matcher m = p.matcher("zhoujj3kay00");
        boolean flg = m.matches();
        System.out.println(flg);
    }
    
    
    
}

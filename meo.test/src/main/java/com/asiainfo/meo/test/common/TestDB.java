/**                                                                  
  *                                                                                                                                                                                                                                                                                           
  */                                                                              
 package com.asiainfo.meo.test.common;                                                                                                                                                                                                                                                                       

import java.text.ParseException;

import org.junit.Test;

import com.asiainfo.meo.common.core.utils.DateTimeUtil;
import com.asiainfo.meo.common.core.utils.ServiceLocatorFactory;
import com.asiainfo.meo.user.app.bo.UserBO;
                                                                                                                                                                                                                                                                                              
 /**@Description: TODO(������һ�仰��������������)                   
 * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
 * @Author AI                                                                                                                                                                                                                                                                           
 * @Date 2015-1-28                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
 */
public class TestDB {
	
	@Test
	public void testDB() throws ParseException
	{
		UserBO userBo = ServiceLocatorFactory.getService(UserBO.class);
		userBo.getUserInfo(4L);
//		for(int i = 0; i < 1 ; i++)
//		{
//		    userBo.getUserInfo(4L);
//		}
		
//		ClassPathResource resource = new ClassPathResource(path)
//		HibernateRepository repo = context.getBean(HibernateRepository.class);
//		Object[] vars = repo.executeProcedure("{call SEQ_NEXTVAL(?,?)}", new Object[]{"SEQ_CUSTOMER_ID"}, new int[]{java.sql.Types.BIGINT});
//		Object vars = repo.findUniqueMapByNativeSql("SELECT SEQ_NEXTVAL(?) as seq", "SEQ_CUSTOMER_ID");
//		System.out.println(vars);
		//		Map<String,Object> in = new HashMap<String,Object>();
//		in.put("IN_SEQUENCE_NAME", "qq");
//	
//		Map<String,Class<?>> out = new HashMap<String,Class<?>>();
//		out.put("OUT_CURRENT_VAL", Long.class);
//		out.put("OUT_NEXT_VAL", Long.class);
//		
//		Object vars = repo.executeProcedure("P_SEQ_NEXT", in, out);
	    System.out.println(Long.MAX_VALUE);
	    long start = System.currentTimeMillis();
	    for(int i = 0; i < 1; i++)
	    {
	        System.out.println(DateTimeUtil.getDateTime("19901110111111"));;
	    }
	    long end = System.currentTimeMillis();
	    System.out.println(end-start);
	}
}

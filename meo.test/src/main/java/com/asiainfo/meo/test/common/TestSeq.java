package com.asiainfo.meo.test.common;

import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestSeq
{
    private static final Log LOG = LogFactory.getLog(TestSeq.class);
    /**
     * AI 2015-2-2
     * @param args
     */
    public static void main(String[] args)
    {
//        ExecutorService executor = Executors.newFixedThreadPool(10);
//        for(int i = 0; i < 100; i++)
//        {
//            executor.execute(new Runnable()
//            {
//                
//                public void run()
//                {
//                    Sequence s = ServiceLocatorFactory.getInstance().getService(Sequence.class);
//                    LOG.debug(s.next("SEQ_CUSTOMER_ID"));
//                    
//                }
//            });
//        }
//        executor.shutdown();
//        UserBO userBo = ServiceLocatorFactory.getInstance().getService(UserBO.class);
//        userBo.getUserInfo(2582);
        System.out.println(Long.toHexString(4078358726903648L));
        System.out.println(Locale.getDefault());
    }
   
}

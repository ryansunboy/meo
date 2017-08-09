package com.asiainfo.meo.meo.notification.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.asiainfo.meo.common.core.utils.ValidateUtil;

/**
 * 
  * @Description: Utils for Time mode               
  * @Company: Asiainfo Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @author Thanapol                                                                                                                                                                                                                                                                           
  * @Date Sep 14, 2015 12:22:26 PM 
  * @version 1.0
 */
public class TimeModeUtils
{
    private static final String HHMMSS = "^((0?[1-9]|1[012])([0-5]\\d){0,2}(\\ [AP]M))$|^([01]\\d|2[0-3])([0-5]\\d){0,2}$";
    private static final String YYYYMMDD = "^(((\\d{4})(0[13578]|10|12)(0[1-9]|[12][0-9]|3[01]))|((\\d{4})(0[469]|11)([0][1-9]|[12][0-9]|30))|((\\d{4})(02)(0[1-9]|1[0-9]|2[0-8]))|(([02468][048]00)(02)(29))|(([13579][26]00) (02)(29))|(([0-9][0-9][0][48])(02)(29))|(([0-9][0-9][2468][048])(02)(29))|(([0-9][0-9][13579][26])(02)(29))|(00000000)|(88888888)|(99999999))?$";
    private static final String MMDD = "^(0?[1-9]|1[0-2])(0?[1-9]|[12][0-9]|3[01])$";
    private static final String DD = "^(0?[1-9]|[12][0-9]|3[01])$";
    
    public static boolean validateTimeMode(final int timeMode, final Long val) {
        switch (timeMode) {
            case 1:
            case 5:    
                /** DHHMMSS */
                return validateTimeModeType1(val);
            case 2:
            case 6:
                /** YYYYMMDDHHMMSS */
                return validateTimeModeType2(val);
            case 3:
            case 7:
                /** MMDDHHMMSS */
                return validateTimeModeType3(val);
            case 4:
            case 8:
                /** DDHHMMSS */
                return validateTimeModeType4(val);
            default:
                return false;
        }
    }
    
    /**
     * 
      * @Description: validate time mode type 1 (DHHMMSS)
      * @modifyReason: 
      * @author Thanapol
      * @param val
      * @return
     */
    private static boolean validateTimeModeType1(final Long val) {
        if (ValidateUtil.isNull(val) || ValidateUtil.isEmpty(val)) {
            return false;
        }
        if (val.toString().trim().length() != 7) {
            return false;
        }
        final String valStr = val.toString().trim();
        /** validate date should be 1 - 7 */
        boolean validateDate = false;
        try {
            validateDate = Integer.parseInt(valStr.substring(0, 1)) > 0 &&
                    Integer.parseInt(valStr.substring(0, 1)) < 8;
        } catch(NumberFormatException e) {
            return false;
        }
        /** validate HHMMSS */
        return validateDate && validateRegEx(HHMMSS, valStr.substring(1));
        
    }
    
    /**
     * 
      * @Description: Validate time mode type 2 (YYYYMMDDHHMMSS)
      * @modifyReason: 
      * @author Thanapol
      * @param val
      * @return
     */
    private static boolean validateTimeModeType2(final Long val) {
        if (ValidateUtil.isNull(val) || ValidateUtil.isEmpty(val)) {
            return false;
        }
        if (val.toString().trim().length() != 14) {
            return false;
        }
        final String valStr = val.toString().trim();
        final String yyyyMMdd = valStr.substring(0, 8);
        final String hhmmss = valStr.substring(8);
        return validateRegEx(YYYYMMDD, yyyyMMdd) &&
                validateRegEx(HHMMSS, hhmmss);
    }
    
    /**
     * 
      * @Description: validate time mode type 3 (MMDDHHMMSS)
      * @modifyReason: 
      * @author Thanapol
      * @param val
      * @return
     */
    private static boolean validateTimeModeType3(final Long val) {
        if (ValidateUtil.isNull(val) || ValidateUtil.isEmpty(val)) {
            return false;
        }
        if (val.toString().trim().length() != 10) {
            return false;
        }
        final String valStr = val.toString().trim();
        final String mmdd = valStr.substring(0, 4);
        final String hhmmss = valStr.substring(4);
        return validateRegEx(MMDD, mmdd) &&
                validateRegEx(HHMMSS, hhmmss);
    }
    
    /**
     * 
      * @Description: validate time mode type 4 (DDHHMMSS)
      * @modifyReason: 
      * @author Thanapol
      * @param val
      * @return
     */
    private static boolean validateTimeModeType4(final Long val) {
        if (ValidateUtil.isNull(val) || ValidateUtil.isEmpty(val)) {
            return false;
        }
        if (val.toString().trim().length() != 8) {
            return false;
        }
        final String valStr = val.toString().trim();
        final String dd = valStr.substring(0, 2);
        final String hhmmss = valStr.substring(2);
        return validateRegEx(DD, dd) && validateRegEx(HHMMSS, hhmmss);
    }
    
    private static boolean validateRegEx(final String regex, final String val) {
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(val);
        return matcher.matches();
    }
}

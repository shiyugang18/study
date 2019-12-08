package com.syg.utils;

import com.syg.exception.BusinessException;
import java.math.BigDecimal;


public class BigDecimalUtil {

    /**
     * 比较大小
     * @param decimal
     * @param decimal2
     * @return
     */
    public static boolean greater(BigDecimal decimal,BigDecimal decimal2){
        if (decimal == null){
            decimal = BigDecimal.ZERO;
        }
        if (decimal2 == null){
            decimal2 = BigDecimal.ZERO;
        }
        return decimal.compareTo(decimal2) > 0;
    }

    /**
     * 减法
     * @param decimal
     * @param decimal2
     * @return
     */
    public static boolean less(BigDecimal decimal,BigDecimal decimal2){
        if (decimal == null){
            decimal = BigDecimal.ZERO;
        }
        if (decimal2 == null){
            decimal2 = BigDecimal.ZERO;
        }
        return decimal.compareTo(decimal2) < 0;
    }

    /**
     *  等于
     * @param decimal
     * @param decimal2
     * @return
     */
    public static boolean equal(BigDecimal decimal,BigDecimal decimal2){
        if (decimal == null){
            decimal = BigDecimal.ZERO;
        }
        if (decimal2 == null){
            decimal2 = BigDecimal.ZERO;
        }
        return decimal.compareTo(decimal2) == 0;
    }

    /**
     * 减法
     * @param decimal
     * @param decimal2
     * @return
     */
    public static BigDecimal sub(BigDecimal decimal,BigDecimal decimal2){
        if (decimal == null){
            decimal = BigDecimal.ZERO;
        }
        if (decimal2 == null){
            decimal2 = BigDecimal.ZERO;
        }
        return decimal.subtract(decimal2);
    }

    /**
     * 加法
     * @param decimal
     * @param decimal2
     * @return
     */
    public static BigDecimal add(BigDecimal decimal,BigDecimal decimal2){
        if (decimal == null){
            decimal = BigDecimal.ZERO;
        }
        if (decimal2 == null){
            decimal2 = BigDecimal.ZERO;
        }
        return decimal.add(decimal2);
    }

    /**
     * 除法保留2位小数
     * @param decimal
     * @param decimal2
     * @return
     */
    public static BigDecimal divide(BigDecimal decimal,BigDecimal decimal2){
        if (decimal == null){
            decimal = BigDecimal.ZERO;
        }
        if (decimal2 == null || equal(BigDecimal.ZERO,decimal2)){
            throw new BusinessException("除法操作异常,除数不能为0");
        }
        return decimal.divide(decimal2,2, BigDecimal.ROUND_HALF_EVEN);
    }


    /**
     * 乘法
     * @param decimal
     * @param decimal2
     * @return
     */
    public static BigDecimal multiply(BigDecimal decimal,BigDecimal decimal2){
        if (decimal == null){
            decimal = BigDecimal.ZERO;
        }
        if (decimal2 == null){
            decimal2 = BigDecimal.ZERO;
        }
        return decimal.multiply(decimal2);
    }
}

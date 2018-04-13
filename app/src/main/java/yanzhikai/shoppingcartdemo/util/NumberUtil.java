package yanzhikai.shoppingcartdemo.util;

import java.text.DecimalFormat;

/**
 * author : yany
 * e-mail : yanzhikai_yjk@qq.com
 * time   : 2018/04/10
 * desc   : 数字转化工具
 */

public class NumberUtil {
    /**
     * 把float类型，四舍五入后，转化为小数点后一位的String类型
     * @param number 传入数
     * @return 返回String值
     */
    public static String floatToStringWith1Bit(float number) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.0");
        return decimalFormat.format(number);
    }

}

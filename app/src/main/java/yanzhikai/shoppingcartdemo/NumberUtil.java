package yanzhikai.shoppingcartdemo;

import java.text.DecimalFormat;

/**
 * author : yany
 * e-mail : yanzhikai_yjk@qq.com
 * time   : 2018/04/10
 * desc   :
 */

public class NumberUtil {
    public static String floatToStringWith1Bit(float number) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.0");
        return decimalFormat.format(number);
    }

}

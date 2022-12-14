package top.jionjion.validation.jsr303.number;

import org.junit.jupiter.api.Test;
import top.jionjion.validation.ValidationBaseTest;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Jion
 */
class DigitsValidationTest extends ValidationBaseTest {

    @Test
    void test(){
        DigitsValidation obj = new DigitsValidation();
        obj.bigDecimalDigits = BigDecimal.valueOf(123456.789);
        obj.bigIntegerDigits = BigInteger.valueOf(123456789);
        obj.charSequenceDigits = "0.9999";
        obj.byteDigits = (byte) 123;  // 防止数字溢出
        obj.shortDigits = (short) 12345;
        obj.intDigits = 1234567890;
        obj.longDigits = 1234567890L;
        super.validate(obj);
    }
}
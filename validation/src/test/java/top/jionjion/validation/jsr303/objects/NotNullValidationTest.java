package top.jionjion.validation.jsr303.objects;

import org.junit.jupiter.api.Test;
import top.jionjion.validation.ValidationBaseTest;

/**
 * 通用对象校验
 * .@NotNull 必须不为 null
 *
 * @author jion
 */
class NotNullValidationTest extends ValidationBaseTest {

    @Test
    void mustNotNull1() {
        NotNullValidation obj = new NotNullValidation();
        obj.mustNotNull = null;
        // 对象必须非空 fail
        super.validate(obj);
    }
}
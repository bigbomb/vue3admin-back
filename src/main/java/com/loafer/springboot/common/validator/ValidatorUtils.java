package com.loafer.springboot.common.validator;

import com.loafer.springboot.common.exception.RunException;
import com.loafer.springboot.common.utils.Constant;
import com.loafer.springboot.config.ValidatorConfig;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = ValidatorConfig.getValidator();
    }

    /**
     * 功能描述:校验注解参数
     */
    public static <T> void validated(T object, Class<?>... groups) throws RunException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            Constant.StatusCode statusCode = Constant.StatusCode.getStatusCode(5007);
            StringBuilder message = new StringBuilder();
            message.append(statusCode.getMessage());
            String comma = "";
            for (ConstraintViolation<Object> constraint:  constraintViolations) {
                message.append(comma).append(constraint.getPropertyPath()+ "-" + constraint.getMessage());
                comma = ",";
            }
            throw new RunException(5007, message.toString());
        }
    }
}

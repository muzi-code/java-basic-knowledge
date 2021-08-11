package com.github.dev.muzi.base.concurrent.knowledge.exercise.utils.test.beanutils;

import com.github.dev.muzi.base.concurrent.knowledge.exercise.utils.beanutils.PropertyUtils;
import com.github.dev.muzi.base.concurrent.knowledge.exercise.utils.test.beanutils.mdoel.Home;
import org.apache.commons.beanutils.MethodUtils;
import org.junit.Test;
import com.github.dev.muzi.base.concurrent.knowledge.exercise.utils.test.beanutils.mdoel.User;

/**
 * @author lifuyi8
 * @since 2021/4/22 3:42 下午
 */
public class PropertyUtilsTest {

    @Test
    public void test01() throws Exception {
        User user = User.builder().name("李福毅").home(
                Home.builder().address("吉林省通化市").build()
        ).password("abc12345678").build();
//        PropertyUtils.setProperty(user,"name","123");
        System.out.println(PropertyUtils.getProperty(user, "name"));
        System.out.println(PropertyUtils.getProperty(user, "home.address"));
        System.out.println(MethodUtils.invokeExactMethod(user,"getName",null));
    }

}

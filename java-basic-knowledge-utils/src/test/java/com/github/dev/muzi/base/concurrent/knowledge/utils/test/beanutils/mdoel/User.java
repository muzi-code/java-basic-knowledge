package com.github.dev.muzi.base.concurrent.knowledge.utils.test.beanutils.mdoel;

import lombok.Builder;
import lombok.Data;

/**
 * @author lifuyi8
 * @since 2021/4/22 4:11 下午
 */

@Data
@Builder
public class User {
    private String name;
    private String password;
    private Home home;
}

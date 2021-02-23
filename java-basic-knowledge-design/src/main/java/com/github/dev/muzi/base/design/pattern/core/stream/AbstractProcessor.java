package com.github.dev.muzi.base.design.pattern.core.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractProcessor implements ProcessHandler {

    /*
     * 名称
     */
    private String name;

    /*
     * 标识
     */
    private String code;

    /*
     * 描述
     */
    private String description;

}

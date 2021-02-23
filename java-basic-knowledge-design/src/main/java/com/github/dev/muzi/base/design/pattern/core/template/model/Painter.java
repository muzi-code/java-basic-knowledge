package com.github.dev.muzi.base.design.pattern.core.template.model;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@AllArgsConstructor
public class Painter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Painter.class);

    private String name;

    public void draw(Brush brush, DrawPanel panel, Pigment pigment) {
        LOGGER.info("画家【{}】绘画开始，使用【{},{},{}】这些工具开始他的创作之旅！", JSON.toJSONString(name), JSON.toJSONString(brush), JSON.toJSONString(panel), JSON.toJSONString(pigment));
    }
}

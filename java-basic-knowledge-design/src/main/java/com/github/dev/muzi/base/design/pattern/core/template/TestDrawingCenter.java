package com.github.dev.muzi.base.design.pattern.core.template;

import com.github.dev.muzi.base.design.pattern.core.template.model.Brush;
import com.github.dev.muzi.base.design.pattern.core.template.model.DrawPanel;
import com.github.dev.muzi.base.design.pattern.core.template.model.Painter;
import com.github.dev.muzi.base.design.pattern.core.template.model.Pigment;
import org.springframework.stereotype.Service;

/**
 * 自定义绘画中心，配置了专业的画家和各种不错的绘画工具。
 */
@Service("testDrawingCenter")
public class TestDrawingCenter extends AbstractDrawingCenter implements Drawing {

    @Override
    public Brush createBrushInstance() {
        return new Brush("狼毫毛刷");
    }

    @Override
    public DrawPanel createDrawPanelInstance() {
        return new DrawPanel("宽大的画板");
    }

    @Override
    public Pigment createPigmentInstance() {
        return new Pigment("五彩缤纷的颜料");
    }

    @Override
    public Painter createPainterInstance() {
        return new Painter("专业的画家");
    }
}

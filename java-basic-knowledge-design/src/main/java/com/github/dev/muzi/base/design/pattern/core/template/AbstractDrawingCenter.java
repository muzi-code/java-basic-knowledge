package com.github.dev.muzi.base.design.pattern.core.template;

import com.github.dev.muzi.base.design.pattern.core.template.model.Brush;
import com.github.dev.muzi.base.design.pattern.core.template.model.DrawPanel;
import com.github.dev.muzi.base.design.pattern.core.template.model.Painter;
import com.github.dev.muzi.base.design.pattern.core.template.model.Pigment;

/**
 * 绘画中心
 */
public abstract class AbstractDrawingCenter implements Drawing {

    /**
     * 模版方法流程
     */
    @Override
    public void draw() {

        Brush brush = createBrushInstance();

        DrawPanel drawPanel = createDrawPanelInstance();

        Pigment pigment = createPigmentInstance();

        Painter painter = createPainterInstance();

        painter.draw(brush, drawPanel, pigment);
    }

    /**
     * 钩子方法子类自定义实现
     */
    abstract public Brush createBrushInstance();

    abstract public DrawPanel createDrawPanelInstance();

    abstract public Pigment createPigmentInstance();

    abstract public Painter createPainterInstance();
}

package com.feary.airhockey.objects;

import com.feary.util.VertexArray;
import com.feary.airhockey.program.ColorShaderProgram;
import com.feary.util.Geometry;

import java.util.List;

/**
 * Created by feary on 17-9-27.
 */

public class Puck {
    private static final int POSITION_COMPONENT_COUNT = 3;
    public final float radius, height;

    private final VertexArray vertexArray;
    private final List<ObjectBuilder.DrawCommand> drawList;

    public Puck(float radius, float height, int numPointsAroundPuck) {
        ObjectBuilder.GeneratedData generatedData =
                ObjectBuilder.createPuck(new Geometry.Cylinder(new Geometry.Point(0f, 0f, 0f), radius, height), numPointsAroundPuck);

        this.radius = radius;
        this.height = height;
        vertexArray = new VertexArray((generatedData.vertexData));
        drawList = generatedData.drawList;

    }

    //把顶点着色器的数据绑定到着色器程序对应属性上
    public void bindData(ColorShaderProgram colorShaderProgram) {
        vertexArray.setVertexAttribPointer(0, colorShaderProgram.getPositionAttributeLocation(), POSITION_COMPONENT_COUNT, 0);
    }

    //遍历 ObjectBuilder 绘制的列表
    public void draw() {
        for (ObjectBuilder.DrawCommand drawCommand : drawList) {
            drawCommand.draw();
        }
    }
}

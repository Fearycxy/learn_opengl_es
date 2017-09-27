package com.feary.airhockey.objects;

import com.feary.airhockey.data.VertexArray;
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
}

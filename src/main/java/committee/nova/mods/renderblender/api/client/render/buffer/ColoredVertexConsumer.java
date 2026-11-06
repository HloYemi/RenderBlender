package committee.nova.mods.renderblender.api.client.render.buffer;

import com.mojang.blaze3d.vertex.VertexConsumer;

public class ColoredVertexConsumer implements VertexConsumer {
    private final VertexConsumer delegate;
    private final float red, green, blue;

    public ColoredVertexConsumer(VertexConsumer delegate, float red, float green, float blue) {
        this.delegate = delegate;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public VertexConsumer addVertex(float x, float y, float z) {
        return delegate.addVertex(x, y, z);
    }

    @Override
    public VertexConsumer setColor(int r, int g, int b, int a) {
        // 使用自定义颜色替换原始颜色
        return delegate.setColor((int)(red * 255), (int)(green * 255), (int)(blue * 255), a);
    }

    @Override
    public VertexConsumer setUv(float u, float v) {
        return delegate.setUv(u, v);
    }

    @Override
    public VertexConsumer setUv1(int u, int v) {
        return delegate.setUv1(u, v);
    }

    @Override
    public VertexConsumer setUv2(int u, int v) {
        return delegate.setUv2(u, v);
    }

    @Override
    public VertexConsumer setNormal(float x, float y, float z) {
        return delegate.setNormal(x, y, z);
    }
}

package pl.manco.core;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class Animations {

    public static Animation loop(float frameDuration, TextureRegion ...keyFrames) {
        final Animation animation = new Animation(frameDuration, keyFrames);
        animation.setPlayMode(Animation.PlayMode.LOOP);
        return animation;
    }

    public static TextureRegion[] regions(TextureAtlas atlas, String name, int start, int end) {
        return IntStream.rangeClosed(start, end)
                .mapToObj(getRegion(atlas, name))
                .toArray(TextureRegion[]::new);

    }

    private static IntFunction<TextureRegion> getRegion(TextureAtlas atlas, String name) {
        return i -> atlas.findRegion(name+i);
    }
}

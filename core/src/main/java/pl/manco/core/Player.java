package pl.manco.core;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

public abstract class Player {

    private final Map<State, Animation> animations;

    private final Position position = new Position();

    private State state = State.STANCE;

    public Player(String texturesPackName) {
        final TextureAtlas textures = new TextureAtlas("chars/" + texturesPackName + ".pack");
        animations = ImmutableMap.of(
            State.STANCE, Animations.loop(0.10f, Animations.regions(textures, "stance", 1, 8)),
            State.WALK, Animations.loop(0.10f, Animations.regions(textures, "walk", 1, 9)),
            State.PUNCH, Animations.loop(0.08f, Animations.regions(textures, "punch", 1, 6))
            );
    }

    public Animation image() {
        return animations.get(state);
    }

    public void setState(State state) {
        this.state = state;
    }

    public Position getPosition() {
        return position;
    }
}

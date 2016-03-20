package pl.manco.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Player {

    private final TextureRegion image;
    private final Position position = new Position();

    public Player(String texturesPackName) {
        TextureAtlas textures = new TextureAtlas(Gdx.files.internal("chars/" + texturesPackName + ".pack"));
        image = textures.findRegion("stance1");
    }

    public TextureRegion image() {
        return image;
    }

    public Position getPosition() {
        return position;
    }
}

package pl.manco.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.function.BiConsumer;

public class GameScreen extends ScreenAdapter {

    private static final BiConsumer<Position, Integer> NOP = (a1, a2) -> {};

    private Texture background;
    private SpriteBatch batch;
    private Player player;

    @Override
    public void show() {
        background = new Texture(Gdx.files.internal("stages/Stage1-1.png"));
        player = new Jax();
        player.getPosition().up(10);
        player.getPosition().right(10);
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        queryInput();
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(player.image(), player.getPosition().x(), player.getPosition().y());
        batch.end();
    }

    private void queryInput() {
        movePlayerIfPressed(Input.Keys.LEFT);
        movePlayerIfPressed(Input.Keys.RIGHT);
        movePlayerIfPressed(Input.Keys.UP);
        movePlayerIfPressed(Input.Keys.DOWN);
    }

    private void movePlayerIfPressed(int key) {
        final int step = 5;
        if (Gdx.input.isKeyPressed(key)) {
            inputToMovement(key).accept(player.getPosition(), step);
        }
    }

    private static BiConsumer<Position, Integer> inputToMovement(int inputKey) {
        switch (inputKey) {
            case Input.Keys.LEFT:
                return Position::left;
            case Input.Keys.RIGHT:
                return Position::right;
            case Input.Keys.UP:
                return Position::up;
            case Input.Keys.DOWN:
                return Position::down;
            default:
                return NOP;
        }
    }
}

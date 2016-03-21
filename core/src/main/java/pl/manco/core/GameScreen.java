package pl.manco.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.function.BiConsumer;

public class GameScreen extends ScreenAdapter {

    private static final BiConsumer<Position, Integer> NOP = (a1, a2) -> {};

    private Texture background;
    private SpriteBatch batch;
    private Player player;

    private float elapsedTime;

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
        elapsedTime += delta;
        queryInput();
        batch.begin();
        batch.draw(background, 0, 0);
        TextureRegion frame = player.image().getKeyFrame(elapsedTime);
        batch.draw(frame, player.getPosition().x(), player.getPosition().y());
        batch.end();
    }

    private void queryInput() {
        player.setState(State.STANCE);

        movePlayerIfPressed(Input.Keys.LEFT);
        movePlayerIfPressed(Input.Keys.RIGHT);
        movePlayerIfPressed(Input.Keys.UP);
        movePlayerIfPressed(Input.Keys.DOWN);

        punchIfPressed(Input.Keys.Q);
    }

    private void movePlayerIfPressed(int key) {
        final int step = 3;
        if (Gdx.input.isKeyPressed(key)) {
            inputToMovement(key).accept(player.getPosition(), step);
            player.setState(State.WALK);
        }
    }

    private void punchIfPressed(int key) {
        if (Gdx.input.isKeyPressed(key)) {
            player.setState(State.PUNCH);
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

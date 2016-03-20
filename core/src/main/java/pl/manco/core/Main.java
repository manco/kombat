package pl.manco.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;

public class Main extends Game {

	private final Screen game = new GameScreen();

    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}

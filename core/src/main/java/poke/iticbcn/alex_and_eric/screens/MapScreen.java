package poke.iticbcn.alex_and_eric.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import poke.iticbcn.alex_and_eric.PokemonFireRedGame;
import poke.iticbcn.alex_and_eric.actors.Debug;
import poke.iticbcn.alex_and_eric.actors.Player;

public class MapScreen implements Screen {

    private final PokemonFireRedGame game;
    private SpriteBatch batch;
    private Texture mapTexture;
    private Music backgroundMusic;
    private Player player;


    public MapScreen(PokemonFireRedGame game) {
        this.game = game;
        this.batch = game.getBatch();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        mapTexture = new Texture("ruta11.png");

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Route11Music.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        player = new Player();
    }

    @Override
    public void render(float delta) {
        player.update(delta);

        ScreenUtils.clear(0.1f, 0.5f, 0.8f, 1);
        batch.begin();
        batch.draw(mapTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player.render(batch);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        mapTexture.dispose();
        backgroundMusic.dispose();
        player.dispose();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

}

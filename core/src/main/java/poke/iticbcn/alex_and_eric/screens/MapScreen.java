package poke.iticbcn.alex_and_eric.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import poke.iticbcn.alex_and_eric.PokemonFireRedGame;
import poke.iticbcn.alex_and_eric.actors.Player;

public class MapScreen implements Screen {

    private final PokemonFireRedGame game;
    private SpriteBatch batch;
    private Texture mapTexture;
    private Music backgroundMusic;
    private Player player;

    private List<Rectangle> gespaZones;

    // Escalat
    private float scaleX;
    private float scaleY;

    public MapScreen(PokemonFireRedGame game) {
        this.game = game;
        this.batch = game.getBatch();
    }

    @Override
    public void show() {
        mapTexture = new Texture("ruta11.png");

        // Escales segons la pantalla
        scaleX = (float) Gdx.graphics.getWidth() / mapTexture.getWidth();
        scaleY = (float) Gdx.graphics.getHeight() / mapTexture.getHeight();

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Route11Music.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        player = new Player();

        // Escalem la zona de gespa
        gespaZones = new ArrayList<>();
        gespaZones.add(new Rectangle(
            469 * scaleX,
            225 * scaleY,
            80 * scaleX,
            41 * scaleY
        ));
    }

    @Override
    public void render(float delta) {
        player.update(delta);

        float escala = 4f;
        float playerCenterX = player.getX() + (16 * escala) / 2;
        float playerCenterY = player.getY() + (23 * escala) / 2;

        for (Rectangle zona : gespaZones) {
            if (zona.contains(playerCenterX, playerCenterY)) {
                if (Math.random() < 0.1) {
                    System.out.println("S'ha iniciat un combat!");
                    backgroundMusic.stop();
                    Gdx.input.setInputProcessor(null);
                    game.setScreen(new CombatScreen());
                    return;
                }
            }
        }

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
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }
}

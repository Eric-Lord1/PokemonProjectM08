package poke.iticbcn.alex_and_eric.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.awt.Font;

import poke.iticbcn.alex_and_eric.PokemonFireRedGame;

public class CombatScreen implements Screen {

    public static Music music;
    private Batch batch;
    private OrthographicCamera camera;
    private Stage stage;
    private BitmapFont font;
    private static Texture background;
    private static Texture combatText;
    private static Texture pkmEnemigo;
    private static Texture pkmAliado;
    private static Texture vidaEnemigo;
    private static Texture vidaAliado;
    private static GlyphLayout txtPlacaje;
    private static GlyphLayout txtPkmEnemigo;
    private static GlyphLayout txtPkmAliado;

    public CombatScreen(PokemonFireRedGame game){

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/combate.mp3"));
        music.setVolume(0.2f);
        music.setLooping(true);
        music.play();

        background = new Texture(Gdx.files.internal("combate_limpio.png"));
        combatText = new Texture(Gdx.files.internal("attack_selection.png"));
        pkmEnemigo = new Texture(Gdx.files.internal("squirtle.png"));
        pkmAliado = new Texture(Gdx.files.internal("back/4_back.png"));
        vidaEnemigo = new Texture(Gdx.files.internal("hpbar_top.png"));
        vidaAliado = new Texture(Gdx.files.internal("hpbar_bot.png"));


        font = new BitmapFont();
        txtPlacaje = new GlyphLayout();
        txtPlacaje.setText(new BitmapFont(), "Placaje");

        txtPkmAliado = new GlyphLayout();
        txtPkmAliado.setText(new BitmapFont(), "Charmander");

        txtPkmEnemigo = new GlyphLayout();
        txtPkmEnemigo.setText(new BitmapFont(), "Squirt");


        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 768);

        StretchViewport viewport = new StretchViewport(1024, 768, camera);

        stage = new Stage(viewport);

        batch = stage.getBatch();



        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 250, 1024, 518);
        batch.draw(combatText, 0, 0, 1024, 250);
        batch.draw(pkmEnemigo, 650, 450, 200,200);
        batch.draw(pkmAliado, 175, 210, 250,250);
        batch.draw(vidaEnemigo, 100, 500, 300,200);
        batch.draw(vidaAliado, 650, 250, 350,200);
        font.setColor(Color.BLACK);
        font.getData().setScale(3.5f);
        font.draw(batch, "Pajilla", 50, 190);

        font.getData().setScale(2.8f);
        font.draw(batch, "Charmander", 690, 420);
        font.draw(batch, "Squirt", 140, 650);
        font.getData().setScale(3.0f);
        font.draw(batch, "35", 850, 185);
        font.draw(batch, "35", 940, 185);
        font.draw(batch, "Normal", 820, 95);
        batch.end();

        stage.act(delta);
        stage.draw();
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

    @Override
    public void dispose() {

    }
}

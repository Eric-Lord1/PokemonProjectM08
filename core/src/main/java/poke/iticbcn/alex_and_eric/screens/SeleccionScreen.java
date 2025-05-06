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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import poke.iticbcn.alex_and_eric.PokemonFireRedGame;

public class SeleccionScreen implements Screen {

    public static Music music;
    private Batch batch;
    private OrthographicCamera camera;
    private GlyphLayout textLayout;
    private Stage stage;
    private Image charmanderImage;
    private Image bulbasaurImage;
    private Image squirtleImage;
    private BitmapFont font;

    private static Texture background;
    public static Texture sheetPokemon;
    public static Texture charmander;
    public static Texture bulbasaur;
    public static Texture squirtle;

    public SeleccionScreen(PokemonFireRedGame game){

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/tema_principal.mp3"));
        music.setVolume(0.2f);
        music.setLooping(true);
        music.play();

        font = new BitmapFont();
        textLayout = new GlyphLayout();
        textLayout.setText(new BitmapFont(), "Selecciona Pokemon");

        background = new Texture(Gdx.files.internal("oak.png"));
        sheetPokemon = new Texture(Gdx.files.internal("1raGen.png"));
        charmander = new Texture(Gdx.files.internal("charmander.png"));
        bulbasaur = new Texture(Gdx.files.internal("bulbasaur.png"));
        squirtle = new Texture(Gdx.files.internal("squirtle.png"));


        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 768);

        StretchViewport viewport = new StretchViewport(1024, 768, camera);

        stage = new Stage(viewport);

        batch = stage.getBatch();

        charmanderImage = new Image(charmander);
        charmanderImage.setScale(0.2f);

        bulbasaurImage = new Image(bulbasaur);
        bulbasaurImage.setScale(0.15f);

        squirtleImage = new Image(squirtle);
        squirtleImage.setScale(0.2f);

        charmanderImage.setPosition(250, 50);

        bulbasaurImage.setPosition(400, 50);


        squirtleImage.setPosition(590, 50);

        stage.addActor(charmanderImage);
        stage.addActor(bulbasaurImage);
        stage.addActor(squirtleImage);

        Gdx.input.setInputProcessor(stage);


        charmanderImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("¡Charmander clickeado!");
                music.stop();
                Gdx.input.setInputProcessor(null);
                game.setScreen(new MapScreen(game));
            }
        });

        bulbasaurImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("¡Bulbasaur clickeado!");
                music.stop();
                Gdx.input.setInputProcessor(null);
                game.setScreen(new MapScreen(game));
            }
        });

        squirtleImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("¡Squirtle clickeado!");
                music.stop();
                Gdx.input.setInputProcessor(null);
                game.setScreen(new MapScreen(game));
            }
        });


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0, 1024, 768);
        font.setColor(Color.BLACK);
        font.getData().setScale(3f);
        font.draw(batch, "Clica un Pokemon para empezar", 200, 700);
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

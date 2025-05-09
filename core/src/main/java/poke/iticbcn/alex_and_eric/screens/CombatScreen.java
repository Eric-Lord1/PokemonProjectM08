package poke.iticbcn.alex_and_eric.screens;

import static com.badlogic.gdx.math.MathUtils.random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import poke.iticbcn.alex_and_eric.PokemonFireRedGame;
import poke.iticbcn.alex_and_eric.assets.AssetLoader;

public class CombatScreen implements Screen {

    private enum CombatState { SELECCION, ATACANT, MISSATGE, FINAL }
    private CombatState estat = CombatState.SELECCION;

    private final Game game;
    private final String inicial;
    private String enemigo;

    private Stage stage;
    private Batch batch;
    private OrthographicCamera camera;
    private BitmapFont font;
    private Music music;

    private Texture pkmAliado;
    private Texture pkmEnemigo;
    private Texture vidaEnemigo;

    private Image txtTackleImage, flechaImg;

    private int contVida = 0;

    public CombatScreen(Game game, String inicial) {
        this.game = game;
        this.inicial = inicial;
        init();
    }

    private void init() {
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/combate.mp3"));
        music.setVolume(0.2f);
        music.setLooping(true);
        music.play();

        int num = random.nextInt(100) + 1;
        if (num <= 1) {
            pkmEnemigo = AssetLoader.raticate; enemigo = "Raticate";
        } else if (num <= 10) {
            pkmEnemigo = AssetLoader.pidgeotto; enemigo = "Pidgeotto";
        } else if (num <= 25) {
            pkmEnemigo = AssetLoader.rattata; enemigo = "Rattata";
        } else if (num <= 50) {
            pkmEnemigo = AssetLoader.pidgey; enemigo = "Pidgey";
        } else if (num <= 70) {
            pkmEnemigo = AssetLoader.spearow; enemigo = "Spearow";
        } else if (num <= 85) {
            pkmEnemigo = AssetLoader.sandsrew; enemigo = "Sandsrew";
        } else {
            pkmEnemigo = AssetLoader.ekans; enemigo = "Ekans";
        }

        if (inicial.equalsIgnoreCase("Charmander")) pkmAliado = AssetLoader.backCharmander;
        else if (inicial.equalsIgnoreCase("Bulbasaur")) pkmAliado = AssetLoader.backBulbasaur;
        else pkmAliado = AssetLoader.backSquirtle;

        vidaEnemigo = AssetLoader.hpFull;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 768);
        StretchViewport viewport = new StretchViewport(1024, 768, camera);
        stage = new Stage(viewport);
        batch = stage.getBatch();

        font = new BitmapFont();
        font.getData().setScale(3.0f);

        txtTackleImage = new Image(AssetLoader.ataquePlacaje);
        txtTackleImage.setPosition(70, 140);
        txtTackleImage.setScale(2f);

        flechaImg = new Image(AssetLoader.flecha);
        flechaImg.setPosition(40, 150);
        flechaImg.setScale(4f);

        stage.addActor(txtTackleImage);
        stage.addActor(flechaImg);

        txtTackleImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (estat == CombatState.SELECCION) {
                    txtTackleImage.setVisible(false);
                    flechaImg.setVisible(false);
                    txtTackleImage.setTouchable(Touchable.disabled);
                    flechaImg.setTouchable(Touchable.disabled);
                    estat = CombatState.ATACANT;
                }
            }
        });

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateHpTexture();

        batch.begin();
        batch.draw(AssetLoader.combateBackground, 0, 250, 1024, 518);
        batch.draw(vidaEnemigo, 100, 500, 300, 200);
        batch.draw(AssetLoader.vidaAliado, 650, 250, 350, 200);
        batch.draw(pkmEnemigo, 620, 430, 250, 250);
        batch.draw(pkmAliado, 175, 250, 200, 200);

        font.setColor(Color.BLACK);
        font.draw(batch, inicial, 690, 420);
        font.draw(batch, enemigo, 140, 650);

        switch (estat) {
            case SELECCION:
                batch.draw(AssetLoader.attackSelection, 0, 0, 1024, 250);
                font.draw(batch, "35", 850, 185);
                font.draw(batch, "35", 940, 185);
                font.draw(batch, "Normal", 820, 95);
                break;
            case ATACANT:
                batch.draw(AssetLoader.cuadroAzul, 0, 0, 1024, 250);
                font.setColor(Color.WHITE);
                font.draw(batch, inicial + " usó Placaje!", 50, 200);
                estat = (contVida >= 2) ? CombatState.FINAL : CombatState.MISSATGE;
                break;
            case MISSATGE:
                batch.draw(AssetLoader.cuadroAzul, 0, 0, 1024, 250);
                font.setColor(Color.WHITE);
                font.draw(batch, "Pulsa para continuar", 50, 200);
                if (Gdx.input.justTouched()) {
                    contVida++;
                    txtTackleImage.setVisible(true);
                    flechaImg.setVisible(true);
                    txtTackleImage.setTouchable(Touchable.enabled);
                    flechaImg.setTouchable(Touchable.enabled);
                    estat = CombatState.SELECCION;
                }
                break;
            case FINAL:
                batch.draw(AssetLoader.cuadroAzul, 0, 0, 1024, 250);
                font.setColor(Color.WHITE);
                font.draw(batch, enemigo + " ha sido derrotado! Pulsa per tornar.", 50, 200);
                if (Gdx.input.justTouched()) {
                    music.stop();
                    game.setScreen(new MapScreen((PokemonFireRedGame) game, inicial));
                }
                break;
        }

        batch.end();
        stage.act(delta);
        stage.draw();
    }

    private void updateHpTexture() {
        if (contVida == 0) vidaEnemigo = AssetLoader.hpFull;
        else if (contVida == 1) vidaEnemigo = AssetLoader.hpHalf;
        else vidaEnemigo = AssetLoader.hpZero;
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void show() {}

    @Override
    public void dispose() {
        stage.dispose();
        music.dispose();
    }
}

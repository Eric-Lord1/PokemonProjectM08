package poke.iticbcn.alex_and_eric.screens;

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
import poke.iticbcn.alex_and_eric.actors.Player;
import poke.iticbcn.alex_and_eric.assets.AssetLoader;

public class CombatScreen implements Screen {

    private enum CombatState { SELECCION, ATACANT, MISSATGE, FINAL }

    private CombatState estat = CombatState.SELECCION;
    private final Game game;
    private final Player player;
    private Stage stage;
    private Batch batch;
    private OrthographicCamera camera;
    private BitmapFont font;
    private Image txtTackleImage, flechaImg;
    private Music music;
    private int contVida = 0;

    public CombatScreen(Game game, Player player) {
        this.game = game;
        this.player = player;
        init();
    }

    private void init() {
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/combate.mp3"));
        music.setVolume(0.2f);
        music.setLooping(true);
        music.play();

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

        batch.begin();
        batch.draw(AssetLoader.combateBackground, 0, 250, 1024, 518);
        batch.draw(getHpTexture(), 100, 500, 300, 200); // vida enemic
        batch.draw(AssetLoader.hpFull, 650, 250, 350, 200); // vida aliat
        batch.draw(AssetLoader.pkmEnemigo, 650, 450, 200, 200);
        batch.draw(AssetLoader.pkmAliado, 175, 210, 250, 250);

        switch (estat) {
            case SELECCION:
                batch.draw(AssetLoader.attackSelection, 0, 0, 1024, 250);
                font.setColor(Color.BLACK);
                font.draw(batch, "Charmander", 690, 420);
                font.draw(batch, "Squirt", 140, 650);
                font.draw(batch, "35", 850, 185);
                font.draw(batch, "35", 940, 185);
                font.draw(batch, "Normal", 820, 95);
                break;

            case ATACANT:
                batch.draw(AssetLoader.cuadroAzul, 0, 0, 1024, 250);
                font.setColor(Color.WHITE);
                font.draw(batch, "Has usat Placaje!", 50, 200);
                estat = (contVida >= 2) ? CombatState.FINAL : CombatState.MISSATGE;
                break;

            case MISSATGE:
                batch.draw(AssetLoader.cuadroAzul, 0, 0, 1024, 250);
                font.setColor(Color.WHITE);
                font.draw(batch, "Torna a atacar", 50, 200);
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
                font.draw(batch, "Has guanyat el combat! Pulsa per continuar.", 50, 200);
                if (Gdx.input.justTouched()) {
                    music.stop();
                    game.setScreen(new MapScreen((PokemonFireRedGame) game, player));
                }
                break;
        }

        batch.end();
        stage.act(delta);
        stage.draw();
    }

    private Texture getHpTexture() {
        if (contVida == 0) return AssetLoader.hpFull;
        if (contVida == 1) return AssetLoader.hpHalf;
        return AssetLoader.hpZero;
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

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
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import poke.iticbcn.alex_and_eric.PokemonFireRedGame;

public class CombatScreen implements Screen {

    public static Game game;
    public static Music music;
    private Batch batch;
    private OrthographicCamera camera;
    private Stage stage;
    private BitmapFont font;
    private boolean showStats = true;
    private int contVida = 0;
    boolean mensajeMostrado = false;
    boolean esperandoNuevoToque = false;
    private String inicial;
    private String enemigo;

    private static Texture background;
    private static Texture combatText;
    private static Texture pkmEnemigo;
    private static Texture pkmAliado;
    private static Texture vidaEnemigo;
    private static Texture vidaAliado;
    private static Texture txtTackleTexture;
    private static Image txtTackleImage;
    private static Image flechaImg;
    private static GlyphLayout txtPlacaje;
    private static GlyphLayout txtPkmEnemigo;
    private static GlyphLayout txtPkmAliado;

    public CombatScreen(PokemonFireRedGame game, String inicial){
        this.game = game;
        this.inicial = inicial;
        int num = random.nextInt(100) + 1;
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/combate.mp3"));
        music.setVolume(0.2f);
        music.setLooping(true);
        music.play();

        background = new Texture(Gdx.files.internal("combate_limpio.png"));
        combatText = new Texture(Gdx.files.internal("attack_selection.png"));


        if (num <= 1) {
            pkmEnemigo = new Texture(Gdx.files.internal("font/raticate.png"));
            enemigo = "Raticate";
        } else if (num <= 10) {
            pkmEnemigo = new Texture(Gdx.files.internal("font/pidgeotto.png"));
            enemigo = "Pidgeotto";
        } else if (num <= 25) {
            pkmEnemigo = new Texture(Gdx.files.internal("font/rattata.png"));
            enemigo = "Rattata";
        } else if (num <= 50) {
            pkmEnemigo = new Texture(Gdx.files.internal("font/pidgey.png"));
            enemigo = "Pidgey";
        } else if (num <= 70) {
            pkmEnemigo = new Texture(Gdx.files.internal("font/spearow.png"));
            enemigo = "Spearow";
        } else if (num <= 85) {
            pkmEnemigo = new Texture(Gdx.files.internal("font/sandsrew.png"));
            enemigo = "Sandsrew";
        } else {
            pkmEnemigo = new Texture(Gdx.files.internal("font/ekans.png"));
            enemigo = "Ekans";
        }

        txtTackleTexture = new Texture(Gdx.files.internal("placaje.png"));
        if(inicial.equalsIgnoreCase("Charmander"))pkmAliado = new Texture(Gdx.files.internal("back/4_back.png"));
        if(inicial.equalsIgnoreCase("Bulbasaur"))pkmAliado = new Texture(Gdx.files.internal("back/1_back.png"));
        if(inicial.equalsIgnoreCase("Squirtle"))pkmAliado = new Texture(Gdx.files.internal("back/7_back.png"));
        if(contVida == 0)vidaEnemigo = new Texture(Gdx.files.internal("hpbar_top.png"));
        if(contVida == 1)vidaEnemigo = new Texture(Gdx.files.internal("hpbar_top_50.png"));
        if(contVida == 2)vidaEnemigo = new Texture(Gdx.files.internal("hpbar_top_0.png"));
        vidaAliado = new Texture(Gdx.files.internal("hpbar_bot.png"));
        Texture flecha = new Texture(Gdx.files.internal("flecha.png"));


        font = new BitmapFont();
        txtPlacaje = new GlyphLayout();
        txtPlacaje.setText(new BitmapFont(), "Placaje");

        txtPkmAliado = new GlyphLayout();
        txtPkmAliado.setText(new BitmapFont(), "Charmander");

        txtPkmEnemigo = new GlyphLayout();
        txtPkmEnemigo.setText(new BitmapFont(), "Squirt");

        txtTackleImage = new Image(txtTackleTexture);
        txtTackleImage.setScale(2f);
        flechaImg = new Image(flecha);
        flechaImg.setScale(4f);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 768);

        StretchViewport viewport = new StretchViewport(1024, 768, camera);

        stage = new Stage(viewport);

        batch = stage.getBatch();

        txtTackleImage.setPosition(70,140);
        flechaImg.setPosition(40,150);
        stage.addActor(txtTackleImage);
        stage.addActor(flechaImg);


        Gdx.input.setInputProcessor(stage);

        txtTackleImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                txtTackleImage.setVisible(false);
                flechaImg.setVisible(false);
                txtTackleImage.setTouchable(Touchable.disabled);
                flechaImg.setTouchable(Touchable.disabled);
                showStats = false;
                combatText = new Texture(Gdx.files.internal("cuadro_azul.png"));

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

        if(contVida == 0)vidaEnemigo = new Texture(Gdx.files.internal("hpbar_top.png"));
        if(contVida == 1)vidaEnemigo = new Texture(Gdx.files.internal("hpbar_top_50.png"));
        if(contVida == 2)vidaEnemigo = new Texture(Gdx.files.internal("hpbar_top_0.png"));

        batch.begin();
        batch.draw(background, 0, 250, 1024, 518);
        batch.draw(combatText, 0, 0, 1024, 250);
        batch.draw(pkmEnemigo, 620, 430, 250,250);
        batch.draw(pkmAliado, 175, 250, 200,200);
        batch.draw(vidaEnemigo, 100, 500, 300,200);
        batch.draw(vidaAliado, 650, 250, 350,200);
        font.setColor(Color.BLACK);


        font.getData().setScale(2.8f);
        font.draw(batch, inicial, 690, 420);
        font.draw(batch, enemigo, 140, 650);
        if(showStats && contVida!=2){
            font.getData().setScale(3.0f);
            font.draw(batch, "35", 850, 185);
            font.draw(batch, "35", 940, 185);
            font.draw(batch, "Normal", 820, 95);
        }
        if(!showStats){
            font.getData().setScale(3.0f);
            font.setColor(Color.WHITE);
            font.draw(batch, "Nigga usó placaje. Pulsa para continuar.", 50, 200);
        }
        if (showStats && contVida == 2) {
            font.getData().setScale(3.0f);
            font.setColor(Color.WHITE);
            font.draw(batch, "Has debilitado al pokemon. Pulsa para volver.", 50, 200);

            txtTackleImage.setVisible(false);
            flechaImg.setVisible(false);
            txtTackleImage.setTouchable(Touchable.disabled);
            flechaImg.setTouchable(Touchable.disabled);

            combatText = new Texture(Gdx.files.internal("cuadro_azul.png"));

            if (!mensajeMostrado) {

                mensajeMostrado = true;
                esperandoNuevoToque = true;
            } else if (esperandoNuevoToque && Gdx.input.justTouched()) {

                game.setScreen(new MapScreen((PokemonFireRedGame) game, inicial));
            }
        }
        if(!showStats){
                if(Gdx.input.isTouched()) {
                    combatText = new Texture(Gdx.files.internal("attack_selection.png"));
                    txtTackleImage.setVisible(true);
                    flechaImg.setVisible(true);
                    txtTackleImage.setTouchable(Touchable.enabled);
                    flechaImg.setTouchable(Touchable.enabled);
                    showStats = true;
                    contVida++;
                }
        }



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

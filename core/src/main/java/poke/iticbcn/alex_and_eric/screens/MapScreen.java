package poke.iticbcn.alex_and_eric.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import poke.iticbcn.alex_and_eric.PokemonFireRedGame;
import poke.iticbcn.alex_and_eric.actors.Player;

public class MapScreen implements Screen {

    private final PokemonFireRedGame game;
    private final SpriteBatch batch;
    private final Player player;
    private Texture mapTexture;
    private Pixmap pixmap;
    private Music backgroundMusic;
    private float scaleX;
    private float scaleY;
    private String inicial;

    public MapScreen(PokemonFireRedGame game, String inicial, Player player) {
        this.player = player;
        this.game = game;
        this.batch = game.getBatch();
        this.inicial = inicial;
    }

    @Override
    public void show() {
        pixmap = new Pixmap(Gdx.files.internal("ruta11.png"));
        mapTexture = new Texture(pixmap);

        scaleX = (float) Gdx.graphics.getWidth() / mapTexture.getWidth();
        scaleY = (float) Gdx.graphics.getHeight() / mapTexture.getHeight();

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Route11Music.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
    }

    @Override
    public void render(float delta) {
        player.update(delta);

        float escala = 4f;
        float playerCenterX = player.getX() + (16 * escala) / 2;
        float playerCenterY = player.getY() + (23 * escala) / 2;

        int pixelX = (int)(playerCenterX / scaleX);
        int pixelY = pixmap.getHeight() - (int)(playerCenterY / scaleY); // Invert Y

        if (pixelX >= 0 && pixelX < pixmap.getWidth() && pixelY >= 0 && pixelY < pixmap.getHeight()) {
            int pixel = pixmap.getPixel(pixelX, pixelY);
            int r = (pixel >> 24) & 0xff;
            int g = (pixel >> 16) & 0xff;
            int b = (pixel >> 8) & 0xff;

            if (g > 140 && r < 100 && b < 100) {
                if (Math.random() < 0.02) {
                    System.out.println("S'ha iniciat un combat!");
                    backgroundMusic.stop();
                    Gdx.input.setInputProcessor(null);
                    game.setScreen(new CombatScreen(this.game, inicial, player));
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
        pixmap.dispose();
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

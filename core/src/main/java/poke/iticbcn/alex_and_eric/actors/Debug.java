package poke.iticbcn.alex_and_eric.actors;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class Debug implements Screen {
    private Texture sheet;
    private SpriteBatch batch;

    private final int frameWidth = 24;  // Prova també amb 32, 16, 20
    private final int frameHeight = 24;

    @Override
    public void show() {
        batch = new SpriteBatch();
        sheet = new Texture("personaje.png");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();

        int columns = sheet.getWidth() / frameWidth;
        int rows = sheet.getHeight() / frameHeight;

        for (int row = 0; row < Math.min(rows, 20); row++) {
            for (int col = 0; col < Math.min(columns, 10); col++) {
                TextureRegion region = new TextureRegion(sheet, col * frameWidth, sheet.getHeight() - (row + 1) * frameHeight, frameWidth, frameHeight);
                batch.draw(region, col * (frameWidth + 1), row * (frameHeight + 1));
            }
        }

        batch.end();
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
        batch.dispose();
        sheet.dispose();
    }

}


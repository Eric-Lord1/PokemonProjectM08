package poke.iticbcn.alex_and_eric.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class Player {
    private Texture sheet;
    private Animation<TextureRegion> walkDown, walkLeft, walkRight, walkUp, currentAnimation;

    private float x, y;
    private float speed = 300f;
    private float stateTime;
    private final int frameWidth = 16;
    private final int frameHeight = 23;
    private final int frameSpacing = 17;
    private final int baseX = 9;
    private final int baseY = 42;

    public Player() {
        sheet = new Texture("personajes.png");

        TextureRegion[] downFrames  = new TextureRegion[3];
        TextureRegion[] upFrames    = new TextureRegion[3];
        TextureRegion[] leftFrames  = new TextureRegion[3];
        TextureRegion[] rightFrames = new TextureRegion[3];

        for (int i = 0; i < 3; i++) {
            downFrames[i]  = new TextureRegion(sheet, baseX + (i + 0) * frameSpacing, baseY, frameWidth, frameHeight);
            upFrames[i]    = new TextureRegion(sheet, baseX + (i + 3) * frameSpacing, baseY, frameWidth, frameHeight);
            leftFrames[i]  = new TextureRegion(sheet, baseX + (i + 6) * frameSpacing, baseY, frameWidth, frameHeight);
            rightFrames[i] = new TextureRegion(sheet, baseX + (i + 9) * frameSpacing, baseY, frameWidth, frameHeight);
        }

        walkDown  = new Animation<>(0.03f, downFrames);
        walkUp    = new Animation<>(0.03f, upFrames);
        walkLeft  = new Animation<>(0.03f, leftFrames);
        walkRight = new Animation<>(0.03f, rightFrames);

        currentAnimation = walkDown;

        x = ((float) Gdx.graphics.getWidth() / 2);
        y = ((float) Gdx.graphics.getHeight() /2);
        stateTime = 0f;
    }

    public void update(float delta) {
        boolean moving = false;

        if (Gdx.input.isTouched()) {
            int touchX = Gdx.input.getX();
            int touchY = Gdx.input.getY();
            touchY = Gdx.graphics.getHeight() - touchY; // corregir eje Y

            // calcular centro del jugador
            float scale = 4f;
            float playerCenterX = x + (frameWidth * scale) / 2;
            float playerCenterY = y + (frameHeight * scale) / 2;

            float diffX = touchX - playerCenterX;
            float diffY = touchY - playerCenterY;

            if (Math.abs(diffX) > Math.abs(diffY)) {
                // Movimiento horizontal
                if (diffX < 0) {
                    x -= speed * delta;
                    currentAnimation = walkLeft;
                } else {
                    x += speed * delta;
                    currentAnimation = walkRight;
                }
            } else {
                // Movimiento vertical
                if (diffY < 0) {
                    y -= speed * delta;
                    currentAnimation = walkDown;
                } else {
                    y += speed * delta;
                    currentAnimation = walkUp;
                }
            }
            moving = true;
        }

        stateTime = moving ? stateTime + delta : 0f;
    }


    public void render(SpriteBatch batch) {
        TextureRegion frame = currentAnimation.getKeyFrame(stateTime, true);
        float scale = 4f;
        batch.draw(frame, x, y, frame.getRegionWidth() * scale, frame.getRegionHeight() * scale);
    }

    public void dispose() {
        sheet.dispose();
    }
}


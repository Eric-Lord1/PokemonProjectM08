package poke.iticbcn.alex_and_eric;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import poke.iticbcn.alex_and_eric.assets.*;
import poke.iticbcn.alex_and_eric.screens.CombatScreen;
import poke.iticbcn.alex_and_eric.screens.MapScreen;
import poke.iticbcn.alex_and_eric.screens.SeleccionScreen;

public class PokemonFireRedGame extends Game {
    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        AssetLoader.load();
        setScreen(new SeleccionScreen(this));
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    @Override
    public void dispose() {
        batch.dispose();
        AssetLoader.dispose();
        super.dispose();
    }
}

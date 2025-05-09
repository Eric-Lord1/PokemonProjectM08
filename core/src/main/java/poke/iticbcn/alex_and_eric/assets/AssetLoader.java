package poke.iticbcn.alex_and_eric.assets;

import com.badlogic.gdx.graphics.Texture;

public class AssetLoader {
    public static Texture combateBackground;
    public static Texture hpFull, hpHalf, hpZero;
    public static Texture pkmEnemigo, pkmAliado;
    public static Texture ataquePlacaje, cuadroAzul, attackSelection;
    public static Texture flecha;

    public static void load() {
        combateBackground = new Texture("combate_limpio.png");
        attackSelection = new Texture("attack_selection.png");
        cuadroAzul = new Texture("cuadro_azul.png");

        pkmEnemigo = new Texture("squirtle.png");
        pkmAliado = new Texture("back/4_back.png");

        hpFull = new Texture("hpbar_top.png");
        hpHalf = new Texture("hpbar_top_50.png");
        hpZero = new Texture("hpbar_top_0.png");

        ataquePlacaje = new Texture("placaje.png");
        flecha = new Texture("flecha.png");
    }

    public static void dispose() {
        combateBackground.dispose();
        attackSelection.dispose();
        cuadroAzul.dispose();
        pkmEnemigo.dispose();
        pkmAliado.dispose();
        hpFull.dispose();
        hpHalf.dispose();
        hpZero.dispose();
        ataquePlacaje.dispose();
        flecha.dispose();
    }
}

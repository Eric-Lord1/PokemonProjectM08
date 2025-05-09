package poke.iticbcn.alex_and_eric.assets;

import com.badlogic.gdx.graphics.Texture;

public class AssetLoader {
    public static Texture combateBackground;
    public static Texture hpFull, hpHalf, hpZero;
    public static Texture vidaAliado;
    public static Texture ataquePlacaje, cuadroAzul, attackSelection;
    public static Texture flecha;

    // Pokémon enemics
    public static Texture rattata, raticate, pidgey, pidgeotto, spearow, sandsrew, ekans;

    // Backs dels inicials
    public static Texture backCharmander, backBulbasaur, backSquirtle;

    public static void load() {
        combateBackground = new Texture("combate_limpio.png");
        attackSelection = new Texture("attack_selection.png");
        cuadroAzul = new Texture("cuadro_azul.png");

        hpFull = new Texture("hpbar_top.png");
        hpHalf = new Texture("hpbar_top_50.png");
        hpZero = new Texture("hpbar_top_0.png");
        vidaAliado = new Texture("hpbar_bot.png");

        ataquePlacaje = new Texture("placaje.png");
        flecha = new Texture("flecha.png");

        // Enemics
        rattata = new Texture("font/rattata.png");
        raticate = new Texture("font/raticate.png");
        pidgey = new Texture("font/pidgey.png");
        pidgeotto = new Texture("font/pidgeotto.png");
        spearow = new Texture("font/spearow.png");
        sandsrew = new Texture("font/sandsrew.png");
        ekans = new Texture("font/ekans.png");

        // Inicials
        backCharmander = new Texture("back/4_back.png");
        backBulbasaur = new Texture("back/1_back.png");
        backSquirtle = new Texture("back/7_back.png");
    }

    public static void dispose() {
        combateBackground.dispose();
        attackSelection.dispose();
        cuadroAzul.dispose();

        hpFull.dispose();
        hpHalf.dispose();
        hpZero.dispose();
        vidaAliado.dispose();

        ataquePlacaje.dispose();
        flecha.dispose();

        rattata.dispose();
        raticate.dispose();
        pidgey.dispose();
        pidgeotto.dispose();
        spearow.dispose();
        sandsrew.dispose();
        ekans.dispose();

        backCharmander.dispose();
        backBulbasaur.dispose();
        backSquirtle.dispose();
    }
}

package nl.han.ica.Savethetower;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

/**
 * @author Ralph Niels
 * Een zwaardvis is een spelobject dat zelfstandig
 * door de wereld beweegt
 */
public class Mob extends SpriteObject {

    private WaterWorld world;

    /**
     * Constructor
     * @param world Referentie naar de wereld
     */
    public Mob(WaterWorld world) {
        this(new Sprite("src/main/java/nl/han/ica/Savethetower/media/basic.png"));
        this.world=world;
    }

    /**
     * Maak een Swordfish aan met een sprite
     * @param sprite De sprite die aan dit object gekoppeld moet worden
     */
    private Mob(Sprite sprite) {
        super(sprite);
        setxSpeed(-1);
    }

    @Override
    public void update() {
        if (getX()+getWidth()<=0) {
            setX(world.getWidth());
        }

    }
}

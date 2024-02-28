package isen.content;

import mindustry.content.Fx;
import mindustry.entities.bullet.*;
import mindustry.type.*;

public class IsenUnitTypes {

        public static UnitType miden;

    public void load(){
        miden = new UnitType("miden"){{
            speed = 2f;
            hitSize = 6f;
            health = 150;
            weapons.add(new Weapon("small-mount-weapon"){{
                reload = 13f;
                x = 4f;
                y = 2f;
                top = true;
                ejectEffect = Fx.casing1;
                bullet = new BasicBulletType(3f, 8f){{
                    width = 4f;
                    height = 11f;
                    lifetime = 60f;
                }};
            }});
        }};
    }
}

package isen.content;

import mindustry.content.Fx;
import mindustry.entities.bullet.*;
import mindustry.type.*;

public class IsenUnitTypes {

        public static UnitType miden;

    public void load(){
        miden = new UnitType("miden"){{
            speed = 0.5f;
            hitSize = 8f;
            health = 150;
            weapons.add(new Weapon("large-weapon"){{
                reload = 13f;
                x = 4f;
                y = 2f;
                top = false;
                ejectEffect = Fx.casing1;
                bullet = new BasicBulletType(2.5f, 9){{
                    width = 7f;
                    height = 9f;
                    lifetime = 60f;
                }};
            }});
        }};
    }
}

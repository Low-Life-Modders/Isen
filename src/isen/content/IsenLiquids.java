package isen.content;

import arc.graphics.*;
import mindustry.type.*;

public class IsenLiquids{
    public static Liquid coal, copper, titanium, thorium, lead;

    public static void load(){

        coal = new Liquid("coal", Color.valueOf("474747")){{
            heatCapacity = 0f;
            temperature = 1f;
        }};

        copper = new Liquid("copper", Color.valueOf("cca435")){{
            heatCapacity = 0f;
            temperature = 1f;
        }};

        lead = new Liquid("lead", Color.valueOf("9e75c7")){{
            heatCapacity = 0f;
            temperature = 1f;
        }};

        titanium = new Liquid("titanium", Color.valueOf("cca435")){{
            heatCapacity = 0f;
            temperature = 1f;
        }};

        thorium = new Liquid("thorium", Color.valueOf("ab63bf")){{
            heatCapacity = 0f;
            temperature = 1f;
        }};
    }
}
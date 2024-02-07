package isen.content;

import arc.graphics.*;
import mindustry.type.*;

public class Liquids{
    public static Liquid coal, copper, titanium, thorium, lead;

    public static void load(){

        coal = new Liquid("coal", Color.valueOf("474747")){{
            heatCapacity = 0f;
            temperature = 1f;
            boilPoint = 10f;
        }};

        copper = new Liquid("copper", Color.valueOf("cca435")){{
            
        }};


    }
}
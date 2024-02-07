package isen.content;

import arc.graphics.*;
import arc.struct.*;
import mindustry.type.*;

public class IsenItems{
    public static Item ice;

    public static final Seq<Item> serpuloItems = new Seq<>(), erekirItems = new Seq<>(), erekirOnlyItems = new Seq<>();

    public static void load(){
        ice = new Item("ice", Color.valueOf("d99d73")){{
            hardness = 1;
            cost = 0.5f;
            alwaysUnlocked = false;
        }};
    }

}
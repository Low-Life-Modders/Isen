package isen.world.blocks.environment;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.graphics.*;
import mindustry.graphics.MultiPacker.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.OverlayFloor;

import static mindustry.Vars.*;

/**An overlay ore for a specific item type.*/
public class LiquidOre extends OverlayFloor{


    private Object mapColor;
    private int variants;
    private Liquid liquid;
    private boolean wallOre;

    public LiquidOre(String name, Liquid ore){
        super(name);
        this.localizedName = ore.localizedName;
        this.liquid = ore;
        this.variants = 3;
        ((Color) this.mapColor).set(ore.color);
        this.useColor = true;
    }

    public LiquidOre(Liquid ore){
        this("ore-" + ore.name, ore);
    }

    /** For mod use only!*/
    public LiquidOre(String name){
        super(name);
        this.useColor = true;
        variants = 3;
    }

    public void setup(Liquid ore){
        this.localizedName = ore.localizedName;
        this.liquid = ore;
        ((Color) this.mapColor).set(ore.color);
    }

    @Override
    public void createIcons(MultiPacker packer){
        for(int i = 0; i < variants; i++){
            //use name (e.g. "ore-copper1"), fallback to "copper1" as per the old naming system
            PixmapRegion shadow = Core.atlas.has(name + (i + 1)) ?
                Core.atlas.getPixmap(name + (i + 1)) :
                Core.atlas.getPixmap(itemDrop.name + (i + 1));

            Pixmap image = shadow.crop();

            int offset = image.width / tilesize - 1;
            int shadowColor = Color.rgba8888(0, 0, 0, 0.3f);

            for(int x = 0; x < image.width; x++){
                for(int y = offset; y < image.height; y++){
                    if(shadow.getA(x, y) == 0 && shadow.getA(x, y - offset) != 0){
                        image.setRaw(x, y, shadowColor);
                    }
                }
            }

            packer.add(PageType.environment, name + (i + 1), image);
            packer.add(PageType.editor, "editor-" + name + (i + 1), image);

            if(i == 0){
                packer.add(PageType.editor, "editor-block-" + name + "-full", image);
                packer.add(PageType.main, "block-" + name + "-full", image);
            }
        }
    }

    @Override
    public void init(){
        super.init();

        if(itemDrop != null){
            setup(liquid);
        }else{
            throw new IllegalArgumentException(name + " must have an item drop!");
        }
    }

    @Override
    public String getDisplayName(Tile tile){
        return itemDrop.localizedName;
    }
}
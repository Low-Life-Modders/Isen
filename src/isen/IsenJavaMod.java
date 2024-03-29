package isen;

import arc.*;
import arc.util.*;
import isen.content.IsenLiquids;
import isen.content.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class IsenJavaMod extends Mod{

    public IsenJavaMod(){
        Log.info("Loaded IsenJavaMod constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("behold").row();
                //mod sprites are prefixed with the mod name (this mod is called 'example-java-mod' in its config)
                dialog.cont.image(Core.atlas.find("j")).pad(20f).row();
                dialog.cont.button("I see", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        Log.info("Lo and Behold");
        IsenLiquids.load();
        IsenBlocks.load();
        IsenPlanets.load();
        IsenItems.load();
    }

}

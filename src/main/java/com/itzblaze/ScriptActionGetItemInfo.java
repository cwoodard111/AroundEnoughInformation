package com.itzblaze;

import baritone.BaritoneProvider;
import baritone.api.BaritoneAPI;
import baritone.api.pathing.goals.*;
import net.eq2online.macros.core.settings.Setting;
import net.eq2online.macros.scripting.api.APIVersion;
import net.eq2online.macros.scripting.api.IMacro;
import net.eq2online.macros.scripting.api.IMacroAction;
import net.eq2online.macros.scripting.api.IReturnValue;
import net.eq2online.macros.scripting.api.IScriptActionProvider;
import net.eq2online.macros.scripting.parser.ScriptAction;
import net.eq2online.macros.scripting.parser.ScriptContext;
import net.eq2online.util.Util;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.translation.I18n;

@APIVersion(26)
public class ScriptActionGetItemInfo extends ScriptAction {
    public ScriptActionGetItemInfo() {
        // Context is the context for this action, action name must be lowercase
        super(ScriptContext.MAIN, "getiteminfo");
    }
    @Override
    public boolean isThreadSafe() {
        return false;
    }
    public IReturnValue execute(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String rawParams, String[] params) {
        String expansion = provider.expand(macro,params[0],true);
        int expansionInt = Integer.parseInt(expansion);
        ItemStack item = slotHelper.getSlotStack(expansionInt);
        String unlocalizedname = item.getUnlocalizedName();
        String itemname = I18n.translateToLocal(unlocalizedname + ".name");
        int itemcount = item.getCount();
        String itemdisplay = item.getDisplayName();
        int datavar = item.getMetadata();

        /* here we are storing all of this information into player defined variables.
         */

        provider.setVariable(macro,params[1],itemname);
        provider.setVariable(macro,params[2],itemcount);
        provider.setVariable(macro,params[3],datavar);
        provider.setVariable(macro,params[4],itemdisplay);
        // idk what this last one does but i think its to get nbt
        return null;
    }
    @Override
    public void onInit() {

        this.context.getCore().registerScriptAction(this);
    }

}

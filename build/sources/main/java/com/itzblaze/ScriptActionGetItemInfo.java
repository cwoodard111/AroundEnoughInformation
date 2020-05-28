package com.itzblaze;

import net.eq2online.macros.scripting.api.*;
import net.eq2online.macros.scripting.parser.ScriptAction;
import net.eq2online.macros.scripting.parser.ScriptContext;
import net.minecraft.item.ItemStack;
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
    try {
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
    } catch (NullPointerException e) {
        e.printStackTrace();
    }
        return null;
    }
    @Override
    public void onInit() {

        this.context.getCore().registerScriptAction(this);
    }

}

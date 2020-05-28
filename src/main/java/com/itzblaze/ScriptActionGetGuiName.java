package com.itzblaze;

import net.eq2online.macros.scripting.api.*;
import net.eq2online.macros.scripting.parser.ScriptAction;
import net.eq2online.macros.scripting.parser.ScriptContext;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;

import static net.minecraft.client.Minecraft.getMinecraft;

@APIVersion(26)
public class ScriptActionGetGuiName extends ScriptAction  {
    public ScriptActionGetGuiName() {
        super(ScriptContext.MAIN, "getguiname");
    }
    @Override
    public boolean isThreadSafe() {
        return false;
    }
    public IReturnValue execute(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String rawParams, String[] params) {
        try {
            EntityPlayerSP player = getMinecraft().player;
            if (player.openContainer instanceof ContainerChest) {
                ContainerChest chest = (ContainerChest) player.openContainer;
                IInventory inv = chest.getLowerChestInventory();
                String chestName = inv.getDisplayName().getFormattedText();
                provider.setVariable(macro, params[0], chestName);
            }
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

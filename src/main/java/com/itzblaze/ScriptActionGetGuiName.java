package com.itzblaze;

import net.eq2online.macros.scripting.api.*;
import net.eq2online.macros.scripting.parser.ScriptAction;
import net.eq2online.macros.scripting.parser.ScriptContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.text.ITextComponent;

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
        EntityPlayerSP player = getMinecraft().player;
        if (player.openContainer instanceof ContainerChest) {
            ContainerChest chest = (ContainerChest)player.openContainer;
            IInventory inv = chest.getLowerChestInventory();
            String chestName = inv.getDisplayName().toString();
            provider.setVariable(macro,params[0],chestName);
        }

        return null;
    }
    @Override
    public void onInit() {

        this.context.getCore().registerScriptAction(this);
    }
}

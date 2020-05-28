package com.itzblaze;

import net.eq2online.macros.scripting.api.*;
import net.eq2online.macros.scripting.parser.ScriptAction;
import net.eq2online.macros.scripting.parser.ScriptContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;
@APIVersion(26)
public class ScriptActionInvFull extends ScriptAction {
        public ScriptActionInvFull() {

            super(ScriptContext.MAIN, "invfull");
        }
        @Override
        public boolean isThreadSafe() {

            return false;
        }
    public boolean executeConditional(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String rawParams, String[] params) {
        InventoryPlayer playerinv = Minecraft.getMinecraft().player.inventory;
        if (!playerinv.mainInventory.stream().anyMatch(stack -> stack.isEmpty())) {
            return true;
        } else {
           return false;
        }
    }
    public void onInit() {

        this.context.getCore().registerScriptAction(this);
    }
}

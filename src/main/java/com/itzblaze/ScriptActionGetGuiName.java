package com.itzblaze;

import net.eq2online.macros.scripting.api.IMacro;
import net.eq2online.macros.scripting.api.IMacroAction;
import net.eq2online.macros.scripting.api.IReturnValue;
import net.eq2online.macros.scripting.api.IScriptActionProvider;
import net.eq2online.macros.scripting.parser.ScriptAction;
import net.eq2online.macros.scripting.parser.ScriptContext;
import net.minecraft.inventory.IInventory;

public class ScriptActionGetGuiName extends ScriptAction  {
    public ScriptActionGetGuiName() {
        super(ScriptContext.MAIN, "getguiname");
    }
    @Override
    public boolean isThreadSafe() {
        return false;
    }
    public IReturnValue execute(IScriptActionProvider provider, IMacro macro, IMacroAction instance, String rawParams, String[] params) {
        IInventory inventory = null;
        String invname = inventory.getName();
        provider.setVariable(macro,params[0],invname);
        return null;
    }
    @Override
    public void onInit() {

        this.context.getCore().registerScriptAction(this);
    }
}

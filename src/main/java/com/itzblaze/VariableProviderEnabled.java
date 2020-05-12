package com.itzblaze;

import net.eq2online.macros.scripting.api.IVariableProvider;
import net.eq2online.macros.scripting.parser.ScriptContext;
import net.eq2online.macros.scripting.variable.VariableCache;

public class VariableProviderEnabled extends VariableCache {
    @Override
    public Object getVariable(String variableName) {
        System.out.println("Start");
        return null;
    }

    public void onInit() {
        storeVariable("AEI",true);
        ScriptContext.MAIN.getCore().registerVariableProvider((IVariableProvider)this);
    }
}

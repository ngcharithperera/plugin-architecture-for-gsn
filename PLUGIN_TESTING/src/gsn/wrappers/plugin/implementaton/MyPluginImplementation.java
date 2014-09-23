package gsn.wrappers.plugin.implementaton;

import gsn.wrappers.plugin.MyPlugin;
import net.xeoh.plugins.base.Plugin;
import net.xeoh.plugins.base.annotations.PluginImplementation;


@PluginImplementation
public class MyPluginImplementation implements MyPlugin {
		
	public String sayHello() {
		return "This is Charith";
	}
}

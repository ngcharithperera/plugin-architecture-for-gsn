package com.plugintest;

import gsn.wrappers.plugin.MyPlugin;

import java.io.File;

import net.xeoh.plugins.base.PluginManager;
import net.xeoh.plugins.base.impl.PluginManagerFactory;
import net.xeoh.plugins.base.util.uri.ClassURI;

public class Boss {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Hello");
		PluginManager pm = PluginManagerFactory.createPluginManager();
		pm.addPluginsFrom(ClassURI.CLASSPATH);
	//	pm.addPluginsFrom(new File("plugins/").toURI());

		MyPlugin cool = pm.getPlugin(MyPlugin.class);
		System.out.println(cool.sayHello());

	}

}

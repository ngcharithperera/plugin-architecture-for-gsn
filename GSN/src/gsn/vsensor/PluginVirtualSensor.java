/**
 * Global Sensor Networks (GSN) Source Code
 * Copyright (c) 2006-2014, Ecole Polytechnique Federale de Lausanne (EPFL)
 * 
 * This file is part of GSN.
 * 
 * GSN is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 * 
 * GSN is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with GSN.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * File: src/gsn/vsensor/BridgeVirtualSensor.java
 *
 * @author Ali Salehi
 * @author Mehdi Riahi
 * @author Sofiane Sarni
 *
 */

package gsn.vsensor;

import gsn.beans.StreamElement;
import gsn.beans.VSensorConfig;
import gsn.vsensor.plugin.VirtualSensorPlugin;
import gsn.wrappers.plugin.WrapperPlugin;
import net.xeoh.plugins.base.PluginManager;
import net.xeoh.plugins.base.impl.PluginManagerFactory;

import org.apache.log4j.Logger;

import java.io.File;
import java.util.TreeMap;

public class PluginVirtualSensor extends AbstractVirtualSensor {

	private static final transient Logger logger = Logger
			.getLogger(PluginVirtualSensor.class);
	private boolean allow_nulls = true; // by default allow nulls
	private TreeMap<String, String> params;
	private VSensorConfig vsensor;
	private String PluginName;
	public VirtualSensorPlugin virtualSensorPlugin;
	
	public boolean initialize() {
		vsensor = getVirtualSensorConfiguration();
		params = vsensor.getMainClassInitialParams();
		PluginName = params.get("plugin_name");
	    PluginManager pm = PluginManagerFactory.createPluginManager();
		pm.addPluginsFrom(new File("virtual-sensor-plugin-repository/"+PluginName+".jar").toURI());	
		virtualSensorPlugin = pm.getPlugin(VirtualSensorPlugin.class);
		virtualSensorPlugin.setParameters(params);
		String allow_nulls_str = params.get("allow-nulls");
		if (allow_nulls_str != null)
			allow_nulls = allow_nulls_str.equalsIgnoreCase("true");

		return true;
	}

	public void dataAvailable(String inputStreamName, StreamElement data) {
		if (allow_nulls) {
			dataProduced(virtualSensorPlugin.getAnalysedData(inputStreamName, data));
		} else {
			if (!areAllFieldsNull(data))
				dataProduced(data);
			else {
				if (logger.isDebugEnabled())
					logger.debug("Nulls received for timestamp ("
							+ data.getTimeStamp() + "), discarded");
			}
		}
		if (logger.isDebugEnabled())
			logger.debug("Data received under the name: " + inputStreamName);
	}

	public boolean areAllFieldsNull(StreamElement data) {
		boolean allFieldsNull = false;
		for (int i = 0; i < data.getData().length; i++)
			if (data.getData()[i] == null) {
				allFieldsNull = true;
				break;
			}

		return allFieldsNull;
	}

	public void dispose() {

	}

}

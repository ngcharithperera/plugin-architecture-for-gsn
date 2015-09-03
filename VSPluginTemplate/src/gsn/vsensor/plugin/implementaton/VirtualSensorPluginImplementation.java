/*************************************************************
 * The variable 'param' allows to access any information     *
 * that is passed  by VSD to the VS.                         *
 *                                                           *
 * This project is intended to act as a templates to develop *
 * new virtual sensor plugins.                               *        
 *                                                           *
 * Copyright to Charith Perera                               *
 * **********************************************************/


package gsn.vsensor.plugin.implementaton;

import java.util.TreeMap;

import gsn.beans.StreamElement;
import gsn.vsensor.plugin.VirtualSensorPlugin;
import net.xeoh.plugins.base.annotations.PluginImplementation;

@PluginImplementation
public class VirtualSensorPluginImplementation implements VirtualSensorPlugin {

	StreamElement  streamElement;
	TreeMap<String, String> params;
	public StreamElement getAnalysedData(String inputStreamName,
			StreamElement data){
		streamElement = data;		
		//TODO: Process data as much as needed. 
		// You may call external API from here (Step 1)
		return streamElement;
	}
	@Override
	public void setParameters(TreeMap<String, String> params) {
		this.params = params;	
		//TODO: Access any parameters passed from VSD (Step 2)
	}
}

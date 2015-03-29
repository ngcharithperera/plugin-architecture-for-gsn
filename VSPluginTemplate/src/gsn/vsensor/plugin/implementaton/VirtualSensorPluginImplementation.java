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
		System.out.println(params.toString());
		return streamElement;
	}
	@Override
	public void setParameters(TreeMap<String, String> params) {
		this.params = params;	
		
	}
}

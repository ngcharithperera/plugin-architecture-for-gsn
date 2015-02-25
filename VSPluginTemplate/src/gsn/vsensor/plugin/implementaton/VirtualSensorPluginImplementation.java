package gsn.vsensor.plugin.implementaton;

import java.io.Serializable;

import gsn.beans.DataField;
import gsn.beans.StreamElement;
import gsn.vsensor.plugin.VirtualSensorPlugin;
import net.xeoh.plugins.base.Plugin;
import net.xeoh.plugins.base.annotations.PluginImplementation;

@PluginImplementation
public class VirtualSensorPluginImplementation implements VirtualSensorPlugin {

	StreamElement  se;
	public StreamElement getAnalysedData(String inputStreamName,
			StreamElement data){
		se = data;
		return se;
	}
}

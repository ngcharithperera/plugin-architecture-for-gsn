package gsn.wrappers.plugin.implementaton;

import java.io.Serializable;

import gsn.beans.AddressBean;
import gsn.beans.DataField;
import gsn.wrappers.plugin.WrapperPlugin;
import net.xeoh.plugins.base.Plugin;
import net.xeoh.plugins.base.annotations.PluginImplementation;

@PluginImplementation
public class WrapperPluginImplementation implements WrapperPlugin {
	private AddressBean params;
	@Override
	public DataField[] getCollection() {
		DataField[] collection = new DataField[] {
				new DataField("packet_type", "int", "packet type"),
				new DataField("temperature", "double",
						"Presents the temperature sensor."),
				new DataField("light", "double", "Presents the light sensor.") };
		return collection;
	}

	@Override
	public Serializable[] getData() {
	    Double light = 0.0, temperature = 0.0;
	    int packetType = 0;
	    
	    light = ((int) (Math.random() * 10000)) / 10.0;
	    temperature = ((int) (Math.random() * 1000)) / 10.0;
	    packetType = 2;
		return new Serializable[] { packetType, temperature, light };
	}

	@Override
	public void setParameters(AddressBean params) {
		this.params = params;		
	}
	
	
	

}

package gsn.vsensor.plugin;

import java.io.Serializable;

import gsn.beans.DataField;
import gsn.beans.StreamElement;
import net.xeoh.plugins.base.Plugin;

public interface VirtualSensorPlugin extends Plugin {
	public DataField[] getCollection();
	public Serializable[] getData();
	public StreamElement getAnalysedData(String inputStreamName,
			StreamElement data);
}


package gsn.vsensor.plugin;

import gsn.beans.StreamElement;
import net.xeoh.plugins.base.Plugin;

public interface VirtualSensorPlugin extends Plugin {
	public StreamElement getAnalysedData(String inputStreamName,
			StreamElement data);
}

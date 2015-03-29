package gsn.vsensor.plugin;

import java.util.TreeMap;

import gsn.beans.StreamElement;
import net.xeoh.plugins.base.Plugin;

public interface VirtualSensorPlugin extends Plugin {
	public StreamElement getAnalysedData(String inputStreamName,
			StreamElement data);
	public void setParameters(TreeMap<String, String> params);
}


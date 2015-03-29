package gsn.wrappers.plugin;

import java.io.Serializable;

import gsn.beans.AddressBean;
import gsn.beans.DataField;
import net.xeoh.plugins.base.Plugin;

public interface WrapperPlugin extends Plugin {
	public DataField[] getCollection();
	public Serializable[] getData();
	public void setParameters(AddressBean params);
}


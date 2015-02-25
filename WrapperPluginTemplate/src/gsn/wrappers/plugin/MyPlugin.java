package gsn.wrappers.plugin;

import java.io.Serializable;

import gsn.beans.DataField;
import net.xeoh.plugins.base.Plugin;

public interface MyPlugin extends Plugin {
	public DataField[] getCollection();
	public Serializable[] getData();
}

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
* File: src/gsn/wrappers/MultiFormatWrapper.java
*
* @author sp3dy
* @author Ali Salehi
* @author Mehdi Riahi
*
*/

package gsn.wrappers;

import gsn.beans.AddressBean;
import gsn.beans.DataField;
import gsn.wrappers.plugin.MyPlugin;

import java.io.File;
import java.io.Serializable;

import net.xeoh.plugins.base.PluginManager;
import net.xeoh.plugins.base.impl.PluginManagerFactory;

import org.apache.log4j.Logger;

/**
 * This wrapper presents a MultiFormat protocol in which the data comes from the
 * system clock. Think about a sensor network which produces packets with
 * several different formats. In this example we have 3 different packets
 * produced by three different types of sensors. Here are the packet structures
 * : [temperature:double] , [light:double] , [temperature:double, light:double]
 * The first packet is for sensors which can only measure temperature while the
 * latter is for the sensors equipped with both temperature and light sensors.
 * 
 */
public class PluginWrapper extends AbstractWrapper {
  private DataField[] collection = new DataField[] { new DataField("packet_type", "int", "packet type"),
      new DataField("temperature", "double", "Presents the temperature sensor."), new DataField("light", "double", "Presents the light sensor.") };
  private final transient Logger logger = Logger.getLogger(PluginWrapper.class);
  private int counter;
  private AddressBean params;
  private long rate = 1000;
  private String PluginName;
  public MyPlugin cool;

  public boolean initialize() {
    setName("MultiFormatWrapper" + counter++);
    
    params = getActiveAddressBean();
    
    if ( params.getPredicateValue( "rate" ) != null ) {
      rate = (long) Integer.parseInt( params.getPredicateValue( "rate"));
      
      logger.info("Sampling rate set to " + params.getPredicateValue( "rate") + " msec.");
    }
    PluginName = params.getPredicateValue( "plugin_name");
    
    PluginManager pm = PluginManagerFactory.createPluginManager();
	pm.addPluginsFrom(new File("plugins/"+PluginName+".jar").toURI());
	cool = pm.getPlugin(MyPlugin.class);
	
	collection = cool.getCollection();
	cool.setParameters(params);
	
    return true;
  }

  public void run() {
	  
	  

    
    while (isActive()) {
      try {
        // delay 
        Thread.sleep(rate);
      } catch (InterruptedException e) {
        logger.error(e.getMessage(), e);
      }
      


      // post the data to GSN
      postStreamElement(cool.getData());       
    }
  }

  public DataField[] getOutputFormat() {
    return collection;
  }

  public String getWrapperName() {
    return "MultiFormat Sample Wrapper";
  }  

  public void dispose() {
    counter--;
  }
}

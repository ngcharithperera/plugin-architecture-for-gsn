# Plugin Architecture for GSN

Java Simple Plugin Framework used to build the plugin architecture (https://code.google.com/p/jspf/).

Involves three (3) Projects:

1. PURE GSN + Extended Plugins Support
1. Wrapper Plugin Project
1. Virtual Sensor Plugin Project

Step-by-step process

1. Create two folders in PURE GSN project
       * wrapper-plugin-repository
       * virtual-sensor-plugin-repository
1. Inside *gsn.wrappers* package -> created a *PluginWrapper*
       * *PluginWrapper* is based on typical structure. 
       * In initialize() method, it uses parameters grabbed from VSD to grab the plugin name (using property 'plugin_name').
       * Then it uses *jspf* libraries to initialize the plugin.
1. Inside *gsn.vsensor* package -> created a *PluginVirtualSensor*
       * In initialize() method, it uses parameters grabbed from VSD to grab the plugin name (using property 'plugin_name').
                     * processing-class --> init-params -->  param
1. Created a new package called *gsn.wrappers.plugin*
       * Inside it, created a interface *WrapperPlugin*
1. Created a new package called *gsn.vsensor.plugin*
       * Inside it, created a interface *VirtualSensorPlugin*

1. *PluginWrapper* passes values it retrieved from VSD into Plugin jar.
1. *PluginVirtualSensor* passes values it retrieved from VSD into Plugin jar.
1. Register: *gsn_plugins* = *gsn.wrappers.PluginWrapper*




1. Normal GSN with PluginWrapper + Plugin folder
1. Plugin project that works as a template to create new plugins. In eclipse --> export --> create normal jar --> save it to GSN's plugins folder at run time

jspf libraries (Jars) should be attached to both projects.

* In GSN, first created a PluginWrapper and added it to conf/wrapper.properties.
* In the virtual sensor definition file (VSD), name of the jar file need to me mentioned

Some important files such as 1) DataField (gsn.beans), 2) DataTypes (gsn.beans), and 3) GSNRuntimeException (gsn.utils)

*Note: PURE GSN and GSN4MOSDEN are incompatible with each other. In any system that requires multiple GSN instances use wither all PUREE_GSN or GSN4MOSDEN. GSN4MOSDEN  Supports Android Communication. The difference between to is 'StreamElement4Rest'. Always check, what version you are using.*


Plugin version should be lower than GSN version (Java Compiling) 

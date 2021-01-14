package homework7;


import homework7.firstPlugin.Plugin;
import homework7.firstPlugin.PluginImpl;
import org.junit.Assert;
import org.junit.Test;

public class PluginManagerTest {


    @Test
    public void loadPlugin(){
        PluginManager pluginManager = new PluginManager
                ("C:\\Users\\User\\IdeaProjects\\homework\\homework\\target\\classes\\homework7");
        Plugin plugin = pluginManager.load("firstPlugin", "homework7.firstPlugin.PluginImpl");
        Assert.assertNotNull(plugin);
        plugin.doUsefull();
    }

}
package ClassLoaderFunc;


import ClassLoaderFunc.firstPlugin.Plugin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginManager {
    private final String pluginRootDirectory;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public Plugin load(String pluginName, String pluginClassName) {
        String directory = pluginRootDirectory + "\\" + pluginName;
        try {
            URLClassLoader urlClassLoader = getURLClassLoader(directory, true);
            Class<?> plugin = urlClassLoader.loadClass(pluginClassName);
            return (Plugin) plugin.newInstance();

        } catch (MalformedURLException e) {
            System.out.println("A wrong directory");
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException | InstantiationException e) {
            System.out.println("The class is not found");
        }

        return null;
    }

    private URLClassLoader getURLClassLoader(String directory, boolean parent)
            throws MalformedURLException {
        File dir = new File(directory);
        URLClassLoader urlClassLoader;
        if (!parent) {
            // PluginManager will load a class
            urlClassLoader = new URLClassLoader(new URL[]{dir.toURL()}, null);
        } else {
            // Delegation
            urlClassLoader = new URLClassLoader(new URL[]{dir.toURL()});
        }
        return urlClassLoader;
    }
}

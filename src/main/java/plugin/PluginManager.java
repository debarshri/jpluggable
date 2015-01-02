package plugin;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class PluginManager {
    private static final String PLUGIN_PATH = "plugins/";
    private List<Plugin> plugins = new ArrayList<Plugin>();

    /**
     * Load plugin descriptors from a directory location
     * and then load plugin classes.
     * @return
     * @throws Exception
     */
    public List<Plugin> loadPlugins() throws Exception{

        File filePath = new File(PLUGIN_PATH);
        File files [] = filePath.listFiles();

//Iterate over files in the plugin directory

        for(File file:files){
            if(file.isFile()){
                FileInputStream fstream = new FileInputStream(file);
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                //read fully qualified class name of plugin from plugin descriptor file
                String fullyQualifiedName = br.readLine();
                in.close();

                // Convert File to a URL
                URI uri = URI.create("file:/"+file.getAbsolutePath());


                URL url = uri.toURL();
                URL[] urls = new URL[]{url};

                System.out.println(url);


                // Create a new class loader with the directory
                ClassLoader loader = new URLClassLoader(urls);
                Class cls = loader.loadClass(fullyQualifiedName);

                //add loaded plugin to plugin list
                plugins.add((Plugin)cls.newInstance());

            }else {
                //skip folders
                continue;
            }
        }
        return plugins;
    }
}

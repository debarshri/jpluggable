package registry;

import plugin.Plugin;
import plugin.PluginManager;

import java.util.List;

public class Scheduler {

    public Scheduler()
    {
        PluginManager manager = new PluginManager();

        try {
            List<Plugin> plugins = manager.loadPlugins();

            for(Plugin plugin : plugins){
                plugin.run();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String arg[]){
        new Scheduler();
    }
}

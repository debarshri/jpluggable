package plugin;

public class TestPlugin implements Plugin {
    @Override
    public String name() {
        return "Test";
    }

    @Override
    public String description() {
        return "Test";
    }

    @Override
    public void run() {

        System.out.println("Test....");
    }
}

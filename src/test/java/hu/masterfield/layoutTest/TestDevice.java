package hu.masterfield.layoutTest;

import org.openqa.selenium.Dimension;

import java.util.List;

class TestDevice {

    private final String name;
    private final Dimension screenSize;
    private final List<String> tags;

    public TestDevice(String name, Dimension screenSize, List<String> tags) {
        this.name = name;
        this.screenSize = screenSize;
        this.tags = tags;
    }

    public Dimension getScreenSize() {
        return screenSize;
    }

    public List<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TestDevice [");
        if (name != null) {
            builder.append("name=");
            builder.append(name);
        }
        builder.append("]");
        return builder.toString();
    }
}

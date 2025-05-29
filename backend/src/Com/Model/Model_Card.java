
package Com.Model;

import javax.swing.Icon;

public class Model_Card {
    String title;
    double values;
    Icon icon;

    public Model_Card(String title, double values, Icon icon) {
        this.title = title;
        this.values = values;
        this.icon = icon;
    }

    public Model_Card() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getValues() {
        return values;
    }

    public void setValues(double values) {
        this.values = values;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
    
    
}

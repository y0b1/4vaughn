public class Battery {
    private int percentage;
    private boolean isActive;

    // Constructor
    public Battery(int initialPercentage) {
        if(initialPercentage < 0 || initialPercentage > 100) {
            this.percentage = 100;
        } else {
            this.percentage = initialPercentage;
        }
        this.isActive = false;
    }

    // Observers
    public int getPercentage() {
        return percentage;
    }

    public boolean isFullyCharged() {
        return percentage == 100;
    }

    public boolean isEmpty() {
        return percentage == 0;
    }

    public String displayStatus() {
        return "Battery at " + percentage + "%";
    }

    public boolean isActive() {
        return isActive;
    }

    // Transformers
    public void chargeByOne() {
        if(percentage < 100) {
            percentage++;
        }
    }

    public void dischargeByOne() {
        if(isActive && percentage > 0) {
            percentage--;
        }
    }

    public void turnOn() {
        isActive = true;
    }

    public void turnOff() {
        isActive = false;
    }

    public String displayVisualRepresentation() {
        int fullBars = percentage / 5;
        StringBuilder bars = new StringBuilder();
        for (int i = 0; i < fullBars; i++) {
            bars.append("/");
        }
        return bars.toString();
    }
}

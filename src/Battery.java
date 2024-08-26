public class Battery {
    private int percentage;

    public Battery(int initialPercentage) {
        if (initialPercentage >= 0 && initialPercentage <= 100) {
            this.percentage = initialPercentage;
        } else {
            this.percentage = 100;
        }
    }

    public int getPercentage() {
        return percentage;
    }

    public void chargeByOne() {
        if (percentage < 100) {
            percentage++;
        }
    }

    public void dischargeByOne() {
        if (percentage > 0) {
            percentage--;
        }
    }

    public boolean isFullyCharged() {
        return percentage == 100;
    }

    public boolean isEmpty() {
        return percentage == 0;
    }

    public String displayStatus() {
        return "Battery: " + percentage + "%";
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

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

    public void chargeToFull() {
        percentage = 100;
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
}

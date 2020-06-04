
public class Wagon {
    private Boolean brokenTongue;
    private Boolean[] brokenAxle;
    private Boolean[] brokenWheel;

    public Wagon() {
        this.brokenTongue = false;
        this.brokenAxle = new Boolean[2];
        this.brokenAxle[0] = false;
        this.brokenAxle[1] = false;
        this.brokenWheel = new Boolean[4];
        for(int i = 0; i < 4; i++) {
            this.brokenWheel[i] = false;
        }
    }

    public void setTongue(Boolean status) {
        this.brokenTongue = status;
    }

    public void setAxle(Boolean status, int i) {
        this.brokenAxle[i] = status;
    }

    public void setWheel(Boolean status, int i) {
        this.brokenWheel[i] = status;
    }

    public Boolean getTongue() {
        return this.brokenTongue;
    }

    public Boolean getAxle(int i) {
        return this.brokenAxle[i];
    }

    public Boolean getWheel(int i) {
        return this.brokenWheel[i];
    }
}
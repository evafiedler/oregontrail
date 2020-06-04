
public class Char {
    private String job;
    private int age;
    private String name;
    private String[] jobs = {"Doctor", "Farmer", "Banker", "Factory Worker", "Carpenter", "None"};
    private Boolean isSick;
    private String disease;
    private Boolean isDead;


    public Char(int job, int age, String name) {
        this.job = jobs[job];
        this.age = age;
        this.name = name;
        this.isSick = false;
        this.isDead = false;
    }
    
    public String getName() {
        return this.name;
    }

    public String getJob() {
        return this.job;
    }

    public int getAge() {
        return this.age;
    }

    public Boolean getSick() {
        return this.isSick;
    }

    public String getDisease() {
        return this.disease;
    }

    public Boolean getDead() {
        return this.isDead;
    }

    public void setSick(Boolean status) {
        this.isSick = status;
    }

    public void setDisease(int num) {
        String[] diseases = {"Dysentery", "Cholera", "Typhoid", "Tuberculosis"};
        this.disease = diseases[num];
    }

    public void setDead() {
        this.isDead = true;
    }
}
import java.util.TimerTask;
import java.util.Random;

public class Time extends TimerTask {
    
    private int count;
    private Random r;
    private int dayOf;
    private Prompt p;

    public Time(int c) {
        count = c;
        r = new Random();
        this.p = new Prompt();
    }
    
    public void run() {
        if(this.p.one.numDead == 4) {
            System.out.println("All members of your wagon have perished. You still had " +
             this.p.one.miles + " to go. Better luck next time!");
            this.cancel();
        }

        if(this.count % 10 == 1) {
            chooseDay(this.count);
        }

        System.out.println("Day " + this.count + ", Miles Remaining: " + this.p.one.miles + ", Food Remaining: " +
         this.p.one.food + ", Money Remaining: " + this.p.one.money);

        if(this.count == this.dayOf) {
            chooseEvent();
        }

        this.p.one.food = this.p.one.food - this.p.one.perDayFood;
        this.p.one.miles = this.p.one.miles - this.p.one.perDayMiles;

        if(this.p.one.miles <= 0) {
            this.p.finished();
            this.cancel();
        }

        if(this.p.one.food <= 0) {
            this.p.outOfFood();
        }

        if(this.p.one.food <= 0) {
            System.out.println("Your wagon party has starved to death. You failed");
            this.cancel();
        }
        this.count++;
    }

    public int getCount() {
        return this.count;
    }

    public void chooseDay(int range) {
        this.dayOf = r.nextInt(10) + range;
    }

    public void chooseEvent() {
        int choice = r.nextInt(4);
        if(choice == 0) {
            this.p.wagonEvent();
        } else if(choice == 1) {
            this.p.illnessEvent();
        } else if(choice == 2){
            this.p.deathEvent();
        } else {
            this.p.foodEvent();
        }
    }
}
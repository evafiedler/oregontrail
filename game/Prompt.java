import java.util.Scanner;
import java.util.Random;

public class Prompt {

    Scanner sc = new Scanner(System.in);
    String[] jobs = {"Doctor", "Farmer", "Banker", "Factory Worker", "Carpenter"};
    Random r = new Random();
    Player one;
    Char leader;

    public Prompt() {
        this.one = new Player(4);
        this.leader = this.start();
        this.one.party.addMember(this.leader);

        for(int i = 0; i < 3; i++) {
            one.party.addMember(this.createChar());   
        }

    }

    public Char start() {
        System.out.println("Welcome to the Oregon Trail!");

        System.out.println("First you must make a wagon leader. What will your wagon leader's name be?");
        String name = sc.nextLine();

        System.out.println("What is the age of your wagon leader?");
        int age = sc.nextInt();

        System.out.println("Now you must choose the occupation of your wagon leader. Select the number of the job of your wagon leader");
        for(int i = 0; i < 5; i++) {
            System.out.println(i + ". A " + this.jobs[i]);
        }
        int job = sc.nextInt();
        sc.nextLine();

        Char character = new Char(job, age, name);
        if(job == 0) {
            this.one.food = 1000;
            this.one.money = 1200;
            this.one.perDayFood = 15;
            this.one.perDayMiles = 20;
        }else if(job == 1) {
            this.one.food = 1200;
            this.one.money = 1000;
            this.one.perDayFood = 15;
            this.one.perDayMiles = 20;
        }else if(job == 2) {
            this.one.food = 1000;
            this.one.money = 1200;
            this.one.perDayFood = 15;
            this.one.perDayMiles = 20;
        }else if(job == 3) {
            this.one.food = 1000;
            this.one.money = 800;
            this.one.perDayFood = 13;
            this.one.perDayMiles = 20;
        }else if(job == 4) {
            this.one.food = 1000;
            this.one.money = 1000;
            this.one.perDayFood = 15;
            this.one.perDayMiles = 20;
        }
        return character;
    }

    public Char createChar() {
        System.out.println("Let's make another member for your wagon party!");

        System.out.println("What will their name be?");
        String name = sc.nextLine();
        
        System.out.println("What will their age be?");
        int age = sc.nextInt();
        sc.nextLine();

        Char character = new Char(5, age, name);
        return character;
    }

    public void wagonEvent() {
        Boolean broken = false;
        int choice = 0;
        String res;
        int chance;
        while(!broken) {
            choice = r.nextInt(3);
            if(choice == 0) {
                if(!this.one.wagon.getTongue()) {
                    this.one.wagon.setTongue(true);
                    System.out.println("Broken wagon tongue");
                    broken = true;
                }
            } else if(choice == 1) {
                for(int i = 0; i < 2; i++) {
                    if(!this.one.wagon.getAxle(i)) {
                        this.one.wagon.setAxle(true, i);
                        System.out.println("Broken wagon axle");
                        broken = true;
                        break;
                    }
                }
            } else if(choice == 2) {
                for(int i = 0; i < 4; i++) {
                    if(!this.one.wagon.getWheel(i)) {
                        this.one.wagon.setWheel(true, i);
                        System.out.println("Broken wagon wheel");
                        broken = true;
                        break;
                    }
                }
            }
        }

        if(this.one.party.members[0].getJob().equals("Carpenter") && !this.one.party.members[0].getDead()) {
            System.out.println("As a carpenter, you can attempt to fix your wagon yourself for a lower price of $10. Would you like to do this? Please answer yes or no");
            res = sc.nextLine();
            if(res.equals("yes")) {
                chance = r.nextInt(5);
                if(chance < 4) {
                    System.out.println("Congrats! You have successfully fixed your wagon!");
                    this.one.money-=10;
                    this.fixWagon(choice);
                } else {
                    System.out.println("Unfortunately, you were unable to fix your wagon. You will have to pay someone else to do it");
                }
            }
        }

        System.out.println("Would you like to pay $100 to have your wagon fixed? Please answer yer or no");
        res = sc.nextLine();
        if(res.equals("yes")) {
            this.fixWagon(choice);
            this.one.money-=100;
        } 
    }

    public void illnessEvent() {
        String[] illnesses = {"Dysentery", "Cholera", "Typhoid", "Tuberculosis"};
        int illness = r.nextInt(4);
        Boolean dead = true;
        int member = 0;
        while(dead){
            member = r.nextInt(4);
            dead = this.one.party.members[member].getDead();
        }
        System.out.println(member);
        int age = this.one.party.members[member].getAge();
        int prob = 0;
        if(age < 5) {
            prob = 5;
        } else if(age >= 5 && age < 30) {
            prob = 3;
        } else if(age >= 30 && age < 50) {
            prob = 7;
        } else if(age >= 50) {
            prob = 10;
        }
        int rand = r.nextInt(15);
        if((prob + rand) >= 15) {
            System.out.println(this.one.party.members[member].getName() + " has " + illnesses[illness]);
            this.one.party.members[member].setSick(true);
            this.one.party.members[member].setDisease(illness);
            this.healDisease(illnesses[illness], member);
        } else {
            System.out.println(this.one.party.members[member].getName() + " had a cold but recovered quickly"); 
        }
    }

    public void deathEvent() {
        Boolean dead = true;
        int member = 0;
        while(dead){
            member = r.nextInt(4);
            dead = this.one.party.members[member].getDead();
        }
        System.out.println(member);
        int prob = 0;
        int age = this.one.party.members[member].getAge();
        String[] events = {"ate poison berries", "was eaten by a bear", "fell off a cliff", "was run over by wild horses",
         "was crushed by a falling boulder"};
        String death = events[r.nextInt(5)];
        if(this.one.party.members[member].getSick()) {
            if(age < 5) {
                prob = 7;
            } else if(age >= 5 && age < 30) {
                prob = 3;
            } else if(age >= 30 && age < 50) {
                prob = 7;
            } else if(age >= 50) {
                prob = 10;
            }
            int rand = r.nextInt(15);
            if((prob + rand) >= 13) {
                System.out.println(this.one.party.members[member].getName() + " has died from " +
                 this.one.party.members[member].getDisease());
                this.one.party.members[member].setDead(); 
                this.one.numDead++;
            } else {
                System.out.println(this.one.party.members[member].getName() + " has recovered from " + 
                 this.one.party.members[member].getDisease());
                this.one.party.members[member].setSick(false);
            }    
        } else {
            System.out.println(this.one.party.members[member].getName() + " " + death + " and died");
            this.one.party.members[member].setDead();
            this.one.numDead++;
        }
    }

    public void foodEvent() {
        int c = r.nextInt(4);
        if(c == 0) {
            System.out.println("You found a berry bush by the side of the road!");
            this.one.food+=25;
        }else if(c == 1) {
            System.out.println("You found 3 crates of food by the side of the road!");
            this.one.food+=100;
        }else if(c == 2) {
            System.out.println("You found a freshly slaughtered pig by the side of the road!");
            this.one.food+=70;
        }else if(c == 3) {
            System.out.println("You found freshly cooked stew by the side of the road!");
            this.one.food+=30;
        }
        
    }

    public void outOfFood() {
        String response;
        int amt;
        System.out.println("You are out of food!");
        if(this.one.party.members[0].getJob().equals("Farmer") && !this.one.party.members[0].getDead()) {
            System.out.println("Would you like to search for food with your farmer skills? Please answer yes or no");
            response = sc.nextLine();
            if(response.equals("yes")) {
                amt = r.nextInt(100);
                System.out.println("You found " + amt + " pounds of food");
                this.one.food+=amt;
            }
    
        }

        System.out.println("Would you like to replenish your food for $250? Please answer yes or no");
        response = sc.nextLine();
        if(response.equals("yes")) {
            if(this.one.money < 250) {
                System.out.println("You don't have enough money to make this purchase");
            } else {
                this.one.money-=250;
                this.one.food = 500;
            }
        }  

    }

    public void fixWagon(int choice) {
        if(choice == 0) {
            this.one.wagon.setTongue(false);
            System.out.println("Fixed wagon tongue");
        } else if(choice == 1) {
            for(int i = 0; i < 2; i++) {
                if(this.one.wagon.getAxle(i)) {
                    this.one.wagon.setAxle(false, i);
                    System.out.println("Fixed wagon axle");
                    break;
                }
            }
        } else if(choice == 2) {
            for(int i = 0; i < 4; i++) {
                if(this.one.wagon.getWheel(i)) {
                    this.one.wagon.setWheel(false, i);
                    System.out.println("Fixced wagon wheel");
                    break;
                }
            }
        }
    }

    public void healDisease(String disease, int member) {
        String res;
        int chance;
        if(this.one.party.members[0].getJob().equals("Doctor") && !this.one.party.members[0].getDead()) {
            System.out.println("Would you like to use your doctor skills to try to heal " +
             this.one.party.members[member].getName() + " for the reduced price of $50? Please answer yes or no");
            res = sc.nextLine();
            if(res.equals("yes")) {
                this.one.money-=50;
                chance = r.nextInt(10);
                if(chance < 4) {
                    System.out.println("You have cured " + this.one.party.members[member].getName() +
                     " of " + this.one.party.members[member].getDisease());
                    this.one.party.members[member].setSick(false); 
                } else {
                    System.out.println("Unfortunately, your doctor skills aren't enough and " +
                     this.one.party.members[member] + " is still sick.");
                }
            }

        }

        System.out.println("Would like to hire a doctor for $150 to try to heal " + this.one.party.members[member].getName() +
         "? Please answer yes or no");
        res = sc.nextLine();
        if(res.equals("yes")) {
            this.one.money-=150;
            chance = r.nextInt(10);
            if(chance < 5) {
                System.out.println(this.one.party.members[member].getName() + " has been cured of " +
                 this.one.party.members[member].getDisease());
                this.one.party.members[member].setSick(false); 
            } else {
                System.out.println("The treatment was unsuccessful and " + this.one.party.members[member].getName() +
                 " is still sick"); 
            }
        }
    }

    public void finished() {
        int score;
        int alive = 4 - this.one.numDead;
        score = 150 * alive;
        score+=this.one.food;
        score+=this.one.money;
        if(this.one.party.members[0].getJob().equals("Factory Worker")) {
            score+=100;
        }

        System.out.println("You made it to Oregon! Your final score is " + score + ". These people in your wagon party survived: ");
        for(int i = 0; i < 4; i++) {
            if(!this.one.party.members[i].getDead()) {
                System.out.println(this.one.party.members[i].getName() + ", Age: " + this.one.party.members[i].getAge());
            }
        }
        
    }
}
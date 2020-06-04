public class Player {
    public Party party;
    public Wagon wagon;
    public int numDead;
    public int food;
    public int money;
    public int miles;
    public int perDayMiles;
    public int perDayFood;

    public Player(final int num) {
        this.party = new Party(num);
        this.wagon = new Wagon();
        this.numDead = 0;
        this.miles = 2000;
    }
}
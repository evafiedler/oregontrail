
public class Party {
    private int size;
    public Char[] members;
    private int count;

    public Party(int num) {
        this.size = num;
        this.members = new Char[this.size];
        this.count = 0;
    }

    public void addMember(Char member) {
        this.members[this.count] = member;
        this.count++;
    }
}
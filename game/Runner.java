import java.util.Timer;

public class Runner {
    public static void main(String[] args) {
        
        Timer t = new Timer();

        t.schedule(new Time(1), 0, 1000);

        
        
        // Prompt p = new Prompt();
        // p.wagonEvent();
        
    }
    
}
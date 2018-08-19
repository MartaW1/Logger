import model.Event;
import repository.Repository;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main (String[] args){
        Logger logger = new Logger();
        logger.startLogging();


        Scanner scanner = new Scanner(System.in);
        System.out.println("If you want to get events press Y");
        String getEvents = scanner.nextLine();
        if (getEvents.equals("Y")){
            List<Event> events = Repository.getEvents();
            for (Event event: events){
                System.out.println(event);
            }
        }
    }
}

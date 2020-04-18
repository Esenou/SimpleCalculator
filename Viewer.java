import java.util.Scanner;
public class Viewer {

    private Scanner sc;
    public Viewer() {
        sc = new Scanner(System.in);
        Controller controller = new Controller(this);
        inputDate(controller);
    }

    public void inputDate(Controller controller) {
        
        System.out.println("Input:");
	String expression = sc.nextLine();
        controller.actionCommand(expression);
    }

    public void update (String value) {
        System.out.println("Output:");
        System.out.println(value);
    }


}
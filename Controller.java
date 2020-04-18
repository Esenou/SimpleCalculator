

public class Controller {

    private Model model;
    public Controller(Viewer viewer) {
        model = new Model(viewer);   
    }

    public void actionCommand(String value) {
        model.doAction(value);
    } 
     

} 
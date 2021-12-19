import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Controller implements KeyListener, ActionListener {

    private Model model;

    public Controller(Viewer viewer){
        model = new Model(viewer);
    }

    public Model getModel() {
        return model;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode){
            case 37:
                model.move("Left");
                break;
            case 38:
                model.move("Up");
                break;
            case 39:
                model.move("Right");
                break;
            case 40:
                model.move("Down");
                break;
            default:
                return;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action){
            case "First":
                model.goToChosenLevel(1);
                break;
            case "Second":
                model.goToChosenLevel(2);
                break;
            case "Third":
                model.goToChosenLevel(3);
                break;
            case "Fourth":
                model.goToChosenLevel(4);
                break;
            case "Fifth":
                model.goToChosenLevel(5);
                break;
            case "Sixth":
                model.goToChosenLevel(6);
                break;
            case "Seventh":
                model.goToChosenLevel(7);
                break;
            case "Eighth":
                model.goToChosenLevel(8);
                break;
            case "Ninth":
                model.goToChosenLevel(9);
                break;
        }
    }
}

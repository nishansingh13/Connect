
import controller.ConnectController;
import view.ConnectView;

public class Main {
    public static void main(String[] args) {
        ConnectController controller = new ConnectController();
        ConnectView view = new ConnectView(controller);
        view.showMenu();
    }
}


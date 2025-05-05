import java.util.Scanner;

import controller.ConnectController;
import ui.MyFrame;
import view.ConnectView;

public class Main {
    public static void main(String[] args) {
     
        ConnectController controller = new ConnectController();
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose 1 for GUI and other for CLI look: ");
        choice = sc.nextInt();
       
        if(choice==1){
           
            new MyFrame(controller);
        }
        else{
            ConnectView view = new ConnectView(controller);
            view.showMenu();
            
        }
    }
}


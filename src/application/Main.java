package application;



import view.View;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	static BorderPane root = new BorderPane();
	//static Controller theController = new Controller();
	@Override
	public void start(Stage primaryStage) {

		try {
			View theView = new View(root);
			theView.menus(root);

			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

		/*
		 * Chapitre chap = new Chapitre(8, "test"); DAOchapitre daochap = new
		 * DAOchapitre(); daochap.add(chap); System.out.println(chap.getidchap());
		 * System.out.println("hi");
		 */

		/*
		 * DAOchapitre daochap = new DAOchapitre(); List<Chapitre> listChap =
		 * daochap.findAll(); for (Chapitre temp : listChap) {
		 * System.out.println(temp.getidchap() + "  " + temp.gettitlechap()); }
		 */

		/*
		 * View view = new View(); root.setCenter(view.printAllChapters());
		 */

	}
}

package view;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.*;

import javax.swing.event.ChangeListener;

import control.Controller;
import dao.DAOquestion;

public class View {

	private static BorderPane root = new BorderPane();

	static Controller ctrl = new Controller();

	public View(BorderPane root) { // constructor
		super();
		View.root = root;
	}

	public void menus(BorderPane root) {

		MenuBar mBar = new MenuBar();

		Menu menu1 = new Menu("les chapitre");
		Menu menu2 = new Menu("Examen");
		Menu menu3 = new Menu("Improt/export");

		MenuItem listchap = new MenuItem("la liste des chapitres");
		MenuItem addchap = new MenuItem("ajouter un chapitre");
		MenuItem addq = new MenuItem("ajouter une question");
		MenuItem quitapp = new MenuItem("Quitter l'application");
		listchap.setOnAction(e -> ctrl.printAllChapters());
		addchap.setOnAction(e -> addChapConsole());
		addq.setOnAction(e -> addQeustionConsole());
		quitapp.setOnAction(e -> Platform.exit());
		menu1.getItems().addAll(listchap, addchap, addq, quitapp);

		MenuItem examlist = new MenuItem("la liste des examens");
		MenuItem genexam = new MenuItem("generer un examen");
		examlist.setOnAction(e -> ctrl.printAllExams());
		genexam.setOnAction(e -> addExamConsole());
		menu2.getItems().addAll(examlist, genexam);

		MenuItem updatebase = new MenuItem("alimenter la base de données");
		MenuItem expchap = new MenuItem("exporter les chapitres");
		MenuItem exportexam = new MenuItem("exporter le examens");
		menu3.getItems().addAll(updatebase, expchap, exportexam);

		mBar.getMenus().addAll(menu1, menu2, menu3);
		root.setTop(mBar);
	}

	@SuppressWarnings("unchecked")
	public static void ChaptersTab(ObservableList<Chapitre> oList) {

		TableView<Chapitre> chaptable = new TableView<Chapitre>();
		TableColumn<Chapitre, Integer> chapID = new TableColumn<Chapitre, Integer>("ID Chapitre");
		TableColumn<Chapitre, String> chapTitle = new TableColumn<Chapitre, String>("Nom Chapitre");
		chaptable.getColumns().addAll(chapID, chapTitle);

		chapID.setCellValueFactory(new PropertyValueFactory<Chapitre, Integer>("idchap"));
		chapTitle.setCellValueFactory(new PropertyValueFactory<Chapitre, String>("titlechap"));

		chaptable.setItems(oList);
		root.setCenter(chaptable);
	}

	public static void addChapConsole() {

		TextField addchap = new TextField();
		addchap.setPromptText("Chapter Name");
		addchap.setMinWidth(100);
		Button add = new Button("add");
		add.setOnAction(e -> {
			ctrl.addchapter(addchap.getText());
			addchap.clear();
		});

//		BooleanBinding empty =  bind(addchap.getText().isBlank());
//		add.setDisable(!empty);
		add.disableProperty().bind(Bindings.isEmpty(addchap.textProperty()));

		// add.disableProperty().bind(Observable.of(addchap.textProperty().getValue().trim().isEmpty()));

		HBox hbox = new HBox();
		hbox.getChildren().addAll(addchap, add);
		hbox.setSpacing(10);
		hbox.setPadding(new Insets(5, 50, 10, 50)); // top right bottom left

		root.setBottom(hbox);
		ctrl.printAllChapters(); // update chap list view
	}

	public static void addQeustionConsole() {

		root.setBottom(null);

		HBox hbox = new HBox();
		VBox vbox = new VBox();
		ComboBox<String> comboBox = new ComboBox<String>();
		TextField enofild = new TextField();
		Text generatedID = new Text();
		Text sub1 = new Text("new id:");
		Text sub2 = new Text("Nom de chapitre:");
		Text sub3 = new Text("Enoncé:");
		Button add = new Button("Add");

		generatedID.setText(String.valueOf(ctrl.generateIDforQestion()));

		comboBox.setPromptText("Nom Chapitre");
		comboBox.getItems().addAll(ctrl.allchapternames());

		hbox.getChildren().addAll(sub1, generatedID, sub2, comboBox);
		hbox.setSpacing(20);

		enofild.setPrefWidth(80);
		enofild.setMaxWidth(80);

		vbox.getChildren().addAll(hbox, sub3, enofild, add);
		vbox.setSpacing(20);
		
		add.disableProperty().bind((enofild.textProperty()).isEmpty().or(comboBox.valueProperty().isNull()));
		
		add.setOnAction(e -> {
			ctrl.addQestion(Integer.parseInt(generatedID.getText()), comboBox.getValue(), enofild.getText());
			addQeustionConsole();
		});
		root.setCenter(vbox);
	}

	public static void examTab(ObservableList<Exam> eList) {
		root.setBottom(null);
		
		TableView<Exam> eqtable = new TableView<Exam>();
		TableColumn<Exam, Integer> idexam = new TableColumn<Exam, Integer>("ID Exam");
		TableColumn<Exam, String> questions = new TableColumn<Exam, String>("Question");
		TableColumn<Exam, Integer> duree = new TableColumn<Exam, Integer>("duree Exam");
		TableColumn<Exam, Integer> nbq = new TableColumn<Exam, Integer>("Nb de question");
		eqtable.getColumns().addAll(idexam, duree, nbq, questions);

		idexam.setCellValueFactory(new PropertyValueFactory<>("idexam"));
		questions.setCellValueFactory(new PropertyValueFactory<>("myquestions"));
		duree.setCellValueFactory(new PropertyValueFactory<>("Duree"));
		nbq.setCellValueFactory(new PropertyValueFactory<>("nbq"));

		eqtable.setItems(eList);

		root.setCenter(eqtable);
	}

	public static void addExamConsole() {
		root.setBottom(null);

		DAOquestion daoquestion = new DAOquestion();
		ObservableList<Question> qlist = FXCollections.observableArrayList(daoquestion.findAll());
		ObservableList<Integer> selectedids = FXCollections.observableArrayList();

		VBox vbox = new VBox();
		GridPane infogrid = new GridPane();
		VBox qvbox = new VBox();
		Text newid = new Text(String.valueOf(RandID.randID())); // generate new id without controlling duplicates TO DO

		
		
		TextField nbq = new TextField();
		TextField duree = new TextField();
		
		Button btn =new Button("Generate exam");

		infogrid.add(new Label("New exam id:"), 0, 0);
		infogrid.add(new Label("Nb de questions:"), 0, 1);
		infogrid.add(new Label("Durée:"), 0, 2);
		infogrid.add(newid, 1, 0);
		infogrid.add(nbq, 1, 1);
		infogrid.add(duree, 1, 2);

		qvbox.getChildren().add(new Label("Questions available:"));
		// qvbox.getChildren().add(chk1);

		nbq.setMaxWidth(40);
		duree.setMaxWidth(40);
		infogrid.setHgap(10);
		infogrid.setVgap(10);
		vbox.setSpacing(10);

		for (Question temp : qlist) {
			final CheckBox chk = new CheckBox(temp.getEnoncee());
			chk.selectedProperty().addListener((v, oldv, newv) -> {
				if (newv) {
					selectedids.add(temp.getIdQuestion());
				} else {
					selectedids.remove(selectedids.indexOf(temp.getIdQuestion()));
				}
			});
			qvbox.getChildren().add(chk);
		}
		vbox.getChildren().addAll(infogrid, qvbox,btn);
		btn.disableProperty().bind(Bindings.isEmpty(nbq.textProperty()).or(Bindings.isEmpty(duree.textProperty())).or(Bindings.isEmpty(selectedids)));
		btn.setOnAction(e -> {ctrl.addExam(new Exam(Integer.parseInt(newid.getText()), Integer.parseInt(nbq.getText()), Integer.parseInt(duree.getText())),
				selectedids); addExamConsole();});
		
		root.setCenter(vbox);
		
	}
	
	public static void error (String errortext)
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(errortext);

		alert.showAndWait();
	}

}

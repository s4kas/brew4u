package com.bmp.xanga.brew4u;

import impl.org.controlsfx.i18n.Localization;

import java.io.IOException;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import com.bmp.xanga.arc.AbstractArc;
import com.bmp.xanga.brew4u.menubar.MenuBarController;
import com.bmp.xanga.brew4u.recipe.RecipeController;
import com.bmp.xanga.brew4u.temperature.TemperatureController;
import com.bmp.xanga.brew4u.temperature.model.DispatcherEvent;
import com.bmp.xanga.brew4u.temperature.model.TemperatureDispatcher;
import com.bmp.xanga.brew4u.temperature.model.TemperatureService;
import com.bmp.xanga.brew4u.temperature.views.ArcTemperature;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainApp extends Application implements Observer {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	// UI
	private Stage primaryStage;
    private GridPane rootLayout;
    private FXMLLoader leftLoader;
    private Pane leftTemperature;
    private FXMLLoader rightLoader;
    private Pane rightTemperature;
    
    // BUSINESS
    private TemperatureService tempService;
    private TemperatureDispatcher tempDispatcher;
    
    //I18N
    Locale locale = new Locale("pt", "PT");
    ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

	@Override
	public void start(Stage stage) throws Exception {
		//set PT as default locale
		Locale.setDefault(locale);
		Localization.setLocale(locale);

		this.primaryStage = stage;
        this.primaryStage.setTitle("Cerveja XANGA");
        
        initBusiness();
        initRootLayout();
        initMenuBar();
        initTemperatures();
        
//TODO BM
        
FXMLLoader loader = new FXMLLoader();
loader.setResources(bundle);
loader.setLocation(RecipeController.class.getResource("views/Recipe.fxml"));
TabPane pane = (TabPane)loader.load();

rootLayout.add(pane, 0, 2, 3, 1);
//TODO BM
        
        bindTemperatureBusiness();
	}

	/**
	 * Inicializa a parte de negócio
	 */
	private void initBusiness() {
		tempService = new TemperatureService();
		tempDispatcher = new TemperatureDispatcher();
	}

	/**
     * Inicializa o root layout (layout base).
     */
    private void initRootLayout() {
        try {
            // Carrega o root layout do arquivo fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(bundle);
            loader.setLocation(MainApp.class.getResource("MainApp.fxml"));
            rootLayout = (GridPane) loader.load();

            // Mostra a scene (cena) que tem o root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Inicializa o menu
     */
    private void initMenuBar() {
    	try {
            // Carrega o menu bar do arquivo fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(bundle);
            loader.setLocation(MenuBarController.class.getResource("views/MenuBar.fxml"));
            MenuBar menuBar = (MenuBar) loader.load();
            ((MenuBarController)loader.getController()).initData(tempService);
            
            rootLayout.add(menuBar, 0, 0, 3, 1);

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    
    /**
	 * Inicializa as temperaturas
	 */
	private void initTemperatures() {
		try {
			// Carrega a temperatura do arquivo fxml.
			leftLoader = new FXMLLoader();
			leftLoader.setResources(bundle);
			leftLoader.setLocation(ArcTemperature.class.getResource("Temperature.fxml"));
	        leftTemperature = (Pane)leftLoader.load();
	        ((TemperatureController)leftLoader.getController()).initData(AbstractArc.BLUE1, AbstractArc.BLUE2, leftTemperature);
	        
	        // Carrega a temperatura do arquivo fxml.
	        rightLoader = new FXMLLoader();
	        rightLoader.setResources(bundle);
	        rightLoader.setLocation(ArcTemperature.class.getResource("Temperature.fxml"));
	     	rightTemperature = (Pane)rightLoader.load();
	     	((TemperatureController)rightLoader.getController()).initData(AbstractArc.GREEN1, AbstractArc.GREEN2, rightTemperature);
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Efetua a ligação entre o serviço da temperatura e os controladores
	 */
	private void bindTemperatureBusiness() {
		tempService.addObserver(tempDispatcher);
		tempDispatcher.addTempOneObs(leftLoader.getController());
		tempDispatcher.addTempTwoObs(rightLoader.getController());
		tempDispatcher.setMainObserver(this);
	}
	
	@Override
	public void stop() {
		try {
			if (this.tempService != null) {
				this.tempService.close();
			}
			super.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof DispatcherEvent) {
			DispatcherEvent ev = (DispatcherEvent)arg;
			switch (ev) {
				case OPEN_LEFT_TEMP:
					Platform.runLater(() -> {this.rootLayout.add(leftTemperature, 0, 1);});
					break;
				case OPEN_RIGHT_TEMP:
					Platform.runLater(() -> {this.rootLayout.add(rightTemperature, 2, 1);});
					break;
				case SHOW_ERROR_MESSAGE:
					break;
			}
		}
	}
}
package com.bmp.xanga;

import java.io.IOException;

import com.bmp.xanga.arc.IArc;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp {

	private Stage primaryStage;
    private BorderPane rootLayout;
    private Canvas canvasTempLeft;
    private Canvas canvasTempRight;
    
    private IArc leftTemp;
    public void setLeftTemp(IArc leftTemp) {
    	this.leftTemp = leftTemp;
    }
   	private IArc rightTemp;
   	public void setRightTemp(IArc rightTemp) {
   		this.rightTemp = rightTemp;
    }
   	
   	private static final String OP_SEL_RECIPE = "openRecipe";
   	private static final String OP_SEL_TERM = "selectTerm";
	private static final String OP_ABOUT = "about";

	public void init(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Cerveja XANGA");

        initRootLayout();
        addTopMenuOptions();
	}

	/**
     * Inicializa o root layout (layout base).
     */
    private void initRootLayout() {
        try {
            // Carrega o root layout do arquivo fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("MainApp.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Mostra a scene (cena) que tem o root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Adicionar as acções para o menu de topo
     */
    private void addTopMenuOptions() {
		MenuBar topNode = (MenuBar) this.rootLayout.getTop();
		for (Menu menu : topNode.getMenus()) {
			for (MenuItem menuItem : menu.getItems()) {
				if (OP_SEL_RECIPE.equals(menuItem.getId())) {
					menuItem.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent ev) {
							System.out.println("selRec");
						}
					});
				}
				
				if (OP_SEL_TERM.equals(menuItem.getId())) {
					menuItem.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent ev) {
							System.out.println("selTerm");
						}
					});
				}
				
				if (OP_ABOUT.equals(menuItem.getId())) {
					menuItem.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent ev) {
							System.out.println("about");
						}
					});
				}
			}
		}
	}

    /**
     * Mostra o painel da temperatura (esquerda).
     */
    private void showLeftTempPanel() {
    	//instanciamos un novo canvas
    	canvasTempLeft = new Canvas(220,220);
    	

    	
        //instanciamos o indicador temp da esquerda
    	//leftTemp = (IArc) new ArcTemperature(20, AbstractArc.GREEN1, AbstractArc.GREEN2, 200);
    	
    	// add the single node onto the scene graph
    	rootLayout.setLeft(canvasTempLeft);
    	
    	new AnimationTimer() {
    		@Override
    		public void handle(long now) {
    			//left temp
    			
    	    	//recuperamos o GraphicsContext
    	    	GraphicsContext gcLeft = canvasTempLeft.getGraphicsContext2D();
    	    	
    			leftTemp.update(now);
    			gcLeft.clearRect(0, 0, 220, 220);
    			gcLeft.save();
    			gcLeft.translate(5, 5);
    			leftTemp.draw(gcLeft);
    			gcLeft.restore();
    		}
    	}.start();
    }
    
    /**
     * Mostra o painel da temperatura (direita).
     */
    private void showRightTempPanel() {
    	//instanciamos un novo canvas
    	canvasTempRight = new Canvas(220,220);
    	
        //instanciamos o indicador temp da esquerda
    	//rightTemp = (IArc) new ArcTemperature(20, AbstractArc.BLUE1, AbstractArc.BLUE2, 200);
    	
    	// add the single node onto the scene graph
    	rootLayout.setRight(canvasTempRight);
    	
    	new AnimationTimer() {
    		@Override
    		public void handle(long now) {
    			//right temp
    			
    			//recuperamos o GraphicsContext
    	    	GraphicsContext gcRight = canvasTempRight.getGraphicsContext2D();
    			
    			rightTemp.update(now);
    			gcRight.clearRect(0, 0, 220, 220);
    			gcRight.save();
    			gcRight.translate(5, 5);
    			rightTemp.draw(gcRight);
    			gcRight.restore();
    		}
    	}.start();
    }
}

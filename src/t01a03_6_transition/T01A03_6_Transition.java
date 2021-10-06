/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t01a03_6_transition;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import static javafx.scene.shape.StrokeType.CENTERED;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author jose
 */
public class T01A03_6_Transition extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        // Creamos el circulo
        Circle circulo = new Circle(540, 400, 200);
        // Le damos borde negro al circulo
        circulo.setStroke(Color.BLACK);
        // Le decimos que el borde no vaya hacia fuera ni hacia dentro del circulo, sino que tenga mitad y mitad
        circulo.setStrokeType(CENTERED);
        // Asignamos el ancho del borde del circulo
        circulo.setStrokeWidth(5.0);
        // Le damos color al circulo (el valor alpha es 0, por lo tanto será invisible el centro del circulo, pero no su borde
        circulo.setFill(Color.rgb(0, 0, 0, 0));
        
        // Creamos un rectangulo
        Rectangle rect = new Rectangle(100, 200);
        // Le damos color al rectangulo
        rect.setFill(Color.rgb(255, 143, 0));
        
        // Creamos la transicion (animacion), la cual durará 4 segundos (4000 milisegundos), el path (ruta) sera el circulo y el nodo que realiza la ruta sera el rectangulo
        PathTransition animacion = new PathTransition(new Duration(4000) , circulo, rect);
        // Le damos la capacidad al rectangulo de orientarse siempre con la misma verticalidad sobre la circunferencia del circulo, es decir, le damos rotacion.
        animacion.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        // La animacion se ejecutara de forma indefinida
        animacion.setCycleCount(Timeline.INDEFINITE);
        // La animacion no realiza parada, se mueve de forma lineal
        animacion.setInterpolator(Interpolator.LINEAR);
        
        // El nodo raiz es un Pane, ya que permite libertad de posicion para los objetos, otro layout no dejaria que el rectangulo estuviera encima del circulo
        Pane root = new Pane();
        // Añadimos el circulo
        root.getChildren().add(circulo);
        // Añadimos el rectangulo
        root.getChildren().add(rect);
        
        // Comenzamos la animacion
        animacion.play();
        
        // Creamos la escena
        Scene scene = new Scene(root, 1080, 800);
        
        // Si hacemos click (y mantenemos presionado) con el raton en cualquie parte de la ventana, se detendra la animacion
        root.setOnMousePressed(e -> {
            animacion.pause();
        });
        
        // Por el contrario, si despulsamos el raton, se retomará la animacion
        root.setOnMouseReleased(e -> {
            animacion.play();
        });
        
        // Agregamos titulo, la escena al escenario y mostramos escenario
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

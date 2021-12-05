package frontend.buttons;

import backend.model.Point;
import frontend.Exceptions.WrongDirectionException;
import frontend.wrappers.WrappedFigure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

/*
 * Clase abstracta para modelar los botones de las figuras.
 * Utilizado para que al momento de crear una figura, no sea
 * necesario saber que botón está encendido al poder llamarse al
 *  método createFigure.
 */
public abstract class CustomButton extends ToggleButton {

    public CustomButton(String text) {
        super(text);
    }

    public abstract WrappedFigure createFigure(Point startPoint, Point endPoint, GraphicsContext gc, Color edgeColor, Color fillColor, double edgeWidth);

    /*
     * Método utilizado para verificar que se esté arrastrando el mouse en la dirección correcta.
     * No es llamado en LineButton ni CircleButton pues se debe poder dibujar en cualquier dirección
     */
    protected void checkPoints(@NotNull Point startPoint, @NotNull Point endPoint){
        if( (endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY()) )
            throw new WrongDirectionException();
    }
}

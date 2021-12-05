package frontend.buttons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Se crea esta clase para poder tener implementado
 * el método getSelectedButton que busca de la lista el botón
 * encendido.
 */
public class CustomGroup{
    private final List<CustomButton> buttonList = new ArrayList<>();

    public void addButton(CustomButton[] buttons){
        buttonList.addAll(Arrays.asList(buttons));
    }

    public CustomButton getSelectedButton(){
        for(CustomButton button : buttonList){
            if(button.isSelected()){
                return button;
            }
        }
        return null;
    }
}

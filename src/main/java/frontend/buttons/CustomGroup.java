package frontend.buttons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

package com.roleplay.utils;

import com.roleplay.Character;
import com.roleplay.Main;

/**
 * File created by Lorenzo Delcroix on 12/23/2016 2:11.
 * Belongs to the package com.roleplay of the RoleplayHelper project.
 * //TODO: remove character and rework all dependent classes with get/set methods from Main
 */
public abstract class GUIController {
    protected Character character;
    protected Main mainClass;

    public void setCharacter(Character character){
        this.character = character;
    }

    public void setMainClass(Main mainClass) {
        this.mainClass = mainClass;
    }
}

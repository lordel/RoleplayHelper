package com.roleplay;

import com.roleplay.enums.CharacterTraits;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;

/**
 * Class which holds all the statistics for a character.
 * Stats contained in this class include name, strength, dexterity, constitution, intelligence, wisdom,
 * charisma, and health. This class stores both maximum health and current health. Bonuses for each stat which has one
 * is automatically calculated. All the current values stored in this class can be saved to an XML file and a previously
 * saved XML file can be used to read values into this class.
 */
//We define XML elements to allow saving the data to an XML file
@XmlRootElement(name = "character") //Defines the name of the root XML element
@XmlAccessorType(XmlAccessType.FIELD)
public class Character{
    //attributes defined as XmlElements to be able to save them to XML file
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "strength")
    private int str;
    @XmlElement(name ="dexterity")
    private int dex;
    @XmlElement(name = "constitution")
    private int con;
    @XmlElement(name = "intelligence")
    private int intel;
    @XmlElement(name = "wisdom")
    private int wis;
    @XmlElement(name = "charisma")
    private int cha;
    @XmlElement(name = "maximumHealth")
    private int hpMax;
    @XmlElement(name = "currentHealth")
    private int hpCurr;

    //Attributes defined as XmlTransient to avoid saving them to XML file
    @XmlTransient
    private final String filepath = "./src/com/roleplay/XML/characterData.xml"; //constant used to save to/read xml file
    @XmlTransient
    private int strbn, dexbn, conbn, intbn, wisbn, chabn;

    /**
     * Default constructor creates Character with empty values.
     */
    public Character() {}

    /**
     * Constructor initializes a new Character with the input values.
     * @param name The name of the Character.
     * @param str The strength of the Character.
     * @param dex The dexterity of the Character.
     * @param con The constitution of the Character.
     * @param intel The intelligence of the Character.
     * @param wis The wisdom of the Character.
     * @param cha The charisma of the Character.
     * @param hpMax The maximum health value of the Character.
     */
    public Character(String name, int str, int dex, int con, int intel, int wis, int cha, int hpMax){
        this.name = name;
        setValues(str, dex, con, intel, wis, cha, hpMax);
    }

    //Getters-----------------------------------------------------------------------------------------------------------

    /**
     * Gets the name of this Character.
     * @return The name of this Character.
     */
    public String getName(){
        return name;
    }

    /**
     * Gets the strength of this Character.
     * @return The strength of this Character.
     */
    public int getStr() {
        return str;
    }

    /**
     * Gets the dexterity of this Character.
     * @return The dexterity of this Character.
     */
    public int getDex() {
        return dex;
    }

    /**
     * Gets the constitution of this Character.
     * @return The constitution of this Character.
     */
    public int getCon() {
        return con;
    }

    /**
     * Gets the intelligence of this Character.
     * @return The intelligence of this Character.
     */
    public int getIntel() {
        return intel;
    }

    /**
     * Gets the wisdom of this Character.
     * @return The wisdom of this Character.
     */
    public int getWis() {
        return wis;
    }

    /**
     * Gets the charisma of this Character.
     * @return The charisma of this Character.
     */
    public int getCha() {
        return cha;
    }

    /**
     * Gets the maximum health of this Character.
     * @return The maximum health of this Character.
     */
    public int getHpMax() {
        return hpMax;
    }

    /**
     * Gets the current health of this Character.
     * @return The current health of this Character.
     */
    public int getHpCurr() {
        return hpCurr;
    }

    /**
     * Gets one of the bonus values from this Character.
     * @param choice Trait choice to select the wanted bonus.
     * @return Returns the value of the bonus selected by "choice".
     */
    public int getBonus(CharacterTraits choice){
        switch (choice){
            case STR: return strbn;
            case DEX: return dexbn;
            case CON: return conbn;
            case INTEL: return intbn;
            case WIS: return wisbn;
            case CHA: return chabn;
            default: return 0;
        }
    }

    //Setters-----------------------------------------------------------------------------------------------------------
    private void setValues(int str, int dex, int con, int intel, int wis, int cha, int hpMax){
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.intel = intel;
        this.wis = wis;
        this.cha = cha;
        this.hpMax = hpMax;
        hpCurr = hpMax; //current hp
        setBonus();
    }

    private void setBonus(){
        //bonuses can be negative
        strbn=(str-10)/2;
        dexbn=(dex-10)/2;
        conbn=(con-10)/2;
        intbn=(intel-10)/2;
        wisbn=(wis-10)/2;
        chabn=(cha-10)/2;
    }

    public void setHpCurr(int hpCurr) {
        this.hpCurr = hpCurr;
    }

    //XML save/read methods---------------------------------------------------------------------------------------------

    /**
     * Saves this Character's info to XML file.
     */
    public void saveToXML() {
        //TODO: add throws exception
        try {
            JAXBContext jc = JAXBContext.newInstance(Character.class);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.marshal(this, new File(filepath));

            System.out.println("Data was saved to file: " + filepath);
        } catch (JAXBException e) {
            System.out.println("There was a problem saving to the file: " + e.getMessage() + ". please try again.");
            e.printStackTrace();
        }
    }

    /**
     * Reads values from a XML file into this Class' attributes.
     */
    public void readFromXML() {
        //TODO: add throws exception
        try {
            JAXBContext jc = JAXBContext.newInstance(Character.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Character readCharacter = (Character) unmarshaller.unmarshal(new File(filepath));

            name = readCharacter.name;
            str = readCharacter.str;
            dex = readCharacter.dex;
            con = readCharacter.con;
            intel = readCharacter.intel;
            wis = readCharacter.wis;
            cha = readCharacter.cha;
            hpMax = readCharacter.hpMax;
            hpCurr = readCharacter.hpCurr;
        } catch (JAXBException e) {
            System.out.println("Error reading info from file!! " + e.getMessage() + " Please try again.");
        }
    }
}

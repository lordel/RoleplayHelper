package com.roleplay;

import com.roleplay.enums.CharacterTraits;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

import java.io.File;

//We define XML elements to allow saving the data to an XML file
@XmlRootElement(name = "character")
@XmlAccessorType(XmlAccessType.FIELD)
public class Character{
    @XmlTransient
    private final String filepath = "./src/com/roleplay/XML" +
            "/characterData.xml"; //constant used to save/read info from xml file

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

    @XmlTransient
    private int strbn, dexbn, conbn, intbn, wisbn, chabn;

    public Character() {} //default constructor

    public Character(String name, int str, int dex, int con, int intel, int wis, int cha, int hpMax){
        this.name = name;
        setValues(str, dex, con, intel, wis, cha, hpMax);
    }

    //Getters-----------------------------------------------------------------------------------------------------------
    public String getName(){
        return name;
    }

    public int getStr() {
        return str;
    }

    public int getDex() {
        return dex;
    }

    public int getCon() {
        return con;
    }

    public int getIntel() {
        return intel;
    }

    public int getWis() {
        return wis;
    }

    public int getCha() {
        return cha;
    }

    public int getHpMax() {
        return hpMax;
    }

    public int getHpCurr() {
        return hpCurr;
    }

    /**
     * getBonus() is used to retrieve a bonus value from the character class
     * @param choice integer, used to choose bonus to be returned. 1=str, 2=dex, 3=con, 4=intel, 5=wis, 6=cha
     * @return returns the value of the bonus selected by 'choice'
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
        hpCurr = hpMax;      //current hp
        setBonus();
    }

    private void setBonus(){
        strbn=(str-10)/2;           //bonuses can be negative
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
    public void saveToXML() {
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

    public void readFromXML() {
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

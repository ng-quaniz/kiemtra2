package com.example.cayxanh;

public class listview {
    public String name, name2, color, characteristic;


    public listview(){

    }

    public listview(String name, String name2, String color, String characteristic) {
        this.name = name;
        this.name2 = name2;
        this.color = color;
        this.characteristic = characteristic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }
}

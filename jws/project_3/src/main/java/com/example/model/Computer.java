package com.example.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "computer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Computer {
    
    @XmlElement
    private String comID;
    
    @XmlElement
    private String comName;
    
    @XmlElement
    private double price;
    
    @XmlElement
    private String manufacturer;

    public Computer() {
    }

    public Computer(String comID, String comName, double price, String manufacturer) {
        this.comID = comID;
        this.comName = comName;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public String getComID() {
        return comID;
    }

    public void setComID(String comID) {
        this.comID = comID;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Converts Computer object to XML string format.
     * @return XML string representation of the computer
     */
    public String toXmlString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<computer>");
        sb.append("<comID>").append(escapeXml(comID)).append("</comID>");
        sb.append("<comName>").append(escapeXml(comName)).append("</comName>");
        sb.append("<price>").append(price).append("</price>");
        sb.append("<manufacturer>").append(escapeXml(manufacturer)).append("</manufacturer>");
        sb.append("</computer>");
        return sb.toString();
    }

    /**
     * Escapes XML special characters.
     * @param input the string to escape
     * @return the escaped string
     */
    private static String escapeXml(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }

    @Override
    public String toString() {
        return "Computer{" +
                "comID='" + comID + '\'' +
                ", comName='" + comName + '\'' +
                ", price=" + price +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}

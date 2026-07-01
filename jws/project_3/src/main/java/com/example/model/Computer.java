package com.example.model;

public class Computer {
    private String comID;
    private String comName;
    private double price;
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

    public String toXmlString() {
        return "<computer>"
                + "<comID>" + escapeXml(comID) + "</comID>"
                + "<comName>" + escapeXml(comName) + "</comName>"
                + "<price>" + price + "</price>"
                + "<manufacturer>" + escapeXml(manufacturer) + "</manufacturer>"
                + "</computer>";
    }

    private String escapeXml(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }
}

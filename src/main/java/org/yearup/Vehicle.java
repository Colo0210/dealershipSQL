package org.yearup;


import java.math.BigDecimal;

public class Vehicle {
    private String vin;
    private String make;
    private String model;
    private String color;
    private int year;
    private int miles;
    private BigDecimal price;
    private boolean sold;
    private String type;
    public Vehicle(String vin, String make, String model, String color, int year, int miles, BigDecimal price, boolean sold, String type) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.miles = miles;
        this.price = price;
        this.sold = sold;
        this.type = type;
    }
    public Vehicle() {
        // default constructor
    }

    @Override
    public String toString() {
        return "Vehicle [vin=" + vin + ", make=" + make + ", model=" + model
                + ", year=" + year + ", color=" + color + ", mileage=" + miles
                + ", price=" + price + ", sold=" + sold + ", type=" + type + "]";
    }

    public String getVin()
    {
        return vin;
    }

    public void setVin(String vin)
    {
        this.vin = vin;
    }

    public String getMake()
    {
        return make;
    }
    public void setSold(boolean sold)
    {
        this.sold = sold;
    }

    public void setMake(String make)
    {
        this.make = make;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public int getYear()
    {
        return year;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void setYear(int year)
    {
        this.year = year;
    }

    public int getMiles()
    {
        return miles;
    }

    public void setMiles(int miles)
    {
        this.miles = miles;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public boolean sold()
    {
        return sold;
    }
}


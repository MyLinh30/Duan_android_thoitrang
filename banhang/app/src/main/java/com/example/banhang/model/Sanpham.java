package com.example.banhang.model;

public class Sanpham {
    public int Id;
    public String Name;
    public int Idtype;
    public Integer Price;
    public String Color;
    public  String Material;
    public String Linkhinhanh;
    public String Desc;

    public Sanpham(int id, String name, int idtype, Integer price, String color, String material, String linkhinhanh, String desc) {
        Id = id;
        Name = name;
        Idtype = idtype;
        Price = price;
        Color = color;
        Material = material;
        Linkhinhanh = linkhinhanh;
        Desc = desc;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getIdtype() {
        return Idtype;
    }

    public void setIdtype(int idtype) {
        Idtype = idtype;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    public String getLinkhinhanh() {
        return Linkhinhanh;
    }

    public void setLinkhinhanh(String linkhinhanh) {
        Linkhinhanh = linkhinhanh;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }
}

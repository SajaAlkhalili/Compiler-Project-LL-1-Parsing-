package com.example.finalprojectcompiler;

public class ProductionRules {
    public String LeftSide = "";// String for leftsid
    public String RightSide = "";//String for rightside
    public ProductionRules(String left, String right){//constructor  get letside and rightside
        this.LeftSide = left;
        this.RightSide = right;
    }


    public String getLeftSide(){
        return this.LeftSide;
    }// method to get leftside

    public String getRightSide(){
        return this.RightSide;
    }// method to get rightside
}


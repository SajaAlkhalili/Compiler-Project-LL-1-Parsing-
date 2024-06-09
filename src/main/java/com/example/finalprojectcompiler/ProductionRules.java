package com.example.finalprojectcompiler;

public class ProductionRules {
    public String leftSide = "";
    public String rightSide = "";
    public ProductionRules(String left, String right){
        this.leftSide = left;
        this.rightSide = right;
    }


    public String getLeftSide(){
        return this.leftSide;
    }

    public String getRightSide(){
        return this.rightSide;
    }
}


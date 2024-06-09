package com.example.finalprojectcompiler;

public class Tokens {
    String token = "";
    int code;
    int lineNumber;

    Tokens(String token, int code) {
        this.token = token;
        this.code = code;

    }

    public void setLineNumber(int line){
        this.lineNumber = line;
    }

    public int getLineNumber(){
        return this.lineNumber;
    }
//    @Override
//    public String toString() {
//        return "Token: " + token + ", Code: " + code + ", Line: " + lineNumber;
//    }
@Override
public String toString() {
    return " " + token ;
}
}
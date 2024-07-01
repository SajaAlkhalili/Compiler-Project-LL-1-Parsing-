package com.example.finalprojectcompiler;

public class Tokens {
    String token = "";// string to represent of the token
    int code;// integer for code tokens
    int lineNumber;// integer to line number

    Tokens(String token, int code) {// constructor  get token and code
        this.token = token;
        this.code = code;

    }

    public void setLineNumber(int line){// method to  set LineNumber
        this.lineNumber = line;
    }

    public int getLineNumber(){
        return this.lineNumber;
    }//method to  get LineNumber
//    @Override
//    public String toString() {
//        return "Token: " + token + ", Code: " + code + ", Line: " + lineNumber;
//    }
@Override
public String toString() {
    return " " + token ;
}// method to ptint token
}
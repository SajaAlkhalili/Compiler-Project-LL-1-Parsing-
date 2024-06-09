package com.example.finalprojectcompiler;

import java.util.Set;

public class FirstAndFollow {
    private String nonterminal = "";
    private String follows = "";
    private String first = "";


    public FirstAndFollow(String non){
        this.nonterminal = non;
    }

    public int addNewFollow(String newFollow){

        String[]arrOfFollows={};
        if(!follows.equals(""))
            arrOfFollows = this.follows.split("@");

        for(int i=0 ; i<arrOfFollows.length ; i++){
            if(arrOfFollows[i].equals(newFollow)){
                return 0;
            }
        }
        if(follows.equals("")){
            this.follows = newFollow;
        }else{
            this.follows += "@"+newFollow ;
        }
        return 1;
    }

    public int addNewFirst(String newFirst){

        String[]arrOfFirst = {};
        if(!first.equals(""))
            arrOfFirst = this.first.split("@");

        for(int i=0 ; i<arrOfFirst.length ; i++){
            if(arrOfFirst[i].equals(newFirst)){
                return 0;
            }
        }

        if(this.first.equals("")){
            this.first = newFirst;
        }else{
            this.first += "@"+newFirst ;
        }
        return 1;
    }

    public int addAllFollow(String[]follow){
        int changed = 0;
        for(int i=0 ; i<follow.length ; i++){
            if(this.addNewFollow(follow[i]) == 1)
                changed = 1;
        }

        return changed;
    }

    public String[] getFirst(){
        String[]arrOfFirst = {};
        if(!first.equals(""))
            arrOfFirst = this.first.split("@");
        return arrOfFirst;
    }
    public String[] getFollow(){
        String[]arrOfFollow = {};
        if(!follows.equals(""))
            arrOfFollow = this.follows.split("@");
        return arrOfFollow;
    }

    public String getNonTerminal(){
        return this.nonterminal;
    }



}

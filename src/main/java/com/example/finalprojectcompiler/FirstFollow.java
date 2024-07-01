package com.example.finalprojectcompiler;

public class FirstFollow {
    private String nonterminal = "";// string for storing nonterminal tokens
    private String follows = "";// string for  storing follows
    private String first = "";//string for storing first


    public FirstFollow(String non){
        this.nonterminal = non;
    }// constructor to initialize the non-terminal token.

    public int addNewFollow(String newFollow){ // method to add a new follow to the Follow set.

        String[]arrOfFollows={}; // split the follows string to an array if it is not empty.
        if(!follows.equals(""))// check if follows not equals "" do split("@")
            arrOfFollows = this.follows.split("@");

        for(int i=0 ; i<arrOfFollows.length ; i++){// check if the new follow is already in the array.
            if(arrOfFollows[i].equals(newFollow)){
                return 0;// return 0 if the follow already exist.
            }
        }
        if(follows.equals("")){// check if follows equals ("") Add the new follow to the follows string.
            this.follows = newFollow;
        }else{
            this.follows += "@"+newFollow ;
        }
        return 1;// return 1 if the follow  added.
    }

    public int addNewFirst(String newFirst){// method to add a new first to the First set.

        String[]arrOfFirst = {};  // split the first string to an array if it is not empty
        if(!first.equals(""))//check if the first not equals("") do split("@")
            arrOfFirst = this.first.split("@");

        for(int i=0 ; i<arrOfFirst.length ; i++){// check if the new first is already in array
            if(arrOfFirst[i].equals(newFirst)){
                return 0;//return 0 if the first already exist
            }
        }

        if(this.first.equals("")){// add the new first to the first string
            this.first = newFirst;
        }else{
            this.first += "@"+newFirst ;
        }
        return 1;// return 1 if the first  added
    }

    public int addAllFollow(String[]follow){  // method to add all follows from an array to the Follow
        int changed = 0;
        for(int i=0 ; i<follow.length ; i++){ // for loop through the array and add each follow
            if(this.addNewFollow(follow[i]) == 1)
                changed = 1;// set change to 1 if any follow  added
        }

        return changed; // return if any follows  added.
    }

    public String[] getFirst(){ //method to get the First set  an array.
        String[]arrOfFirst = {};
        if(!first.equals("")) // Split("@") the first string to an array if it  equals("")
            arrOfFirst = this.first.split("@");
        return arrOfFirst;// return the array of first
    }
    public String[] getFollow(){//method to get the Follow set an array
        String[]arrOfFollow = {};
        if(!follows.equals(""))
            arrOfFollow = this.follows.split("@");// Split("@") the first string to an array if it  equals("").
        return arrOfFollow;// return the array of follow
    }

    public String getNonTerminal(){
        return this.nonterminal;
    }// method to get nonterminal



}

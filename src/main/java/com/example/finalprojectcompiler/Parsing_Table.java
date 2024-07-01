package com.example.finalprojectcompiler;

public class Parsing_Table {
    static String[] nonTerminals = {//static array of nonTerminal
            "module-decl", "module-heading", "block", "declarations",
            "const-decl", "const-list", "var-decl", "var-list", "var-item", "name-list",
            "more-names", "data-type", "procedure-decl", "procedure-heading", "stmt-list",
            "statement", "ass-stmt", "exp", "exp-prime", "term", "term-prime", "factor",
            "add-oper", "mul-oper", "read-stmt", "write-stmt", "write-list",
            "more-write-value", "write-item", "if-stmt", "else-part", "while-stmt",
            "loop-stmt", "exit-stmt", "call-stmt", "condition", "relational-oper",
            "name-value", "value"};

    static String[] terminals = {//static array of terminals
            "module", ";", "begin", "const", "name", "end", "=", "var", ":",
            ",", "integer", "real", "char", "procedure", "(", ")", "+", "-", "*",
            "/", "mod", "div", "readint", "readreal", "readchar", "readln", "writeint", "writereal", "writechar",
            "writeln", "if", "then", "else", "while", "do", "loop", "exit", "until",
            "call", "|=", "<", "<=", ">", ">=", ":=", "integer-value",
            "real-value","."};

    public ProductionRules[] production = new ProductionRules[80]; //array of production rules
    public static ProductionRules[][] parsingTable = new ProductionRules[40][50];// 2D array representing the parsing table
    public FirstFollow[] firstFollowSets = new FirstFollow[40];//array of FirstFollow object for each nonTerminal
    public Parsing_Table(){ //constructor for the Parsing_Table
        //build the production rules
        production[0] = new ProductionRules("module-decl","module-heading@declarations@block@name@.");
        production[1] = new ProductionRules("module-heading","module@name@;");
        production[2] = new ProductionRules("block","begin@stmt-list@end");
        production[3] = new ProductionRules("declarations","const-decl@var-decl@procedure-decl");
        production[4] = new ProductionRules("const-decl","const@const-list");
        production[5] = new ProductionRules("const-decl","lambda");
        production[6] = new ProductionRules("const-list","name@=@value@;@const-list");
        production[7] = new ProductionRules("const-list","lambda");
        production[8] = new ProductionRules("var-decl","var@var-list");
        production[9] = new ProductionRules("var-decl","lambda");
        production[10] = new ProductionRules("var-list","var-item@;@var-list");
        production[11] = new ProductionRules("var-list","lambda");
        production[12] = new ProductionRules("var-item","name-list@:@data-type");
        production[13] = new ProductionRules("name-list","name@more-names");
        production[14] = new ProductionRules("more-names",",@name-list");
        production[15] = new ProductionRules("more-names","lambda");
        production[16] = new ProductionRules("data-type","integer");
        production[17] = new ProductionRules("data-type","real");
        production[18] = new ProductionRules("data-type","char");
        production[19] = new ProductionRules("procedure-decl","procedure-heading@declarations@block@name@;@procedure-decl");
        production[20] = new ProductionRules("procedure-decl","lambda");
        production[21] = new ProductionRules("procedure-heading","procedure@name@;");
        production[22] = new ProductionRules("stmt-list","statement@;@stmt-list");
        production[23] = new ProductionRules("stmt-list","lambda");
        production[24] = new ProductionRules("statement","ass-stmt");
        production[25] = new ProductionRules("statement","read-stmt");
        production[26] = new ProductionRules("statement","write-stmt");
        production[27] = new ProductionRules("statement","if-stmt");
        production[28] = new ProductionRules("statement","while-stmt");
        production[29] = new ProductionRules("statement","loop-stmt");
        production[30] = new ProductionRules("statement","exit-stmt");
        production[31] = new ProductionRules("statement","call-stmt");
        production[32] = new ProductionRules("statement","block");
        production[33] = new ProductionRules("statement","lambda");
        production[34] = new ProductionRules("ass-stmt","name@:=@exp");
        production[35] = new ProductionRules("exp","term@exp-prime");
        production[36] = new ProductionRules("exp-prime","add-oper@term@exp-prime");
        production[37] = new ProductionRules("exp-prime","lambda");
        production[38] = new ProductionRules("term","factor@term-prime");
        production[39] = new ProductionRules("term-prime","mul-oper@factor@term-prime");
        production[40] = new ProductionRules("term-prime","lambda");
        production[41] = new ProductionRules("factor","(@exp@)");
        production[42] = new ProductionRules("factor","name-value");
        production[43] = new ProductionRules("add-oper","+");
        production[44] = new ProductionRules("add-oper","-");
        production[45] = new ProductionRules("mul-oper","*");
        production[46] = new ProductionRules("mul-oper","/");
        production[47] = new ProductionRules("mul-oper","mod");
        production[48] = new ProductionRules("mul-oper","div");
        production[49] = new ProductionRules("read-stmt","readint@(@name-list@)");
        production[50] = new ProductionRules("read-stmt","readreal@(@name-list@)");
        production[51] = new ProductionRules("read-stmt","readchar@(@name-list@)");
        production[52] = new ProductionRules("read-stmt","readln");
        production[53] = new ProductionRules("write-stmt","writeint@(@write-list@)");
        production[54] = new ProductionRules("write-stmt","writereal@(@write-list@)");
        production[55] = new ProductionRules("write-stmt","writechar@(@write-list@)");
        production[56] = new ProductionRules("write-stmt","writeln");
        production[57] = new ProductionRules("write-list","write-item@more-write-value");
        production[58] = new ProductionRules("more-write-value",",@write-list");
        production[59] = new ProductionRules("more-write-value","lambda");
        production[60] = new ProductionRules("write-item","name");
        production[61] = new ProductionRules("write-item","value");
        production[62] = new ProductionRules("if-stmt","if@condition@then@stmt-list@else-part@end");
        production[63] = new ProductionRules("else-part","else@stmt-list");
        production[64] = new ProductionRules("else-part","lambda");
        production[65] = new ProductionRules("while-stmt","while@condition@do@stmt-list@end");
        production[66] = new ProductionRules("loop-stmt","loop@stmt-list@until@condition");
        production[67] = new ProductionRules("exit-stmt","exit");
        production[68] = new ProductionRules("call-stmt","call@name");
        production[69] = new ProductionRules("condition","name-value@relational-oper@name-value");
        production[70] = new ProductionRules("relational-oper","=");
        production[71] = new ProductionRules("relational-oper","|=");
        production[72] = new ProductionRules("relational-oper","<");
        production[73] = new ProductionRules("relational-oper","<=");
        production[74] = new ProductionRules("relational-oper",">");
        production[75] = new ProductionRules("relational-oper",">=");
        production[76] = new ProductionRules("name-value","name");
        production[77] = new ProductionRules("name-value","value");
        production[78] = new ProductionRules("value","integer-value");
        production[79] = new ProductionRules("value","real-value");

        //initialize first and follow for each nonterminal
        firstFollowSets[0] = new FirstFollow("module-decl");
        firstFollowSets[1] = new FirstFollow("module-heading");
        firstFollowSets[2] = new FirstFollow("block");
        firstFollowSets[3] = new FirstFollow("declarations");
        firstFollowSets[4] = new FirstFollow("const-decl");
        firstFollowSets[5] = new FirstFollow("const-list");
        firstFollowSets[6] = new FirstFollow("var-decl");
        firstFollowSets[7] = new FirstFollow("var-list");
        firstFollowSets[8] = new FirstFollow("var-list");
        firstFollowSets[9] = new FirstFollow("var-item");
        firstFollowSets[10] = new FirstFollow("name-list");
        firstFollowSets[11] = new FirstFollow("more-names");
        firstFollowSets[12] = new FirstFollow("data-type");
        firstFollowSets[13] = new FirstFollow("procedure-decl");
        firstFollowSets[14] = new FirstFollow("procedure-heading");
        firstFollowSets[15] = new FirstFollow("stmt-list");
        firstFollowSets[16] = new FirstFollow("statement");
        firstFollowSets[17] = new FirstFollow("ass-stmt");
        firstFollowSets[18] = new FirstFollow("exp");
        firstFollowSets[19] = new FirstFollow("exp-prime");
        firstFollowSets[20] = new FirstFollow("term");
        firstFollowSets[21] = new FirstFollow("term-prime");
        firstFollowSets[22] = new FirstFollow("factor");
        firstFollowSets[23] = new FirstFollow("add-oper");
        firstFollowSets[24] = new FirstFollow("mul-oper");
        firstFollowSets[25] = new FirstFollow("read-stmt");
        firstFollowSets[26] = new FirstFollow("write-stmt");
        firstFollowSets[27] = new FirstFollow("write-list");
        firstFollowSets[28] = new FirstFollow("more-write-value");
        firstFollowSets[29] = new FirstFollow("write-item");
        firstFollowSets[30] = new FirstFollow("if-stmt");
        firstFollowSets[31] = new FirstFollow("else-part");
        firstFollowSets[32] = new FirstFollow("while-stmt");
        firstFollowSets[33] = new FirstFollow("loop-stmt");
        firstFollowSets[34] = new FirstFollow("exit-stmt");
        firstFollowSets[35] = new FirstFollow("call-stmt");
        firstFollowSets[36] = new FirstFollow("condition");
        firstFollowSets[37] = new FirstFollow("relational-oper");
        firstFollowSets[38] = new FirstFollow("name-value");
        firstFollowSets[39] = new FirstFollow("value");

        computeFirst();//call computeFirst() method
        computeFollow();//call  computeFollow(); method
        buildParsingTable(production);// call   buildParsingTable(production) method
    }

public void buildParsingTable(ProductionRules[] production){// method ro build parsing table
    //initialize first row in parsing table
    FirstFollow startSymbol = findNonterminal("module-decl");
    String[]first0 = startSymbol.getFirst();//get the first set of  start symbol
    boolean containsLambda = false; // do a flag to check if the first set contains lambda
    for(int k=0 ; k<first0.length ; k++){ //for loop  each symbol in the first set the start symbol
        if(first0[k].equals("lambda"))//check if the symbol is lambda
            containsLambda = true;
        else{
            int column = getTerminalIndex(first0[k]);//get the column index for the terminals
            int row = 0;
            parsingTable[row][column] = production[0];//set the production rule in the parsing table
        }
    }
    if(containsLambda){//if lambda is in the first set, consider the follow set
        String[]follow = startSymbol.getFollow(); //get the follow set of  start symbol
        for(int k=0 ; k<follow.length ; k++){  //for loop through each symbol in follow set
            int column = getTerminalIndex(follow[k]); //get the column index for  terminal symbol
            int row = 0;
            parsingTable[row][column] = production[0]; //set the production rule in the parsing table
        }
    }


    //fill the reset of parsing table
    for(int i=1 ; i<production.length ; i++){
        String leftSide = production[i].getLeftSide();//get the left side of the production rule
        String[]rightSide = production[i].getRightSide().split("@");//get the right side of the production rule split("@")
        boolean containLambda = false;
        for(int j=0 ; j<rightSide.length ; j++){  //for loop through each symbol in the right side
            containLambda = false;
            if(isTerminal(rightSide[j]) == 1){ //  if the symbol is a terminal
                int column = getTerminalIndex(rightSide[j]);//get the column index for  terminal symbol
                int row = getNonTerminalIndex(leftSide);
                parsingTable[row][column] = production[i];//set the production rule in the parsing table
                break;

            }
            else if(rightSide[j].equals("lambda")){  //if the symbol is lambda
                FirstFollow leftSymbol = findNonterminal(leftSide);
                String[]follow = leftSymbol.getFollow();//get the follow set of the left symbol
                for(int k=0 ; k<follow.length ; k++){   //for loop through each symbol in the FOLLOW set
                    int column = getTerminalIndex(follow[k]);//get the column index for  terminal symbol
                    int row = getNonTerminalIndex(leftSide);
                    parsingTable[row][column] = production[i];//set the production rule in the parsing table
                }
            }
            else{ //if the symbol is a nonterminal
                FirstFollow nextSymbol = findNonterminal(rightSide[j]);
                String[]firstSet = nextSymbol.getFirst();   //get the first set of the nonterminal
                for(int l=0 ; l<firstSet.length; l++){    //for loop through each symbol in the first set
                    if(firstSet[l].equals("lambda")) //if check if the symbol is lambda
                        containLambda = true;
                    else{
                        int column = getTerminalIndex(firstSet[l]);//get the column index for  terminal symbol
                        int row = getNonTerminalIndex(leftSide);
                        parsingTable[row][column] = production[i];//set the production rule in the parsing table
                    }

                }
                if(!containLambda)//if no lambda, break  loop
                    break;
                if(j == rightSide.length-1){  //check if at the end of the right side and contains lambda
                    FirstFollow leftSymbol = findNonterminal(leftSide);
                    String[]follow = leftSymbol.getFollow();
                    for(int k=0 ; k<follow.length ; k++){
                        int column = getTerminalIndex(follow[k]);
                        int row = getNonTerminalIndex(leftSide);
                        parsingTable[row][column] = production[i];
                    }

                }



            }
        }

    }
    for(int i=0; i<parsingTable.length ; i++){    // fill empty cells in the parsing table with error production rules
        for(int j=0 ; j<parsingTable[0].length ; j++){
            if(parsingTable[i][j] == null){
                ProductionRules p = new ProductionRules("","Error");
                parsingTable[i][j] = p;
            }

        }
    }


}

    public int getTerminalIndex(String terminal) {// method to get Terminal Index
        for (int i = 0; i < terminals.length; i++) {
            if (terminal.equals(terminals[i])) {
                return i;
            }
        }
        return -1;
    }
    public int getNonTerminalIndex(String nonteminal) {//method to get nonTerminal Index
        for (int i = 0; i < nonTerminals.length; i++) {
            if (nonTerminals[i].equals(nonteminal)) {
                return i;
            }
        }
        return -1;
    }

    public void computeFollow(){// method to compute Follow
        boolean mustcontinue;

        do{
            mustcontinue = false;

            for(int i=0 ; i<production.length ; i++){

                String[]rightSide = production[i].getRightSide().split("@");

                for(int j=0 ; j<rightSide.length ; j++){
                    String symbol = rightSide[j];
                    //skip terminals and lambda
                    if(isTerminal(symbol) == 1)continue;
                    if(symbol.equals("lambda"))continue;

                    FirstFollow currentSymbol = findNonterminal(symbol);
                    if(j+1 == rightSide.length){
                        if(addLeftSideFollow(currentSymbol,production[i]) == 1)
                            mustcontinue = true;
                    }
                    else if(isTerminal(rightSide[j+1]) == 1){
                        if(currentSymbol.addNewFollow(rightSide[j+1]) == 1)
                            mustcontinue = true;
                    }else{
                        boolean containLambda = false;
                        for(int k=j+1 ; k<rightSide.length ; k++){
                            containLambda = false;
                            if(isTerminal(rightSide[k]) == 1){
                                if(currentSymbol.addNewFollow(rightSide[k]) == 1)
                                    mustcontinue = true;
                                break;
                            }
                            else{

                                FirstFollow nextSymbol = findNonterminal(rightSide[k]);
                                String[]firstSet = nextSymbol.getFirst();
                                for(int l=0 ; l<firstSet.length; l++){

                                    if(firstSet[l].equals("lambda"))
                                        containLambda = true;
                                    else{
                                        if(currentSymbol.addNewFollow(firstSet[l]) == 1)
                                            mustcontinue = true;
                                    }

                                }
                                if(!containLambda)
                                    break;
                                if(k == rightSide.length-1)
                                    if(addLeftSideFollow(currentSymbol,production[i])==1)
                                        mustcontinue = true;
                            }

                        }

                    }


                }

            }

        }while(mustcontinue);


    }

    public void computeFirst(){// method compute First
        boolean mustcontinue;

        do{
            mustcontinue = false;

            for(int i=0 ; i<production.length ; i++){
                String[]rightSide = production[i].getRightSide().split("@");
                String leftSide = production[i].getLeftSide();
                FirstFollow leftSymbol = findNonterminal(leftSide);
                if(rightSide[0].equals("lambda")){
                    if(leftSymbol.addNewFirst(rightSide[0])==1)
                        mustcontinue = true;

                }
                else{

                    for(int j=0 ; j<rightSide.length ; j++){

                        if(isTerminal(rightSide[j]) == 1){
                            if(leftSymbol.addNewFirst(rightSide[j]) == 1)
                                mustcontinue = true;

                            break;
                        }
                        else{
                            boolean containLambda = false;
                            FirstFollow currentSymbol = findNonterminal(rightSide[j]);
                            String[]first = currentSymbol.getFirst();
                            for(int k=0 ; k<first.length; k++){
                                if(first[k].equals("lambda"))
                                    containLambda = true;
                                else{
                                    if(leftSymbol.addNewFirst(first[k]) == 1)
                                        mustcontinue = true;
                                }
                            }
                            if(!containLambda)
                                break;
                            if(j == rightSide.length-1)
                                if(leftSymbol.addNewFirst("lambda") == 1)
                                    mustcontinue = true;
                        }
                    }
                }
            }
        }while(mustcontinue);

    }

    public int isTerminal(String symbol){// method to check if is terminal
        for(int i=0 ; i<terminals.length ; i++){
            if(symbol.equals(terminals[i]))
                return 1;
        }
        return 0;
    }
    public FirstFollow findNonterminal(String symbol){// method to find nonterminal
        FirstFollow tmp = null;
        for(int k=0 ; k<firstFollowSets.length ; k++){
            if(symbol.equals(firstFollowSets[k].getNonTerminal())){
                tmp = firstFollowSets[k];
                break;
            }
        }
        return tmp;
    }
    public int addLeftSideFollow(FirstFollow currentSymbol, ProductionRules currentProduction){// method to add Left Side Follows
        int mustcontinue = 0;
        String leftSide = currentProduction.getLeftSide();
        FirstFollow leftSymbol = findNonterminal(leftSide);
        String[]follow = leftSymbol.getFollow();
        if(currentSymbol.addAllFollow(follow)== 1)
            mustcontinue = 1;
        return mustcontinue;
    }






public static String printParsingTable() {
    StringBuilder sb = new StringBuilder();

    // Print column headers (terminals)
    sb.append("+--------------------+");
    for (int i = 0; i < terminals.length; i++) {
        sb.append("--------------------+");
    }
    sb.append("\n");

    sb.append(String.format("| %-18s |", ""));
    for (String terminal : terminals) {
        sb.append(String.format("%-19s|", terminal));
    }
    sb.append("\n");

    // Print horizontal line
    sb.append("+--------------------+");
    for (int i = 0; i < terminals.length; i++) {
        sb.append("--------------------+");
    }
    sb.append("\n");

    // Print rows (non-terminals) along with the corresponding production rules
    for (int i = 0; i < nonTerminals.length; i++) {
        sb.append(String.format("| %-18s |", nonTerminals[i]));
        for (int j = 0; j < terminals.length; j++) {
            ProductionRules rule = parsingTable[i][j];
            sb.append(String.format(" %-18s |", rule.getRightSide()));
        }
        sb.append("\n");

        // Print horizontal line
        sb.append("+--------------------+");
        for (int k = 0; k < terminals.length; k++) {
            sb.append("--------------------+");
        }
        sb.append("\n");
    }

    return sb.toString();
}
//public static String printParsingTable() {// method for  print Parsing Table
//    StringBuilder sb = new StringBuilder();
//
//    // Print column headers (terminals)
//    sb.append("                      "); // Initial spacing for the first column
//    for (String terminal : terminals) {
//        sb.append(String.format("%-19s", terminal));
//    }
//    sb.append("\n\n"); // Add space after headers
//
//    // Print rows (non-terminals) along with the corresponding production rules
//    for (int i = 0; i < nonTerminals.length; i++) {
//        sb.append(String.format("%-18s ", nonTerminals[i])); // Non-terminal with space
//        for (int j = 0; j < terminals.length; j++) {
//            ProductionRules rule = parsingTable[i][j];
//            sb.append(String.format("%-19s", rule.getRightSide()));
//        }
//        sb.append("\n\n"); // Add space between rows
//    }

//    return sb.toString();
//}


//public static String printParsingTable() {
//    StringBuilder sb = new StringBuilder();
//
//    // Append column headers (terminals)
//    sb.append(String.format("%-20s", ""));
//    for (String terminal : terminals) {
//        sb.append(String.format("%-20s", terminal));
//    }
//    sb.append("\n");
//
//    // Append rows (non-terminals) along with the corresponding production rules
//    for (int i = 0; i < nonTerminals.length; i++) {
//        sb.append(String.format("%-20s", nonTerminals[i]));
//        for (int j = 0; j < terminals.length; j++) {
//            ProductionRules rule = parsingTable[i][j];
//            if (rule != null) {
//                sb.append(String.format("%-20s", rule.getRightSide()));
//            } else {
//                sb.append(String.format("%-20s", ""));
//            }
//        }
//        sb.append("\n");
//    }
//
//    return sb.toString();
//}



//    public static void main(String[] args) {
//        Parsing_Table parsingTable = new Parsing_Table();
//        parsingTable.buildParsingTable(parsingTable.production);
//        parsingTable.printParsingTable();
//    }




}

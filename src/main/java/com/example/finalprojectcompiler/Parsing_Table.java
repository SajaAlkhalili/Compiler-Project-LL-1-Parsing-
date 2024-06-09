package com.example.finalprojectcompiler;

import java.util.Arrays;

public class Parsing_Table {
    String[] nonTerminals = {
            "module-decl", "module-heading", "block", "declarations",
            "const-decl", "const-list", "var-decl", "var-list", "var-item", "name-list",
            "more-names", "data-type", "procedure-decl", "procedure-heading", "stmt-list",
            "statement", "ass-stmt", "exp", "exp-prime", "term", "term-prime", "factor",
            "add-oper", "mul-oper", "read-stmt", "write-stmt", "write-list",
            "more-write-value", "write-item", "if-stmt", "else-part", "while-stmt",
            "loop-stmt", "exit-stmt", "call-stmt", "condition", "relational-oper",
            "name-value", "value"};

    String[] terminals = {"module", ";", "begin", "const", "name", "end", "=", "var", ":",
            ",", "integer", "real", "char", "procedure", "(", ")", "+", "-", "*",
            "/", "mod", "div", "readint", "readreal", "readchar", "readln", "writeint", "writereal", "writechar",
            "writeln", "if", "then", "else", "while", "do", "loop", "exit", "until",
            "call", "|=", "<", "<=", ">", ">=", ":=", "integer-value",
            "real-value","."};
    public ProductionRules[] production = new ProductionRules[80];
    public ProductionRules[][] parsingTable = new ProductionRules[40][50];
    public FirstAndFollow[] firstFollowSets = new FirstAndFollow[40];
    public Parsing_Table(){
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


        firstFollowSets[0] = new FirstAndFollow("module-decl");
        firstFollowSets[1] = new FirstAndFollow("module-heading");
        firstFollowSets[2] = new FirstAndFollow("block");
        firstFollowSets[3] = new FirstAndFollow("declarations");
        firstFollowSets[4] = new FirstAndFollow("const-decl");
        firstFollowSets[5] = new FirstAndFollow("const-list");
        firstFollowSets[6] = new FirstAndFollow("var-decl");
        firstFollowSets[7] = new FirstAndFollow("var-list");
        firstFollowSets[8] = new FirstAndFollow("var-list");
        firstFollowSets[9] = new FirstAndFollow("var-item");
        firstFollowSets[10] = new FirstAndFollow("name-list");
        firstFollowSets[11] = new FirstAndFollow("more-names");
        firstFollowSets[12] = new FirstAndFollow("data-type");
        firstFollowSets[13] = new FirstAndFollow("procedure-decl");
        firstFollowSets[14] = new FirstAndFollow("procedure-heading");
        firstFollowSets[15] = new FirstAndFollow("stmt-list");
        firstFollowSets[16] = new FirstAndFollow("statement");
        firstFollowSets[17] = new FirstAndFollow("ass-stmt");
        firstFollowSets[18] = new FirstAndFollow("exp");
        firstFollowSets[19] = new FirstAndFollow("exp-prime");
        firstFollowSets[20] = new FirstAndFollow("term");
        firstFollowSets[21] = new FirstAndFollow("term-prime");
        firstFollowSets[22] = new FirstAndFollow("factor");
        firstFollowSets[23] = new FirstAndFollow("add-oper");
        firstFollowSets[24] = new FirstAndFollow("mul-oper");
        firstFollowSets[25] = new FirstAndFollow("read-stmt");
        firstFollowSets[26] = new FirstAndFollow("write-stmt");
        firstFollowSets[27] = new FirstAndFollow("write-list");
        firstFollowSets[28] = new FirstAndFollow("more-write-value");
        firstFollowSets[29] = new FirstAndFollow("write-item");
        firstFollowSets[30] = new FirstAndFollow("if-stmt");
        firstFollowSets[31] = new FirstAndFollow("else-part");
        firstFollowSets[32] = new FirstAndFollow("while-stmt");
        firstFollowSets[33] = new FirstAndFollow("loop-stmt");
        firstFollowSets[34] = new FirstAndFollow("exit-stmt");
        firstFollowSets[35] = new FirstAndFollow("call-stmt");
        firstFollowSets[36] = new FirstAndFollow("condition");
        firstFollowSets[37] = new FirstAndFollow("relational-oper");
        firstFollowSets[38] = new FirstAndFollow("name-value");
        firstFollowSets[39] = new FirstAndFollow("value");

        computeFirst();
        computeFollow();
        buildParsingTable(production);
    }
//
//public void buildParsingTable(ProductionRules[] production) {
//    // Initialize first row in parsing table
//    FirstAndFollow startSymbol = findNonterminal("module-decl");
//    String[] first0 = startSymbol.getFirst();
//    boolean containsLambda = Arrays.asList(first0).contains("lambda");
//
//    for (String s : first0) {
//        if (!s.equals("lambda")) {
//            int column = getTerminalIndex(s);
//            int row = getNonTerminalIndex("module-decl");
//            parsingTable[row][column] = production[0];
//        }
//    }
//    if (containsLambda) {
//        String[] follow = startSymbol.getFollow();
//        for (String s : follow) {
//            int column = getTerminalIndex(s);
//            int row = getNonTerminalIndex("module-decl");
//            parsingTable[row][column] = production[0];
//        }
//    }
//
//    // Fill the rest of the parsing table
//    for (int i = 1; i < production.length; i++) {
//        String leftSide = production[i].getLeftSide();
//        String[] rightSide = production[i].getRightSide().split("@");
//        boolean containLambda = false;
//        for (String s : rightSide) {
//            containLambda = false;
//            if (isTerminal(s) == 1) {
//                int column = getTerminalIndex(s);
//                int row = getNonTerminalIndex(leftSide);
//                parsingTable[row][column] = production[i];
//                break;
//            } else if (s.equals("lambda")) {
//                FirstAndFollow leftSymbol = findNonterminal(leftSide);
//                String[] follow = leftSymbol.getFollow();
//                for (String value : follow) {
//                    int column = getTerminalIndex(value);
//                    int row = getNonTerminalIndex(leftSide);
//                    parsingTable[row][column] = production[i];
//                }
//            } else {
//                FirstAndFollow nextSymbol = findNonterminal(s);
//                String[] firstSet = nextSymbol.getFirst();
//                for (String value : firstSet) {
//                    if (value.equals("lambda")) {
//                        containLambda = true;
//                    } else {
//                        int column = getTerminalIndex(value);
//                        int row = getNonTerminalIndex(leftSide);
//                        parsingTable[row][column] = production[i];
//                    }
//                }
//                if (!containLambda) break;
//            }
//        }
//    }
//
//    // Fill in any remaining empty cells with error productions
//    for (int i = 0; i < parsingTable.length; i++) {
//        for (int j = 0; j < parsingTable[i].length; j++) {
//            if (parsingTable[i][j] == null) {
//                parsingTable[i][j] = new ProductionRules("", "Error");
//            }
//        }
//    }
//}
//    public int getTerminalIndex(String terminal) {
//        for (int i = 0; i < terminals.length; i++) {
//            if (terminal.equals(terminals[i])) {
//                return i;
//            }
//        }
//        return -1;
//    }
//    public int getNonTerminalIndex(String nonteminal) {
//        for (int i = 0; i < nonTerminals.length; i++) {
//            if (nonTerminals[i].equals(nonteminal)) {
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    public void computeFollow(){
//        boolean mustcontinue;
//
//        do{
//            mustcontinue = false;
//
//            for(int i=0 ; i<production.length ; i++){
//
//                String[]rightSide = production[i].getRightSide().split("@");
//
//                for(int j=0 ; j<rightSide.length ; j++){
//                    String symbol = rightSide[j];
//
//                    if(isTerminal(symbol) == 1)continue;
//                    if(symbol.equals("lambda"))continue;
//
//                    FirstAndFollow currentSymbol = findNonterminal(symbol);
//                    if(j+1 == rightSide.length){
//                        if(addLeftSideFollow(currentSymbol,production[i]) == 1)
//                            mustcontinue = true;
//                    }
//                    else if(isTerminal(rightSide[j+1]) == 1){
//                        if(currentSymbol.addNewFollow(rightSide[j+1]) == 1)
//                            mustcontinue = true;
//                    }else{
//                        boolean containLambda = false;
//                        for(int k=j+1 ; k<rightSide.length ; k++){
//                            containLambda = false;
//                            if(isTerminal(rightSide[k]) == 1){
//                                if(currentSymbol.addNewFollow(rightSide[k]) == 1)
//                                    mustcontinue = true;
//                                break;
//                            }
//                            else{
//
//                                FirstAndFollow nextSymbol = findNonterminal(rightSide[k]);
//                                String[]firstSet = nextSymbol.getFirst();
//                                for(int l=0 ; l<firstSet.length; l++){
//
//                                    if(firstSet[l].equals("lambda"))
//                                        containLambda = true;
//                                    else{
//                                        if(currentSymbol.addNewFollow(firstSet[l]) == 1)
//                                            mustcontinue = true;
//                                    }
//
//                                }
//                                if(!containLambda)
//                                    break;
//                                if(k == rightSide.length-1)
//                                    if(addLeftSideFollow(currentSymbol,production[i])==1)
//                                        mustcontinue = true;
//                            }
//
//                        }
//
//                    }
//
//
//                }
//
//            }
//
//        }while(mustcontinue);
//
//
//    }
//
//public void computeFirst() {
//    boolean mustcontinue;
//
//    do {
//        mustcontinue = false;
//
//        for (int i = 0; i < production.length; i++) {
//            String[] rightSide = production[i].getRightSide().split("@");
//            String leftSide = production[i].getLeftSide();
//            FirstAndFollow leftSymbol = findNonterminal(leftSide);
//            if (rightSide[0].equals("lambda")) {
//                if (leftSymbol.addNewFirst(rightSide[0]) == 1)
//                    mustcontinue = true;
//            } else {
//                for (int j = 0; j < rightSide.length; j++) {
//                    if (isTerminal(rightSide[j]) == 1) {
//                        if (leftSymbol.addNewFirst(rightSide[j]) == 1)
//                            mustcontinue = true;
//                        break;
//                    } else {
//                        boolean containLambda = false;
//                        FirstAndFollow currentSymbol = findNonterminal(rightSide[j]);
//                        if (currentSymbol == null) {
//                            System.err.println("Error: Non-terminal '" + rightSide[j] + "' not found in firstFollowSets.");
//                            continue;
//                        }
//                        String[] first = currentSymbol.getFirst();
//                        for (int k = 0; k < first.length; k++) {
//                            if (first[k].equals("lambda"))
//                                containLambda = true;
//                            else {
//                                if (leftSymbol.addNewFirst(first[k]) == 1)
//                                    mustcontinue = true;
//                            }
//                        }
//                        if (!containLambda)
//                            break;
//                        if (j == rightSide.length - 1)
//                            if (leftSymbol.addNewFirst("lambda") == 1)
//                                mustcontinue = true;
//                    }
//                }
//            }
//        }
//    } while (mustcontinue);
//}
//
//
//    public int isTerminal(String symbol){
//        for(int i=0 ; i<terminals.length ; i++){
//            if(symbol.equals(terminals[i]))
//                return 1;
//        }
//        return 0;
//    }
////    public FirstAndFollow findNonterminal(String symbol){
////        FirstAndFollow tmp = null;
////        for(int k=0 ; k<firstFollowSets.length ; k++){
////            if(symbol.equals(firstFollowSets[k].getNonTerminal())){
////                tmp = firstFollowSets[k];
////                break;
////            }
////        }
////        return tmp;
////    }
//public FirstAndFollow findNonterminal(String symbol) {
//    for (int k = 0; k < firstFollowSets.length; k++) {
//        if (symbol.equals(firstFollowSets[k].getNonTerminal())) {
//            return firstFollowSets[k];
//        }
//    }
//    System.err.println("Error: Non-terminal '" + symbol + "' not found in firstFollowSets.");
//    return null;
//}
//
//    public int addLeftSideFollow(FirstAndFollow currentSymbol, ProductionRules currentProduction){
//        int mustcontinue = 0;
//        String leftSide = currentProduction.getLeftSide();
//        FirstAndFollow leftSymbol = findNonterminal(leftSide);
//        String[]follow = leftSymbol.getFollow();
//        if(currentSymbol.addAllFollow(follow)== 1)
//            mustcontinue = 1;
//        return mustcontinue;
//    }
//
//    public void printParsingTable() {
//        int columnWidth = 20;
//        String horizontalSeparator = "-".repeat(columnWidth);
//        String verticalSeparator = "|";
//        String crossSeparator = "+";
//
//        // Print the header row
//        System.out.print(String.format("%-" + columnWidth + "s", "NonTerminal/Terminal"));
//        for (String terminal : terminals) {
//            System.out.print(verticalSeparator + String.format("%-" + columnWidth + "s", terminal));
//        }
//        System.out.println(verticalSeparator);
//
//        // Print a line after the header
//        System.out.print(crossSeparator + horizontalSeparator);
//        for (int i = 0; i < terminals.length; i++) {
//            System.out.print(crossSeparator + horizontalSeparator);
//        }
//        System.out.println(crossSeparator);
//
//        // Print the rest of the rows
//        for (int i = 0; i < nonTerminals.length; i++) {
//            System.out.print(String.format("%-" + columnWidth + "s", nonTerminals[i]));
//            for (int j = 0; j < terminals.length; j++) {
//                System.out.print(verticalSeparator + String.format("%-" + columnWidth + "s", parsingTable[i][j] == null ? "" : parsingTable[i][j].getRightSide()));
//            }
//            System.out.println(verticalSeparator);
//
//            // Print a line after each row
//            System.out.print(crossSeparator + horizontalSeparator);
//            for (int j = 0; j < terminals.length; j++) {
//                System.out.print(crossSeparator + horizontalSeparator);
//            }
//            System.out.println(crossSeparator);
//        }
//    }
public void buildParsingTable(ProductionRules[] production){
    //initialize first row in parsing table
    FirstAndFollow startSymbol = findNonterminal("module-decl");
    String[]first0 = startSymbol.getFirst();
    boolean containsLambda = false;
    for(int k=0 ; k<first0.length ; k++){
        if(first0[k].equals("lambda"))
            containsLambda = true;
        else{
            int column = getTerminalIndex(first0[k]);
            int row = 0;
            parsingTable[row][column] = production[0];
        }
    }
    if(containsLambda){
        String[]follow = startSymbol.getFollow();
        for(int k=0 ; k<follow.length ; k++){
            int column = getTerminalIndex(follow[k]);
            int row = 0;
            parsingTable[row][column] = production[0];
        }
    }


    //fill the reset of parsing table
    for(int i=1 ; i<production.length ; i++){
        String leftSide = production[i].getLeftSide();
        String[]rightSide = production[i].getRightSide().split("@");
        boolean containLambda = false;
        for(int j=0 ; j<rightSide.length ; j++){
            containLambda = false;
            if(isTerminal(rightSide[j]) == 1){
                int column = getTerminalIndex(rightSide[j]);
                int row = getNonTerminalIndex(leftSide);
                parsingTable[row][column] = production[i];
                break;

            }
            else if(rightSide[j].equals("lambda")){
                FirstAndFollow leftSymbol = findNonterminal(leftSide);
                String[]follow = leftSymbol.getFollow();
                for(int k=0 ; k<follow.length ; k++){
                    int column = getTerminalIndex(follow[k]);
                    int row = getNonTerminalIndex(leftSide);
                    parsingTable[row][column] = production[i];
                }
            }
            else{
                FirstAndFollow nextSymbol = findNonterminal(rightSide[j]);
                String[]firstSet = nextSymbol.getFirst();
                for(int l=0 ; l<firstSet.length; l++){
                    if(firstSet[l].equals("lambda"))
                        containLambda = true;
                    else{
                        int column = getTerminalIndex(firstSet[l]);
                        int row = getNonTerminalIndex(leftSide);
                        parsingTable[row][column] = production[i];
                    }

                }
                if(!containLambda)
                    break;
                if(j == rightSide.length-1){
                    FirstAndFollow leftSymbol = findNonterminal(leftSide);
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
    for(int i=0; i<parsingTable.length ; i++){
        for(int j=0 ; j<parsingTable[0].length ; j++){
            if(parsingTable[i][j] == null){
                ProductionRules p = new ProductionRules("","Error");
                parsingTable[i][j] = p;
            }

        }
    }


}

    public int getTerminalIndex(String terminal) {
        for (int i = 0; i < terminals.length; i++) {
            if (terminal.equals(terminals[i])) {
                return i;
            }
        }
        return -1;
    }
    public int getNonTerminalIndex(String nonteminal) {
        for (int i = 0; i < nonTerminals.length; i++) {
            if (nonTerminals[i].equals(nonteminal)) {
                return i;
            }
        }
        return -1;
    }

    public void computeFollow(){
        boolean mustcontinue;

        do{
            mustcontinue = false;

            for(int i=0 ; i<production.length ; i++){

                String[]rightSide = production[i].getRightSide().split("@");

                for(int j=0 ; j<rightSide.length ; j++){
                    String symbol = rightSide[j];

                    if(isTerminal(symbol) == 1)continue;
                    if(symbol.equals("lambda"))continue;

                    FirstAndFollow currentSymbol = findNonterminal(symbol);
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

                                FirstAndFollow nextSymbol = findNonterminal(rightSide[k]);
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

    public void computeFirst(){
        boolean mustcontinue;

        do{
            mustcontinue = false;

            for(int i=0 ; i<production.length ; i++){
                String[]rightSide = production[i].getRightSide().split("@");
                String leftSide = production[i].getLeftSide();
                FirstAndFollow leftSymbol = findNonterminal(leftSide);
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
                            FirstAndFollow currentSymbol = findNonterminal(rightSide[j]);
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

    public int isTerminal(String symbol){
        for(int i=0 ; i<terminals.length ; i++){
            if(symbol.equals(terminals[i]))
                return 1;
        }
        return 0;
    }
    public FirstAndFollow findNonterminal(String symbol){
        FirstAndFollow tmp = null;
        for(int k=0 ; k<firstFollowSets.length ; k++){
            if(symbol.equals(firstFollowSets[k].getNonTerminal())){
                tmp = firstFollowSets[k];
                break;
            }
        }
        return tmp;
    }
    public int addLeftSideFollow(FirstAndFollow currentSymbol, ProductionRules currentProduction){
        int mustcontinue = 0;
        String leftSide = currentProduction.getLeftSide();
        FirstAndFollow leftSymbol = findNonterminal(leftSide);
        String[]follow = leftSymbol.getFollow();
        if(currentSymbol.addAllFollow(follow)== 1)
            mustcontinue = 1;
        return mustcontinue;
    }





    public void printParsingTable() {
        // Print column headers (terminals)
        System.out.print("+--------------------+");
        for (int i = 0; i < terminals.length; i++) {
            System.out.print("--------------------+");
        }
        System.out.println();

        System.out.print("|");
        System.out.printf("%-19s", "");
        System.out.print("|");
        for (String terminal : terminals) {
            System.out.printf("%-19s|", terminal);
        }
        System.out.println();

        // Print horizontal line
        System.out.print("+--------------------+");
        for (int i = 0; i < terminals.length; i++) {
            System.out.print("--------------------+");
        }
        System.out.println();

        // Print rows (non-terminals) along with the corresponding production rules
        for (int i = 0; i < nonTerminals.length; i++) {
            System.out.printf("| %-18s |", nonTerminals[i]);
            for (int j = 0; j < terminals.length; j++) {
                ProductionRules rule = parsingTable[i][j];
                System.out.printf(" %-18s |", rule.getRightSide());
            }
            System.out.println();

            // Print horizontal line
            System.out.print("+--------------------+");
            for (int k = 0; k < terminals.length; k++) {
                System.out.print("--------------------+");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Parsing_Table parsingTable = new Parsing_Table();
        parsingTable.buildParsingTable(parsingTable.production);
        parsingTable.printParsingTable();
    }


//    public static void main(String[] args) {
//        Parsing_Table parsingTable = new Parsing_Table();
//
//        // Build the parsing table
//        parsingTable.buildParsingTable(parsingTable.production);
//
//        // Print the parsing table
//        parsingTable.printParsingTable();
//    }


}

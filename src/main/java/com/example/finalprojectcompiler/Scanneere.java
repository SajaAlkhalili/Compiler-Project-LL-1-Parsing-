package com.example.finalprojectcompiler;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Scanneere {

    private String[] symbols = {//array of symbols
            ";", "=", "<", ">", "+", "/", "*", "-", ":", ",", "(", ")",
            ":=", "<=", ">=", "|="};

    private Tokens[] specialSympols = {//array of specialSympols
            new Tokens(";", 100),
            new Tokens("=", 101),
            new Tokens("(", 103),
            new Tokens(")", 104),
            new Tokens("+", 105),
            new Tokens("-", 106),
            new Tokens("*", 107),
            new Tokens("/", 108),
            new Tokens(".", 109),
            new Tokens("<", 110),
            new Tokens(">", 112),
            new Tokens("|=", 113),
            new Tokens(":=", 114),
            new Tokens("<=", 115),
            new Tokens(">=", 116),
            new Tokens(",", 117)
    };

    private ArrayList<Tokens> listOfTokens;//arraylist of listOfTokens

    private Tokens[] reservedWords = {//array of reservedWords
            new Tokens("module", 1),
            new Tokens("begin", 2),
            new Tokens("end", 3),
            new Tokens("const", 4),
            new Tokens("var", 5),
            new Tokens("integer", 6),
            new Tokens("real", 7),
            new Tokens("char", 8),
            new Tokens("procedure", 9),
            new Tokens("mod", 10),
            new Tokens("div", 11),
            new Tokens("readint", 12),
            new Tokens("readreal", 13),
            new Tokens("readchar", 14),
            new Tokens("readln", 15),
            new Tokens("writeint", 16),
            new Tokens("writereal", 17),
            new Tokens("writechar", 18),
            new Tokens("writeln", 19),
            new Tokens("if", 20),
            new Tokens("then", 21),
            new Tokens("else", 23),
            new Tokens("while", 24),
            new Tokens("do", 25),
            new Tokens("loop", 26),
            new Tokens("until", 27),
            new Tokens("exit", 28),
            new Tokens("call", 29)};

    private String sourceCode;// Private  to store the source code

    public Scanneere(String fileContents) { // constructor takes the source code
        this.sourceCode = fileContents;
        this.listOfTokens = new ArrayList<>();
    }

    public Scanneere() {
        this.listOfTokens = new ArrayList<>();
    }// default constructor


public ArrayList<Tokens> getTokens(){  //method  get the list of tokens from the source code
    int tokenLine = 1;
    StringTokenizer tokenizer = new StringTokenizer(sourceCode,"\n");

    while(tokenizer.hasMoreTokens()){
        String currentLine = tokenizer.nextToken().trim();

        if(!currentLine.equals("")){
            ArrayList<String> list = this.splitIntoTokens(currentLine);
            for(int i=0 ; i<list.size(); i++){
                String token = list.get(i);
                if (token.length() == 1) {  // If the token is a single character
                    this.selectTokenType(token, tokenLine);
                } else {
                    this.Selecttokentypeandterm(token, tokenLine); // Handle periods within tokens
                }
            }
        }
        tokenLine++;
    }
    return listOfTokens;
}

    private void Selecttokentypeandterm(String token, int line) {//method to select token type and handle terms
        if (token == null) {
            return;  // Handle null token if necessary
        }

        // if the token contains a term, handle it as part of a real number
        if (token.contains(".")) {
            // Split the token by terms
            String[] parts = token.split("\\.");

            // check if both parts are numeric
            if (parts.length == 2 && isInteger(parts[0]) && isInteger(parts[1])) {
                Tokens tmp = new Tokens(token, 204); // Token is a real number
                tmp.setLineNumber(line);
                listOfTokens.add(tmp);
            } else {
                // if not numeric, treat each part as separate tokens
                for (String part : parts) {
                    selectTokenType(part, line);
                }
            }
        } else {
            // if no term, treat the token normally
            selectTokenType(token, line);
        }
    }

private ArrayList<String> splitIntoTokens(String line) {
    ArrayList<String> tokens = new ArrayList<>();
    StringTokenizer tokenizer = new StringTokenizer(line);
    while (tokenizer.hasMoreTokens()) {
        String nextToken = tokenizer.nextToken();
        if (nextToken.endsWith(".")) {
            // remove the term and add the name and the term as separate tokens
            String name = nextToken.substring(0, nextToken.length() - 1);
            tokens.add(name);
            tokens.add(".");
        } else {
            char[] arrOfChars = nextToken.toCharArray();
            String beforeString = "";
            for (int i = 0; i < arrOfChars.length; i++) {
                if (isSymbol(Character.toString(arrOfChars[i]))) {
                    if (!beforeString.equals("")) {
                        tokens.add(beforeString);
                        beforeString = "";
                    }
                    if (i < arrOfChars.length - 1 && isSymbol(Character.toString(arrOfChars[i]) + Character.toString(arrOfChars[i + 1]))) {
                        tokens.add(Character.toString(arrOfChars[i]) + Character.toString(arrOfChars[i + 1]));
                        i++;
                    } else {
                        tokens.add(Character.toString(arrOfChars[i]));
                        if (arrOfChars[i] == '.') {
                            tokens.add(null);  // Add null token after period
                        }
                    }
                } else {
                    beforeString += Character.toString(arrOfChars[i]);
                }
            }
            if (!beforeString.equals("")) {
                tokens.add(beforeString);
            }
        }
    }
    return tokens;
}

    private void selectTokenType(String token, int line) { //method to select type of token and add  to the list
        if (token == null) {
            return;  // handle null token if necessary
        }

        int i;
        if ((i = isSpecial(token)) != -1) {
            Tokens tmp = new Tokens(token, specialSympols[i].code);
            tmp.setLineNumber(line);
            listOfTokens.add(tmp);
        } else if ((i = isReservedWord(token)) != -1) {
            Tokens tmp = new Tokens(token, reservedWords[i].code);
            tmp.setLineNumber(line);
            listOfTokens.add(tmp);
        } else if (isUserDefinedName(token)) {
            Tokens tmp = new Tokens(token, 202);
            tmp.setLineNumber(line);
            listOfTokens.add(tmp);
        } else if (isInteger(token)) {
            Tokens tmp = new Tokens(token, 203);
            tmp.setLineNumber(line);
            listOfTokens.add(tmp);
        } else if (isReal(token)) {
            Tokens tmp = new Tokens(token, 204);
            tmp.setLineNumber(line);
            listOfTokens.add(tmp);
        } else {
            Tokens tmp = new Tokens(token, 404);
            tmp.setLineNumber(line);
            listOfTokens.add(tmp);
        }
    }

    private boolean isInteger(String token) { //method to check if a token is  integer
        for (int i = 0; i < token.length(); i++) {
            if (!Character.isDigit(token.charAt(i))) return false;
        }
        return true;
    }

    private boolean isReal(String token) { // method to check if a token is real
        for (int i = 1; i < token.length(); i++) {
            if (!Character.isDigit(token.charAt(i)) && token.charAt(i) != '.') return false;
        }
        if (!Character.isDigit(token.charAt(0)) || !Character.isDigit(token.charAt(token.length() - 1))) return false;

        int numberOfFloatingPoint = 0;
        for (int i = 0; i < token.length(); i++) {
            if (token.charAt(i) == '.') {
                numberOfFloatingPoint++;
            }
        }
        return numberOfFloatingPoint <= 1;
    }


public boolean isUserDefinedName(String token) {// method to check if a token is UserDefinedName
    if (token == null || token.isEmpty()) {
        return false;
    }
    char firstChar = token.charAt(0);
    if (Character.isLetter(firstChar) || firstChar == '_') {
        for (int i = 1; i < token.length(); i++) {
            char ch = token.charAt(i);
            if (!Character.isLetterOrDigit(ch) && ch != '_') {
                return false;
            }
        }
        return true;
    }
    return false;
}
    public int isReservedWord(String token) {// method to check if a token is ReservedWord
        for (int i = 0; i < reservedWords.length; i++) {
            if (token.equals(reservedWords[i].token)) return i;
        }
        return -1;
    }

    public boolean isSymbol(String token) {// method to check if a token is symbol
        for (String symbol : symbols) {
            if (token.equals(symbol)) return true;
        }
        return false;
    }

    public int isSpecial(String token) {// method to check if a token is special
        for (int i = 0; i < symbols.length; i++) {
            if (token.equals(symbols[i])) return i;
        }
        return -1;
    }

//    public static void main(String[] args) {
//        // Provide the path to your source code file
//        String filePath = "err1.txt";
//
//        // Read the file contents
//        String fileContents = readFile(filePath);
//
//        // Tokenize the file contents
//        CompilerScanner scanner = new CompilerScanner(fileContents);
//        ArrayList<Tokens> tokens = scanner.getTokens();
////        System.out.println(scanner.getTokens());
//
//        // Display the tokens
//        for (Tokens token : tokens) {
//            System.out.println(token);
//        }
//    }
//
//    private static String readFile(String filePath) {
//        StringBuilder content = new StringBuilder();
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                content.append(line).append("\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return content.toString();
//    }
}



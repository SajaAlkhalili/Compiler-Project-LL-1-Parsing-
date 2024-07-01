package com.example.finalprojectcompiler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class LL1Parser {
    public Parsing_Table parser;//  public variable Parsing table
    private Scanneere S;// private variable Tokenizer
    private Stack<String> stack;//  private Stack for parsing
    private ArrayList<Tokens> tokens; // private list of tokens from the source code
    private Tokens[] remainingInput;// private array Tokens left to parse
    public String errorMessage = "";//public variable  Messages to store any errors

    public LL1Parser(Scanneere scanner) {//constructor for LL1Parser class, takes a scanner object as input
        this.S = scanner;
        parse();// Call method parse
    }

    private String parse() {//method to parse the input
        parser = new Parsing_Table();//initialize  parsing table
        stack = new Stack<>();// initialize  stack
        tokens = S.getTokens();//get the tokens from the scanner
        remainingInput = new Tokens[tokens.size() + 1];
        for (int i = 0; i < remainingInput.length - 1; i++) {
            remainingInput[i] = tokens.get(i);
        }
        remainingInput[remainingInput.length - 1] = new Tokens("$", 0);//add end of input marker
        if (tokens.size() > 0) {
            remainingInput[remainingInput.length - 1].setLineNumber(remainingInput[remainingInput.length - 2].getLineNumber());
        }
        stack.push("module-decl");
        int pointer = 0;
        boolean isInvalid = false;

        while (!stack.isEmpty() && pointer < remainingInput.length) {// main loop parsing
            if(pointer+2<remainingInput.length-1&&remainingInput[pointer].token.equals("module-heading")&&
                    remainingInput[pointer+1].token.equals("declarations")&&remainingInput[pointer+2].token.equals("block")&&remainingInput[pointer+3].token.equals("name")&&remainingInput[pointer+3].token.equals(".")){
                String str = remainingInput[pointer].token+""+remainingInput[pointer+1].token+remainingInput[pointer+2].token;
                pointer = pointer+2;
                remainingInput[pointer].token = str;
            }
            System.out.println("Stack top: " + stack.peek() + " | Current token: " + remainingInput[pointer].token);
            //match terminals and nonterminals
            if (stack.peek().trim().equals(remainingInput[pointer].token) ||
                    (stack.peek().trim().equals("name") && remainingInput[pointer].code == 202) ||
                    (stack.peek().trim().equals("const-name") && remainingInput[pointer].code == 202) ||
                    (stack.peek().trim().equals("integer-value") && remainingInput[pointer].code == 203) ||
                    (stack.peek().trim().equals("real-value") && remainingInput[pointer].code == 204)) {
                stack.pop();
                pointer++;
            } else if (parser.isTerminal(stack.peek().trim()) == 1) {
                if (remainingInput[pointer].token.equals("$") && !stack.peek().trim().equals("$") && pointer == remainingInput.length - 1) {
                    errorMessage += "missing token at line: " + remainingInput[pointer].getLineNumber() + " expecting " + stack.peek().trim();
                    break;
                } else if (!remainingInput[pointer].token.equals("$") && stack.peek().trim().equals("$")) {
                    errorMessage += "syntax error at token '" + remainingInput[pointer].token + "' at line: " + remainingInput[pointer].getLineNumber() + " delete '" + remainingInput[pointer].token + "'";
                    break;
                }

                errorMessage += "syntax error at token: '" + remainingInput[pointer].token + "' at line " + remainingInput[pointer].getLineNumber() + " expecting " + stack.peek().trim();
                break;
            } else {//handle nonterminal mismatch
                String currentInput = remainingInput[pointer].token;
                if (!S.isSymbol(currentInput) && S.isReservedWord(currentInput) == -1) {
                    if (S.isUserDefinedName(currentInput)) {
                        if (pointer >= 2 && remainingInput[pointer - 2].token.equals("constants")) {
                            currentInput = "const-name";
                        } else {
                            currentInput = "name";
                        }
                    } else if (remainingInput[pointer].code == 203) {
                        currentInput = "integer-value";
                    } else if (remainingInput[pointer].code == 204) {
                        currentInput = "real-value";
                    } else if (remainingInput[pointer].code == 404) {
                        isInvalid = true;
                    }
                }

                if (isInvalid) {// handle invalid input
                    errorMessage += "syntax error at token: '" + remainingInput[pointer].token + "' at line: " + remainingInput[pointer].getLineNumber();
                    String nonterminal = stack.peek().trim();
                    int row = parser.getNonTerminalIndex(nonterminal);

                    if (row == -1) {
                        errorMessage += "Unknown non-terminal: " + nonterminal;
                        break;
                    }

                    String tmp = " expecting ";
                    for (int i = 0; i < parser.parsingTable[row].length - 1; i++) {
                        if (!parser.parsingTable[row][i].getRightSide().equals("Error")) {
                            tmp += parser.terminals[i] + " or ";
                        }
                    }
                    if (tmp.length() >= 3 && tmp.charAt(tmp.length() - 2) == 'r' && tmp.charAt(tmp.length() - 3) == 'o') {
                        errorMessage += tmp.substring(0, tmp.length() - 3);
                    } else {
                        errorMessage += tmp;
                    }
                    break;
                } else {// look up action in parsing table
                    String nonterminal = stack.peek().trim();
                    int row = parser.getNonTerminalIndex(nonterminal);
                    int column = parser.getTerminalIndex(currentInput);

                    if (row == -1 || column == -1) {
                        errorMessage += "Unknown non-terminal or terminal: " + nonterminal + " " + currentInput;
                        break;
                    }

                    String action = parser.parsingTable[row][column].getRightSide();
                    if (!action.equals("Error")) {
                        pushStack(action);
                    } else {
                        if (remainingInput[pointer].token.equals("$") && pointer == remainingInput.length - 1) {
                            errorMessage += "missing token at line: " + remainingInput[pointer].getLineNumber();
                        } else {
                            errorMessage += "syntax error at token: '" + remainingInput[pointer].token + "' at line: " + remainingInput[pointer].getLineNumber();
                        }
                        String tmp = " expecting ";
                        for (int i = 0; i < parser.parsingTable[row].length - 1; i++) {
                            if (!parser.parsingTable[row][i].getRightSide().equals("Error")) {
                                tmp += parser.terminals[i] + " or ";
                            }
                        }
                        if (tmp.length() >= 3 && tmp.charAt(tmp.length() - 2) == 'r' && tmp.charAt(tmp.length() - 3) == 'o') {
                            errorMessage += tmp.substring(0, tmp.length() - 3);
                        } else {
                            errorMessage += tmp;
                        }
                        break;
                    }
                }
            }
        }

        if (errorMessage.equals("")) {// print success message if no errors
            errorMessage = "parsing Successful, no syntax error";
            System.out.println(errorMessage);
            return errorMessage;
        }
        print(errorMessage);
        return errorMessage;
    }

    private void pushStack(String action) {// method to push elements to the stack based on the give action
        String[] array = action.split("@"); // split("@") the action string
        if (action.equals("lambda")) {// check if the action is "lambda"
            stack.pop();// pop the top element from  stack
        } else {// if the action is not lambda
            stack.pop();// pop the top element from stack
            for (int i = array.length - 1; i >= 0; i--) {// for loop to through the array in reverse order
                stack.push(array[i].trim());// Push each element to the stack after trimming
            }
        }
    }

    void print(String errorMsg) {//method to print errors
        String[] arr = errorMsg.split("expecting");//split the error message by expecting
        if (arr.length == 2) {
            String[] arr2 = arr[1].split("or");//split the second part by or
            System.out.println(arr[0] + " expecting one of the following:");//print the first part of the error massag
            for (String s : arr2) {// for loop to  through the array of expected tokens
                System.out.println(s.trim());//print each expected token after trimming
            }
        } else {
            System.out.println(arr[0]);//print the error message as it
        }
    }

    public static void main(String[] args) {
        String inputFile = "1 (2).txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            Scanneere scanner = new Scanneere(content.toString());
            LL1Parser parser = new LL1Parser(scanner);
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public String getParsingResult() {
        return errorMessage;
    }

}


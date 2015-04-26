import java.util.ArrayList;

/**
 * Created by AhmedA on 2/16/2015.
 */


public class Scanner {
    char[] plainText;
    ArrayList<TokenItem> tokens;

    public static void main(String [] args){
        String x = "read x; if  < x then {do not compute if x <= 0} fact := 1;  repeat fact := fact * x; x := x - 1 until x = 0; write fact {output factorial end} end";
        char[] y = x.toCharArray();
        Scanner z = new Scanner(y);
    }
    Scanner(char[] characters){
        tokens = new ArrayList<TokenItem>();
        this.plainText = characters;
        scanning();
        System.out.println(tokens.size());
    }
    /*
    States
        state 0 start state
        state 1 comment state
        state 2 digit state
        state 3 identifier state
        state 4 assignment state
        state 5 done state
     */
    private boolean scanning(){
        int state = 0;
        int nextState = 0;
        int preState = 0;
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < plainText.length ; i++) {
            if(state == 0){// start state
                if(Character.isSpaceChar(plainText[i])){
                    nextState = 0; // wait in start state
                }
                else if(plainText[i] == '{'){
                    nextState = 1; // go to comment state
                }
                else if(Character.isDigit(plainText[i])){
                    nextState = 2; // go to digit state
                    token.append(plainText[i]);

                }
                else if(Character.isLetter(plainText[i])){
                    nextState = 3; // got to identifier state
                    token.append(plainText[i]);
                }
                else if(plainText[i] == '+' || plainText[i] == '-' || plainText[i] == '=' || plainText[i] == '*' || plainText[i] == '/'){
                    nextState = 5; // go to final state
                    preState = 0;
                    token.append(plainText[i]);
                }
                else if(plainText[i] == ':'){
                    nextState = 4; // go to assignment sate
                    token.append(plainText[i]);
                }
            }
            else if(state == 1){// comment state
                if(plainText[i] == '}'){
                    nextState = 0;
                }
            }
            else if(state == 2){
                if(Character.isDigit(plainText[i])){
                    nextState = 2;
                    token.append(plainText[i]);
                }
                else{
                    i--;
                    nextState = 5;
                    preState = 2;
                }
            }
            else if(state == 3){
                if(Character.isLetter(plainText[i])){
                    nextState = 3;
                    token.append(plainText[i]);
                }
                else{
                    i--;
                    nextState = 5;
                    preState = 3;
                }
            }
            else if(state == 4){
                if(plainText[i] == '='){
                    token.append(plainText[i]);
                    nextState = 5;
                    preState = 4;
                }
                else{
                    nextState = 5;
                    i--;
                }
            }
            else if(state == 5){
                TokenItem tokenItem = new TokenItem();
                tokenItem.setTokenString(token);
                tokenItem.setTokenValue(preState);
                tokenItem.findSymbolType();
                tokenItem.getIntegerValue();
                tokenItem.isAssignment();
                System.out.println(tokenItem.getTokenString() + " " + tokenItem.getTokenValue() + " " + tokenItem.getTokenMeaning());
                tokenItem.isReservedWord();
                tokens.add(tokenItem);
                token.delete(0,token.length());
                nextState = 0;
                i--;
            }
            state = nextState;
        }
        if(token.charAt(0) == '=' | token.charAt(0) == '*' | token.charAt(0) == '/' | token.charAt(0) == '+' | token.charAt(0) == '-'){
            preState = 0;
        }
        if(Character.isLetter(token.charAt(0))){
            preState = 3;
        }
        if(Character.isDigit(token.charAt(0))){
            preState = 2;
        }
        if(token.charAt(0) == ':'){
            preState = 4;
        }
        TokenItem tokenItem = new TokenItem();
        tokenItem.setTokenString(token);
        tokenItem.setIntegerValue(preState);
        tokenItem.findSymbolType();
        tokenItem.getIntegerValue();
        tokenItem.isAssignment();
        tokenItem.isReservedWord();
        tokens.add(tokenItem);
        return true;
    }
}

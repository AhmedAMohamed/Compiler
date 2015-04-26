/**
 * Created by AhmedA on 3/5/2015.
 */
public class TokenItem {

    private StringBuilder tokenString;
    private TokenValue tokenValue;
    private StringBuilder tokenMeaning;
    private int integerValue;

    TokenItem(){
        tokenMeaning = new StringBuilder();
    }
    public StringBuilder getTokenString() {
        return tokenString;
    }

    public TokenValue getTokenValue() {
        return tokenValue;
    }

    public StringBuilder getTokenMeaning() {
        return tokenMeaning;
    }

    public void setTokenMeaning(StringBuilder tokenMeaning) {
        this.tokenMeaning = tokenMeaning;
    }

    public void setIntegerValue(int integerValue) {
        this.integerValue = integerValue;
    }

    public void setTokenString(StringBuilder token){
        this.tokenString = token;
    }
    public boolean isReservedWord(){
        if(tokenString.equals("if") || tokenString.equals("then") || tokenString.equals("else") || tokenString.equals("end") || tokenString.equals("repeat") || tokenString.equals("until") || tokenString.equals("read") || tokenString.equals("write")){
            this.tokenValue = TokenValue.reservedWord;
            return true;
        }
        else{
            this.tokenValue = TokenValue.identifier;
            return true;
        }
    }
    public void setTokenValue(int state){
        if(state == 0){
            tokenValue = TokenValue.symbol;
        }
        else if(state == 2){
            tokenValue = TokenValue.number;
        }
        else if(state == 3){
            tokenValue = TokenValue.identifier;
        }
        else if(state == 4){
            tokenValue = TokenValue.assignment;
        }
    }
    public boolean findSymbolType(){
        try{
            if(tokenValue == TokenValue.symbol) {
                if (tokenString.equals("=")) {
                    tokenMeaning = new StringBuilder("equal");
                }
                else if (tokenString.equals("+")) {
                    tokenMeaning = new StringBuilder("plus");
                }
                else if(tokenString.equals("-")){
                    tokenMeaning = new StringBuilder("minus");
                }
                else if(tokenString.equals("*")){
                    tokenMeaning = new StringBuilder("multiply");
                }
                else if(tokenString.equals("/")){
                    tokenMeaning = new StringBuilder("divide");
                }
                else if(tokenString.equals(">")){
                    tokenMeaning = new StringBuilder("greater than");
                }
                else if(tokenString.equals("<")){
                    tokenMeaning = new StringBuilder("less than");
                }
                else if(tokenString.equals("(")){
                    tokenMeaning = new StringBuilder("left");
                }
                else if(tokenString.equals(")")){
                    tokenMeaning = new StringBuilder("right");
                }
                else if(tokenString.equals(";")){
                    tokenMeaning = new StringBuilder("semi colon");
                }
                else {
                    return false;
                }
                return true;
            }
            else{
                return false;
            }
        }catch (NullPointerException e){
            return false;
        }
        finally {
            return false;
        }
    }
    public boolean getIntegerValue(){
        try {
            if(tokenValue == TokenValue.number){
                integerValue = Integer.parseInt(tokenString.toString());
                tokenMeaning = new StringBuilder("numerical value");
                return true;
            }
            else{
                return false;
            }
        }catch (NullPointerException e){
            return false;
        }finally {
            return false;
        }
    }
    public boolean isAssignment(){
        try {
            if(tokenValue == TokenValue.assignment){
                return true;
            }
            else{
                return false;
            }
        }catch (NullPointerException e){
            return false;
        }finally {
            return false;
        }
    }
}
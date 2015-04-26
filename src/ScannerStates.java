import java.util.*;

/**
 * Created by AhmedA on 2/16/2015.
 */
public class ScannerStates {
    ArrayList<Pair> states;

    ScannerStates(){
        states = new ArrayList<Pair>();
        states.add(new Pair("start",0));
        states.add((new Pair("incommnet",1)));
        states.add(new Pair("inid",2));
        states.add(new Pair("innum",3));
        states.add(new Pair("inassanment",4));
        states.add(new Pair("done",5));
    }
}

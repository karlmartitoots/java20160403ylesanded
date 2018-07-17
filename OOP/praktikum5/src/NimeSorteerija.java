import java.util.Comparator;

public class NimeSorteerija implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        if(o1.equals(o2)){
            return 0;
        } else if(Character.isDigit(o1.substring(0,1).toCharArray()[0]) && Character.isDigit(o2.substring(0,1).toCharArray()[0])){
            return comparePartNumericString(o1, o2);
        } else if(Character.isDigit(o1.substring(0,1).toCharArray()[0])){
            return 1;
        } else if(Character.isDigit(o2.substring(0,1).toCharArray()[0])){
            return -1;
        } else {
            return o1.compareTo(o2);
        }
    }

    public int comparePartNumericString(String initFirst, String initSecond){

        String firstChar = initFirst.substring(0,1);
        String secondChar = initSecond.substring(0,1);


        if(Character.isDigit(firstChar.toCharArray()[0]) && Character.isDigit(secondChar.toCharArray()[0])){
            if(firstChar.equals(secondChar)){
                return comparePartNumericString(initFirst.substring(1), initSecond.substring(1));
            } else if(Integer.parseInt(firstChar) < Integer.parseInt(secondChar)){
                return 1;
            } else{
                return -1;
            }
        } else if(Character.isDigit(firstChar.toCharArray()[0])){
            return 1;
        } else if(Character.isDigit(secondChar.toCharArray()[0])){
            return -1;
        } else{
            return compare(firstChar, secondChar);
        }

    }

}

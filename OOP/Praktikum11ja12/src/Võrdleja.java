public class Võrdleja<T extends Comparable<T>> {

    public T leiaSuurem(T o1, T o2){
        switch(o1.compareTo(o2)){
            case 1:
                return o1;
            case -1:
                return o2;
            case 0:
                return o1;
            default:
                System.out.println("Something is very wrong with the comparator!");
                System.exit(-1);
        }
        return o1;
    }
}

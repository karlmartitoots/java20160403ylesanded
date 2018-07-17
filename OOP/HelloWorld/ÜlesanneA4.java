class summaUksKuniViis{

    static int summaUhestViieni1(){
        int summa1 = 0;
        for(int i = 1;i <= 5;i++){
            summa1 += i;
        }
        return summa1;
    }

    static int summaUhestViieni2(){
        int summa2 = 0;
        int i = 1;
        while (i <= 5){
            summa2 += i;
        }
        return summa2;
    }

    static int summaUhestViieni3(){
        int summa3 = 0;
        int i = 1;
        do{
            summa3 += i;
        } while(i <= 5);
        return summa3;
    }

    public static void main(String[] args){
            int vastus = summaUhestViieni1();
            System.out.println("Summa arvust 1 kuni arvuni 5 on: " + vastus);
        }
        }
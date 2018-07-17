package arvelduskontoKontrollimine;

class Konto{
    private String accountOwnerName;
    private double accountBalance;

    Konto(String accountOwnerName, double accountBalance) throws NegatiivneSummaErind{
        setAccountOwnerName(accountOwnerName);
        setAccountBalance(accountBalance);
    }

    void osta(double itemCost){
        if(accountBalance - itemCost < 0){
            throw new RahaEiJätkuErind(accountBalance);
        }else{
            setAccountBalance(accountBalance - itemCost);
        }
    }

    void paneJuurde(double extraBalance){
        setAccountBalance(accountBalance + extraBalance);
    }

    private void setAccountOwnerName(String accountOwnerName) {
        this.accountOwnerName = accountOwnerName;
    }

    private void setAccountBalance(double accountBalance) throws NegatiivneSummaErind{
        if(accountBalance < 0){
            throw new NegatiivneSummaErind(accountBalance);
        }else {
            this.accountBalance = accountBalance;
        }
    }

    @Override
    public String toString() {
        return "Konto{" +
                "accountOwnerName='" + accountOwnerName + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }
}

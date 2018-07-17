package arvelduskontoKontrollimine;

class RahaEiJätkuErind extends RuntimeException {
    private double insuficientBalance;

    RahaEiJätkuErind(double insuficientBalance) {
        this.insuficientBalance = insuficientBalance;
    }

    double getInsuficientBalance(){
        return insuficientBalance;
    }

}


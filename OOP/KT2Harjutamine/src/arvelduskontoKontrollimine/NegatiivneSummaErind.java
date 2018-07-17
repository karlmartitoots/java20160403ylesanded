package arvelduskontoKontrollimine;

class NegatiivneSummaErind extends RuntimeException{
    private double incorrectValue;

    NegatiivneSummaErind(double incorrectValue) {
        this.incorrectValue = incorrectValue;
    }

    double getIncorrectValue(){
        return incorrectValue;
    }
}

class Kontrolltöö {
    private String autoriNimi;
    private String kontrolltööSisu;

    public Kontrolltöö(String autoriNimi, String kontrolltööSisu) {
        this.autoriNimi = autoriNimi;
        this.kontrolltööSisu = kontrolltööSisu;
    }

    public String getKontrolltööSisu() {
        return kontrolltööSisu;
    }

    public String getAutoriNimi() {
        return autoriNimi;
    }

    @Override
    public String toString() {
        return "Autor: " + this.autoriNimi +
                "\nsisu:\n" + this.kontrolltööSisu;
    }
}

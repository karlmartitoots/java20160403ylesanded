class FormaadiErind extends RuntimeException {
    private String tööKirjeldus;

    FormaadiErind(String tööKirjeldus){
        this.tööKirjeldus = tööKirjeldus;
    }

    String getViganeRida(){
        return tööKirjeldus;
    }

    int getVeaPositsioon(){
        boolean withDateTime = false;
        if(tööKirjeldus.contains("\\|")){
            withDateTime = true;
        }
        //kontrolli semikoolonite arvu
        if(!tööKirjeldus.contains(";")){
            return 1;
        }
        int semikooloniteArv;
        if((semikooloniteArv = tööKirjeldus.split(";").length - 1) > 2){
            return 4;
        }

        //kontrolli tööliiki
        if (checkSecondParameter(withDateTime)) return 2;

        //kontrolli monitori
        if (checkThirdParameter(semikooloniteArv, withDateTime)) return 3;

        //kontrolli LocalDateTime
        if (withDateTime && checkDateTime()) return (semikooloniteArv + 2);
        return -1;//määramata positsiooniga viga
    }

    private boolean checkDateTime() {
        if(!(tööKirjeldus.split("|")[1].contains("T"))){
            return true;
        }
        String[] localDateTimeParts = tööKirjeldus.split("|")[1].split("T");
        return localDateTimeParts[0].split("-").length != 3 || (localDateTimeParts[1].split(":").length != 3);
    }

    private boolean checkThirdParameter(int semikooloniteArv, boolean withDateTime) {
        if(tööKirjeldus.endsWith(";")){
            return true;
        }
        if(withDateTime){
            if(semikooloniteArv == 2 && !(tööKirjeldus.split("|")[0].split(";")[2].equals("monitoriga"))){
                return true;
            }
        }else{
            if(semikooloniteArv == 2 && !(tööKirjeldus.split(";")[2].equals("monitoriga"))){
                return true;
            }
        }
        return false;
    }

    private boolean checkSecondParameter(boolean withDateTime) {
        String tööliik;
        if(!withDateTime){
            if(tööKirjeldus.endsWith(";")){
                return true;
            }
            tööliik = tööKirjeldus.split(";")[1];
        }else{
            tööliik = tööKirjeldus.split("|")[0].split(";")[1];
        }
        return !(tööliik.equals("kiirtöö") || tööliik.equals("tavatöö"));
    }
}

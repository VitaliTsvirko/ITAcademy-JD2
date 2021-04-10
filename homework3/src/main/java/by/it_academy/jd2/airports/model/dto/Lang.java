package by.it_academy.jd2.airports.model.dto;

public enum Lang {
    EN ("en"),
    RU ("ru");

    private final String textCode;

    Lang(String textCode) {
        this.textCode = textCode;
    }

    public String getTextCode(){
        return this.textCode;
    }
}

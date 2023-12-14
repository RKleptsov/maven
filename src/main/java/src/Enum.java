package src;

public enum Enum {
    ThreeNumbersInLine("НЕЛЬЗЯ 3 ЦИФРЫ ПОДРЯД"),LetterZ("НЕЛЬЗЯ Z ЭТО ИМПЕРИАЛИЗМ"),ByZero("НА НОЛЬ ДЕЛИТЬ НЕЛЬЗЯ"),
    NeTotSimvol("СИМВОЛ НЕ ПОДОШЕЛ"), FileNotFound("ФАЙЛ НЕ НАЙДЕН "), StringNotFound("НЕ НАЙДЕНА СТРОКА");
    private String error;
    Enum(String error) {
        this.error = error;
    }
    public String getError() {
        return error;
    }
}
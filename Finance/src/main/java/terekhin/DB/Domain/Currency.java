package terekhin.DB.Domain;

public class Currency {

    private int id;
    private int BankId;
    private int Code;
    private String Name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBankId() {
        return BankId;
    }

    public void setBankId(int bankId) {
        BankId = bankId;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

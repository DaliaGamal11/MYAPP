package calculationEntity;
//This class will represent the database table for storing calculation history
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "calculations")
public class Calculation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number1")
    private int number1;

    @Column(name = "number2")
    private int number2;

    @Column(name = "operation")
    private String operation;

    @Column(name = "result")
    private int result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
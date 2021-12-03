package cs631.bank.Group3.models;

public class Loan {
    int loan_number;
    int account_number;
    double loan_amount;
    double monthly_pay;

    public Loan(int loan_number,int account_number,double loan_amount,double monthly_pay){
        this.loan_number=loan_number;
        this.account_number=account_number;
        this.loan_amount=loan_amount;
        this.monthly_pay=monthly_pay;
    }
}

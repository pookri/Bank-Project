package cs631.bank.Group3.ModelImplemention;

public class AccountModel {
    final String GET_ALL_THE_ACCOUNTS="SELECT account_number,branch_id,acc_type,balance,interest_rate,create_date FROM account ORDER BY create_date";
    final String INSERT_ACCOUNT ="INSERT into account (account_number,branch_id,acc_type,balance,interest_rate,create_date) VALUES(?,?,?,?,?,?)";
    final String GET_PARTICULAR_ACCOUNT_INFO="SELECT account_number,branch_id,acc_type,balance,interest_rate,create_date FROM account WHERE account_number=?";
    final String GET_PARTICULAR_BRANCH_ALL_ACCOUNT_INFO="SELECT account_number,branch_id,acc_type,balance,interest_rate,create_date FROM account WHERE branch_id=?";
    final String COUNT_ACCOUNTS_FOR_ALL_BRANCH="SELECT branch_id,COUNT(account_number)AS Number_of_accounts FROM account GROUP BY branch_id";
    final String COUNT_NoOfAcc_BASED_ON_CREATIONDATE="SELECT create_date,COUNT(account_number) FROM account WHERE create_date=?";
    final String DELETE_ACCOUNT="DELETE FROM account WHERE account_number=?";
    final String UPDATE_ACCOUNT="UPDATE account SET account_number=,branch_id=,acc_type=,balance=,interest_rate=,create_date= WHERE account_number=?";
}

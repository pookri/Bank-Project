export interface Customer{
    ssn: string,
    firstName: string,
    lastName: string,
    aptNum: string,
    streetName: string,
    city: string,
    state: string,
    zipcode: string,
    accountType: string,
    branch: string,
    initialDeposit?: number,
    loanAmount?: number

}
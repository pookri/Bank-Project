export interface CustomerInfo{
    ssn: string,
    firstName: string,
    lastName: string,
    aptNum: string,
    streetName: string,
    city: string,
    state: string,
    zipcode: string,
}

export interface CustomerReq{
    type?: string,
    customers: CustomerInfo[],
    accountType?: string,
    employeeHelping?: string,
    branch?: string,
    initialDeposit?: number,
    loanAmount?: number
}
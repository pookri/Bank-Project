export interface CustomerInfo{
    cssn: string,
    cFirstname: string,
    cLastname: string,
    apartmentNumber: string,
    streetName: string,
    city: string,
    state: string,
    zipCode: string,
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

export interface CustomerTable{
    cssn: string,
    cFirstname:string,
    cLastname:string,
    apartmentNumber:string,
    streetName:string,
    city:string,
    state:string,
    zipCode:string,
    numOfAccounts:number,
    numOfBranches:number,
    personalBanker:string
}
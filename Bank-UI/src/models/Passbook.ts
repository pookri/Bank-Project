export interface Passbook{
    date: Date,
    transactionCode: string,
    transactionName: string,
    debits: number,
    credits: number,
    balance: number
}

export interface PassbookReq{
    customerId: string,
    branchId: string,
    accountId: string
}
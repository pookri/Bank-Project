export interface TransactionReq{
    transactionType: string,
    fromAct: string,
    toAct: string,
    amount: number
}
export interface TransactionResponse{
    transactionAccountNumber: string,
    transactionNum: string,
    transactionDate
    transactionTime: Date | string,
    transactionType: string,
    transactionAmount: number
}
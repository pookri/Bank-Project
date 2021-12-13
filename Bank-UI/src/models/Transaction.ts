export interface TransactionReq{
    transactionType: string,
    fromAct: string,
    toAct: string,
    amount: number
}
export interface TransactionResponse{
    actNum: string,
    transactionNum: string,
    transactionTime: Date | string,
    transactionType: string,
    transactionAmount: number
}
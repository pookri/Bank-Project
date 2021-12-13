import {Passbook, PassbookReq} from "../models/Passbook";
import {Axios, AxiosInstance} from "axios";
import {RestClient} from "./RestClient";
import {SelectOptions} from "../models/SelectOptions";
import {backTopDark} from "naive-ui";
import {BranchResult} from "../models/BranchResult";
import {EmployeeResult} from "../models/EmployeeResult";
import {Customer} from "../models/Customer";
import {TransactionReq, TransactionResponse} from "../models/Transaction";
import {Accounts} from "../models/Accounts";

export class ApiService{

    private static instance: ApiService
    private axios: AxiosInstance

    private constructor() {
       this.axios = RestClient.getInstance().getAxios()
    }


    public static getInstance(){
        if (!ApiService.instance){
            ApiService.instance = new ApiService();
        }
        return ApiService.instance
    }

    public getAllBranches():Promise<BranchResult[]>{
        return this.axios.get('branches/').then( (result) =>{
            return result.data
        } )
    }

    public getAllEmployees(): Promise<EmployeeResult[]>{
        return this.axios.get('employees/').then( (result)=> {
            return result.data
        } )
    }

    public editCustomer(customer: Customer): Promise<boolean> {
        return this.axios.put('customer/', ).then( (result) => {
            return result.status === 200
        } )
    }

    public removeCustomer(custId: string): Promise<boolean>{
        return this.axios.delete(`customer/${custId}`).then( (result) => {
            return result.status === 200
        } )
    }


    public createEmployee(employee: EmployeeResult): Promise<boolean> {
        return this.axios.post('employee', employee).then( (value) => {
            return value.status === 200;
        } )
    }

    public createBranch(branch: {name: string, city: string}): Promise<boolean>{
        return this.axios.post('branch', branch).then( (value) =>{
            return value.status === 200;
        } )
    }

    public createCustomer(customer: Customer): Promise<boolean> {
        return this.axios.post('customer', customer).then ( (value) => {
            return value.status === 200;
        } )
    }

    public getPassBook(accountNum: string): Promise<Passbook[]>{

        return this.axios.get<Passbook[]>(`passbook/${accountNum}`).then((result) => {
            return result.data
        })
    }

    public getCustomerNames(): Promise<SelectOptions[]>{
        return this.axios.get('customerInfo/').then( (result) => {
            return result.data
        } )
    }

    public getCustomerBranches(customerId: string): Promise<SelectOptions[]>{
        return this.axios.get(`branchInfo/${customerId}`).then( (result) => {
            return result.data;
        } );
    }

    public getCustomerAccounts(customerId: string, branchId: string): Promise<string[]>{
        return this.axios.get(`accountsInfo/${customerId}/${branchId}/` ).then( (result) =>{
            return result.data;
        } )
    }

    public postTransaction(transactionReq: TransactionReq): Promise<boolean>{
        return this.axios.post('transaction', transactionReq).then( (result) => {
            return result.status === 200
        } )
    }

    public getAllTransactions(): Promise<TransactionResponse[]>{
        return this.axios.get('transactions/').then( (result) => {
            return result.data
        } )
    }

    public getAccountsInfo(): Promise<Accounts[]>{
        return this.axios.get('accounts/').then( (result) =>{
            return result.data
        } )
    }


}
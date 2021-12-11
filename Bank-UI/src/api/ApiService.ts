import {Passbook, PassbookReq} from "../models/Passbook";
import {Axios, AxiosInstance} from "axios";
import {RestClient} from "./RestClient";
import {SelectOptions} from "../models/SelectOptions";
import {backTopDark} from "naive-ui";

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



}
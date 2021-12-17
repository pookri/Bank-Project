import axios, { AxiosInstance} from "axios";

export class RestClient{

    private instance: AxiosInstance
    private static restClient: RestClient

    private constructor() {
        this.instance = axios.create({
            baseURL: 'http://localhost:7000/',
            timeout: 30000,
            headers: {'Content-Type': 'application/json'}
        })
    }

    public static getInstance(): RestClient{
        if(!RestClient.restClient){
            RestClient.restClient = new RestClient();
        }
        return RestClient.restClient;
    }

    public getAxios(): AxiosInstance{
        return this.instance;
    }


}
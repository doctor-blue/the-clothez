export default interface StateCallback<T>{
    onSuccess(data:T):any
    onFailure(code:number,message:string):any
}
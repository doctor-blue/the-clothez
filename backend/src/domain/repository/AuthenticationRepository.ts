import User from "../model/User";
import StateCallback from "../utils/StateCallback";

export default interface AuthenticationRepository extends Repository {

    signIn(userName: string, password: string, callback: StateCallback<boolean>): any;

    signUp(user: User, callback: StateCallback<boolean>): any;

    forgotPassword(email: string): any
}
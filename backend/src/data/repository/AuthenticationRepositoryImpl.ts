import User from "../../domain/model/User";
import AuthenticationRepository from "../../domain/repository/AuthenticationRepository";
import StateCallback from "../../domain/utils/StateCallback";

export default class AuthenticationRepositoryImpl implements AuthenticationRepository {
    signIn(userName: string, password: string, callback: StateCallback<boolean>) {
        this.startSignIn(userName, password, callback)
    }
    signUp(user: User, callback: StateCallback<boolean>) {
        try {
            callback.onSuccess(true);
        } catch (e) {
            callback.onFailure(-1, "Sign in failure")
        }
    }
    forgotPassword(email: string) {
        throw new Error("Method not implemented.");
    }

    private startSignIn = async (userName: string, password: string, callback: StateCallback<boolean>) => {
        callback.onSuccess(false);
    }
}
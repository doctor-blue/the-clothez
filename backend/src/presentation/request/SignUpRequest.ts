import User from "../../domain/model/User";

export class SignUpRequest {
    userInfo: User;
    password: string;

    constructor(userInfo: User, password: string) {
        this.userInfo = userInfo;
        this.password = password;
    }

    static fromObj(obj: any): SignUpRequest {
        return new SignUpRequest(
            User.fromObj(obj.user_info),
            obj.password,
        )
    }
}
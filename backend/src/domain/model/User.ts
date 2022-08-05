export default class User {
    user_id: string;
    first_name: string;
    last_name: string;
    user_name: string;
    email: string;
    phone_number: string;
    avatar: string;
    gender: number;
    is_active: boolean;
    dob: string;
    permission_id: string;

    constructor(user_id: string,
        first_name: string,
        last_name: string,
        user_name: string,
        email: string,
        phone_number: string,
        avatar: string,
        gender: number,
        is_active: boolean,
        dob: string,
        permission_id: string) {

        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_name = user_name;
        this.email = email;
        this.phone_number = phone_number;
        this.avatar = avatar;
        this.is_active = is_active;
        this.dob = dob;
        this.permission_id = permission_id;
        this.gender = gender;
    }

    static fromObj(obj: any): User {        
        return new User(
            obj.user_id,
            obj.first_name,
            obj.last_name,
            obj.user_name,
            obj.email,
            obj.phone_number,
            obj.avatar,
            obj.gender,
            obj.is_active,
            obj.dob,
            obj.permission_id
        )
    }

}
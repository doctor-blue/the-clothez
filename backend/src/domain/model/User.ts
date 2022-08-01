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
        avartar: string,
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
        this.avatar = avartar;
        this.is_active = is_active;
        this.dob = dob;
        this.permission_id = permission_id;
        this.gender = gender;
    }

}
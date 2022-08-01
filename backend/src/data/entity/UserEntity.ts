class UserEntity implements Entity {
    userId: string;
    firstName: string;
    lastName: string;
    userName: string;
    email: string;
    phoneNumber: string;
    password: string;
    createdAt: string;
    updatedAt: string;
    avatar: string;
    gender: number;
    isActive: boolean;
    dob: string;
    permisstionId: string;

    constructor(userId: string,
        firstName: string,
        lastName: string,
        userName: string,
        email: string,
        phoneNumber: string,
        password: string,
        createdAt: string,
        updatedAt: string,
        avartar: string,
        gender: number,
        isActive: boolean,
        dob: string,
        permissionId: string) {

        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.avatar = avartar;
        this.isActive = isActive;
        this.dob = dob;
        this.permisstionId = permissionId;
        this.gender = gender;
    }
}
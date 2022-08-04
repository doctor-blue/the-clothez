import { AUTHENTICATION_FAILURE, INCORRECT_USER_NAME_PWD } from "../../domain/const/ErrorConst";
import { Status } from "../../domain/model/Status";
import User from "../../domain/model/User";
import AuthenticationRepository from "../../domain/repository/AuthenticationRepository";
import StateCallback from "../../domain/utils/StateCallback";
import { postgresPool } from "../source/db";
import bcrypt from 'bcrypt';
import { Token } from "../../domain/model/Token";

import jwt from 'jsonwebtoken';
import { pool } from "../source/pgdb";



function jwtWebToken(user_id: string, user_name: string) {
    const user = { user_id, user_name };
    const accessToken = jwt.sign(user, "process.env.ACCESS_TOKEN_SECRET", { expiresIn: '20s' });
    const refreshToken = jwt.sign(user, "process.env.REFRESH_TOKEN_SECRET", { expiresIn: '5m' });
    return new Token(accessToken, refreshToken);
}

export default class AuthenticationRepositoryImpl implements AuthenticationRepository {
    private signInQuery = "select * from users where email = $1 or user_name = $1";
    private signUpQuery = "insert into users(first_name,last_name,email,permission_id,password) values($1,$2,$3,$4,$5) returning *";

    login(userName: string, password: string, callback: StateCallback<Token, Status>) {
        this.startSignIn(userName, password, callback)
    }

    signUp(user: User, password: string, callback: StateCallback<boolean, Status>) {
        this.startSignUp(user, password, callback);
    }

    forgotPassword(email: string) {
        throw new Error("Method not implemented.");
    }

    refreshToken(refreshToken: string) {

    }

    private startSignIn = async (userName: string, password: string, callback: StateCallback<Token, Status>) => {
        try {
            const users = await postgresPool.query(this.signInQuery, [userName]);
            if (users.rows.length === 0) {
                callback.onFailure(401, INCORRECT_USER_NAME_PWD);
                return;
            }
            const validPassword = await bcrypt.compare(password, users.rows[0].user_password);
            if (!validPassword) {
                callback.onFailure(401, INCORRECT_USER_NAME_PWD);
                return;
            }
            const tokens = jwtWebToken(users.rows[0].user_id, users.rows[0].email);
            // generate and store token
            callback.onSuccess(tokens)
        } catch (err) {
            callback.onFailure(401, AUTHENTICATION_FAILURE)
        }
    }

    private startSignUp = async (user: User, password: string, callback: StateCallback<boolean, Status>) => {
        try {
            const hashedPassword = await bcrypt.hash(password, 20);
            const newUser = await pool.query(this.signUpQuery, [
                user.first_name, user.last_name, user.email, 0, hashedPassword
            ]);
            callback.onSuccess(newUser.rows.length !== 0)
        } catch (err) {
            callback.onFailure(401, AUTHENTICATION_FAILURE)
        }
    }
}
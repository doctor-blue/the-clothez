import { Request, Response } from 'express'
import RepositoryModule from '../../../di/RepositoryModule'
import { SUCCESS_STATUS } from '../../../domain/const/StatusConst'
import User from '../../../domain/model/User'
import AuthenticationRepository from '../../../domain/repository/AuthenticationRepository'


export class AuthenticationController {
    private authenticationRepo: AuthenticationRepository = RepositoryModule.getInstance().provideAuthenticationRepository()

    signIn = async (req: Request, res: Response) => {
        const { user_name, password } = req.body
        this.authenticationRepo.login(user_name, password, {
            onSuccess(data) {
                res.status(200).json(data);
            },
            onFailure(code, message) {
                res.status(code).json(message)
            },
        })
    }

    signUp = async (req: Request, res: Response) => {
        const user = User.fromObj(req.body);
        const password = req.body.password;
        this.authenticationRepo.signUp(user, password, {
            onSuccess(data) {
                if (data)
                    res.status(200).json(SUCCESS_STATUS)
            },
            onFailure(code, message) {
                res.status(code).json(message)
            },
        })

        res.status(200).send("Signup success!!");
    }

    forgotPassword = async (req: Request, res: Response) => {
        res.status(200).send("forgot success!!")

    }

}
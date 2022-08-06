import { Request, Response } from 'express'
import RepositoryModule from '../../../di/RepositoryModule'
import { SUCCESS_STATUS } from '../../../domain/const/StatusConst'
import AuthenticationRepository from '../../../domain/repository/AuthenticationRepository'
import { SignUpRequest } from '../../request/SignUpRequest'
import { IResponse } from '../../response/IResponse'


export class AuthenticationController {
    private authenticationRepo: AuthenticationRepository = RepositoryModule.getInstance().provideAuthenticationRepository()

    signIn = async (req: Request, res: Response) => {
        const { user_name, password } = req.body
        this.authenticationRepo.login(user_name, password, {
            onSuccess(data) {
                res.status(200).json(new IResponse(data, SUCCESS_STATUS));
            },
            onFailure(code, message) {
                res.status(code).json(new IResponse(null, message))
            },
        })
    }

    signUp = async (req: Request, res: Response) => {
        const signUpRequest = SignUpRequest.fromObj(req.body);
        this.authenticationRepo.signUp(signUpRequest.user_info, signUpRequest.password, {
            onSuccess(data) {
                if (data)
                    res.status(200).json(new IResponse(null, SUCCESS_STATUS))
            },
            onFailure(code, message) {
                res.status(code).json(new IResponse(null, message))
            },
        })
    }

    getRefreshToken = async (req: Request, res: Response) => {
        this.authenticationRepo.refreshToken(req.body.refresh_token, {
            onSuccess(data) {
                res.status(200).json(new IResponse(data, SUCCESS_STATUS));
            },
            onFailure(code, message) {
                res.status(code).json(new IResponse(null, message))
            },
        })
    }

    forgotPassword = async (req: Request, res: Response) => {
        res.status(200).send("forgot success!!")

    }

}
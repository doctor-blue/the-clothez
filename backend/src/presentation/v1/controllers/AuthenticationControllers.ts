import { Request, Response } from 'express'


export class AuthenticationController {

    signIn = async (req: Request, res: Response) => {
        res.status(200).send("Signin success!!")
    }

    signUp = async (req: Request, res: Response) => {
        res.status(200).send("Signup success!!")
    }

    forgotPassword = async (req: Request, res: Response) => {
        res.status(200).send("forgot success!!")

    }

}
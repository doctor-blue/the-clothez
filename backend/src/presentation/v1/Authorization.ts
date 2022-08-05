import jwt from 'jsonwebtoken';
import { Request, Response } from 'express';

export function authenticationToken(req: Request, res: Response, next: Function) {
    const authHeader = req.headers['authorization'];// Bearer TOKEN
    const token = authHeader && authHeader.split(" ")[1];

    if (token == null) return res.status(401).json({ error: "Token invalid" });
    let acc = '';
    if (process.env.ACCESS_TOKEN_SECRET) acc = process.env.ACCESS_TOKEN_SECRET

    jwt.verify(token,acc, (err: any, user: any) => {
        if (err) return res.status(403).json({ error: err });

        next();
    });
}
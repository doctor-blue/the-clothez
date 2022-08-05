import 'dotenv/config'

import express, { Application, Request, Response, NextFunction } from 'express';
import User from '../domain/model/User';
import { SignUpRequest } from './request/SignUpRequest';
import { authPath as authPathV1, router as authRoutesV1 } from './v1/routes/AuthenticationRoutes';

const app: Application = express()
const port = 3000;
app.use(express.json());
// npm run dev
app.get('/', (req: Request, res: Response) => {
    res.send("Send request")
});
app.use(authPathV1, authRoutesV1)

app.listen(port, () => {
    console.log("Server running");
})
// console.log(
//     JSON.stringify(
//         new SignUpRequest(
//             new User("", "Nguyen", "Van Tan", 'van-tan', "vantan.nguyen0726@gmail.com", "0987654321", "", 1, true, String(new Date()), "")
//             ,
//             "nao123456"
//         )
//     )
// );

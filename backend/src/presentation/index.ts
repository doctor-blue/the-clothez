import 'dotenv/config'

import express, { Application, Request, Response, NextFunction } from 'express';
import ip from 'ip';
import { swaggerSetup } from './v1/docs/Docs';
import { authPath as authPathV1, router as authRoutesV1 } from './v1/routes/AuthenticationRoutes';

const app: Application = express()
const port = 3000;
app.use(express.json());
swaggerSetup(app);

app.use(authPathV1, authRoutesV1);

app.listen(port, () => {
    console.log("Server running on: ", ip.address() + ":" + port);
})

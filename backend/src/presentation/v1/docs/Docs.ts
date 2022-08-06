import { Application, Router } from 'express';
import { AuthenticationController } from '../controllers/AuthenticationControllers';
import { v1Path } from '../path';
import swaggerUi from 'swagger-ui-express';
import swaggerDocument from 'swagger-jsdoc';
import docs from './document.json';

export const router = Router()
export const docsPath = v1Path + "docs"

const swaggerOptions = {
    swaggerDefinition: docs, 
    apis: [
         
    ],
}

const swaggerDocs = swaggerDocument(swaggerOptions);

export function swaggerSetup(app: Application) {
    app.use(docsPath, swaggerUi.serve, swaggerUi.setup(swaggerDocs))
}

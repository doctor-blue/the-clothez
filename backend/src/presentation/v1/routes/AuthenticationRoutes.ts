import { Router } from 'express';
import { authenticationToken } from '../Authorization';
import { AuthenticationController } from '../controllers/AuthenticationControllers';
import { v1Path } from '../path';
import swaggerUi from 'swagger-ui-express';
import swaggerDocument from 'swagger-jsdoc'

const controller = new AuthenticationController();
export const router = Router()
export const authPath = v1Path + "auth"


router.post("/login", controller.login);
router.post("/signUp", controller.signUp);
router.post("/refresh_token", controller.getRefreshToken);
router.post("/forgot", authenticationToken, controller.forgotPassword)

import { Router } from 'express';
import { AuthenticationController } from '../controllers/AuthenticationControllers';
import { v1Path } from '../path';

const controller = new AuthenticationController();
export const router = Router()
export const authPath = v1Path + "auth"


router.post("/signIn", controller.signIn);
router.post("/signUp", controller.signUp);
router.post("/forgot", controller.forgotPassword)

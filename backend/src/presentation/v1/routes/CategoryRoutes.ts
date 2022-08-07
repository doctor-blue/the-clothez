import { Router } from 'express';
import { authenticationToken } from '../Authorization';
import { AuthenticationController } from '../controllers/AuthenticationControllers';
import { CategoryController } from '../controllers/CategoryController';
import { v1Path } from '../path';

const controller = new CategoryController();
export const router = Router()
export const categoryPath = v1Path + "category"


router.post("/", authenticationToken, controller.createCategory);
router.put("/", authenticationToken, controller.updateCategory);
router.delete("/:id", authenticationToken, controller.deleteCategory)

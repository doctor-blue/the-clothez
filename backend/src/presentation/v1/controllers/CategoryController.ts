import { Request, Response } from 'express'
import RepositoryModule from '../../../di/RepositoryModule'
import { LOCALE_DEFAULT } from '../../../domain/const/Locale'
import { SUCCESS_STATUS } from '../../../domain/const/StatusConst'
import { Category } from '../../../domain/model/Category'
import { CategoryRepository } from '../../../domain/repository/CategoryRepository'
import { IResponse } from '../../response/IResponse'

export class CategoryController {
    private categoryRepo: CategoryRepository = RepositoryModule.getInstance().provideCategoryRepository();

    createCategory = async (req: Request, res: Response) => {
        const category = Category.fromObj(req.body);

        if(!category.lang) category.lang = LOCALE_DEFAULT;

        this.categoryRepo.createCategory(category, {
            onSuccess(data) {
                res.status(200).json(new IResponse(data, SUCCESS_STATUS))
            },
            onFailure(code, message) {
                res.status(code).json(new IResponse(null, message));
            },
        })
    }

    updateCategory = async (req: Request, res: Response) => {
        const category = Category.fromObj(req.body);
        if(!category.lang) category.lang = LOCALE_DEFAULT;

        this.categoryRepo.updateCategory(category, {
            onSuccess(data) {
                res.status(200).json(new IResponse(data, SUCCESS_STATUS))
            },
            onFailure(code, message) {
                res.status(code).json(new IResponse(null, message));
            },
        })
    }

    deleteCategory = async (req: Request, res: Response) => {
        this.categoryRepo.deleteCategory(String(req.params.id), {
            onSuccess(data) {
                res.status(200).json(new IResponse(data, SUCCESS_STATUS))
            },
            onFailure(code, message) {
                res.status(code).json(new IResponse(null, message));
            },
        })
    }


}
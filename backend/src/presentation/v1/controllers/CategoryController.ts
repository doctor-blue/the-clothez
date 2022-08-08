import { Request, Response } from 'express'
import RepositoryModule from '../../../di/RepositoryModule'
import { LOCALE_DEFAULT } from '../../../domain/const/Locale'
import { SUCCESS_STATUS } from '../../../domain/const/StatusConst'
import { Category, SubCategory } from '../../../domain/model/Category'
import { CategoryRepository } from '../../../domain/repository/CategoryRepository'
import { IResponse } from '../../response/IResponse'

export class CategoryController {
    private categoryRepo: CategoryRepository = RepositoryModule.getInstance().provideCategoryRepository();

    getAllCategories = async (req: Request, res: Response) => {

        let locale = LOCALE_DEFAULT;

        if (req.query.locale) locale = String(req.query.locale);


        this.categoryRepo.getAllCategories(locale, {
            onSuccess(data) {
                res.status(200).json(new IResponse(data, SUCCESS_STATUS));
            },
            onFailure(code, message) {
                res.status(code).json(new IResponse(null, message));
            },
        });
    }

    createCategory = async (req: Request, res: Response) => {
        const category = Category.fromObj(req.body);

        if (!category.lang) category.lang = LOCALE_DEFAULT;

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
        if (!category.lang) category.lang = LOCALE_DEFAULT;

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

    createSubCategory = async (req: Request, res: Response) => {
        const subCategory = SubCategory.fromObj(req.body);

        if (!subCategory.lang) subCategory.lang = LOCALE_DEFAULT;

        console.log('create sub');

        this.categoryRepo.createSubCategory(subCategory, {
            onSuccess(data) {
                res.status(200).json(new IResponse(data, SUCCESS_STATUS))
            },
            onFailure(code, message) {
                res.status(code).json(new IResponse(null, message));
            },
        })
    }

    updateSubCategory = async (req: Request, res: Response) => {
        const subCategory = SubCategory.fromObj(req.body);
        if (!subCategory.lang) subCategory.lang = LOCALE_DEFAULT;

        this.categoryRepo.updateSubCategory(subCategory, {
            onSuccess(data) {
                res.status(200).json(new IResponse(data, SUCCESS_STATUS))
            },
            onFailure(code, message) {
                res.status(code).json(new IResponse(null, message));
            },
        })
    }

    deleteSubCategory = async (req: Request, res: Response) => {
        this.categoryRepo.deleteSubCategory(String(req.params.id), {
            onSuccess(data) {
                res.status(200).json(new IResponse(data, SUCCESS_STATUS))
            },
            onFailure(code, message) {
                res.status(code).json(new IResponse(null, message));
            },
        })
    }



}
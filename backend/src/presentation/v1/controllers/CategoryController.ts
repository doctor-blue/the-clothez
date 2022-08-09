import { Request, Response } from 'express'
import RepositoryModule from '../../../di/RepositoryModule'
import { SUCCESS_STATUS } from '../../../domain/const/StatusConst'
import { Category, SubCategory } from '../../../domain/model/Category'
import { CategoryRepository } from '../../../domain/repository/CategoryRepository'
import { IResponse } from '../../response/IResponse'

export class CategoryController {
    private categoryRepo: CategoryRepository = RepositoryModule.getInstance().provideCategoryRepository();

    getAllCategories = async (req: Request, res: Response) => {

        this.categoryRepo.getAllCategories({
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
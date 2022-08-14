import { Injectable, InternalServerErrorException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { ProductColorEntity } from 'src/data/entity/ProductColorEntity';
import { ProductColorResEntity } from 'src/data/entity/ProductColorResEntity';
import { ProductEntity } from 'src/data/entity/ProductEntity';
import { ProductInfoEntity } from 'src/data/entity/ProductInfoEntity';
import { ProductSizeEntity } from 'src/data/entity/ProductSizeEntity';
import MapperModule from 'src/di/MapperModule';
import { EXECUTE_QUERY_FAILURE, INVALID_INFO } from 'src/domain/const/ErrorConst';
import { SUCCESS_STATUS } from 'src/domain/const/StatusConst';
import { Product } from 'src/domain/model/Product';
import { ProductColor } from 'src/domain/model/ProductColor';
import { ProductColorRes } from 'src/domain/model/ProductColorRes';
import { ProductSize } from 'src/domain/model/ProductSize';
import { currentTime } from 'src/domain/utils/Time';
import { IResponse } from 'src/presentation/response/IResponse';
import { Repository } from 'typeorm';

@Injectable()
export class ProductService {

    constructor(
        @InjectRepository(ProductEntity)
        private readonly productRepo: Repository<ProductEntity>,
        @InjectRepository(ProductColorEntity)
        private readonly colorRepo: Repository<ProductColorEntity>,
        @InjectRepository(ProductSizeEntity)
        private readonly sizeRepo: Repository<ProductSizeEntity>,
        @InjectRepository(ProductColorResEntity)
        private readonly resRepo: Repository<ProductColorResEntity>,
        @InjectRepository(ProductInfoEntity)
        private readonly productInfoRepo: Repository<ProductInfoEntity>,
    ) {
    }
    private productMapper = MapperModule.getInstance().provideProductMapper();
    private productColorMapper = MapperModule.getInstance().provideProducColortMapper();
    private productSizeMapper = MapperModule.getInstance().provideProductSizeMapper();
    private productColorResMapper = MapperModule.getInstance().provideProductColorResMapper();
    private productInfoMapper = MapperModule.getInstance().provideProductInfoMapper();

    async getProducts(): Promise<IResponse<Array<Product>>> {
        const products = []
        try {
            const productEntities = await this.productRepo.find();
            const colorEntities = await this.colorRepo.find();
            const sizeEntities = await this.sizeRepo.find();
            const resourceEntities = await this.resRepo.find();
            const infoEntities = await this.productInfoRepo.find();

            productEntities.forEach(productEntity => {
                const product = this.productMapper.toDomain(productEntity);
                const mColorEntities = colorEntities.filter((color) => color.product_id == product.productId);
                const sizes = sizeEntities.filter(size => size.product_id == productEntity.id).map(size => this.productSizeMapper.toDomain(size));

                const colors = [];
                let infos = [];
                product.sizes = sizes;
                

                mColorEntities.forEach(colorEntity => {
                    const color = this.productColorMapper.toDomain(colorEntity);
                    const productInfo = infoEntities.filter(info => info.color_id == colorEntity.id).map(info => this.productInfoMapper.toDomain(info));
                    infos = infos.concat(productInfo);
                    const resources = resourceEntities.filter(res => res.color_id == color.colorId).map(res => this.productColorResMapper.toDomain(res));
                    color.resources = resources;
                    colors.push(color);
                });

                product.colors = colors
                product.infos = infos;
                products.push(product);
            })
        } catch (error) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson())
        }

        return new IResponse(products, SUCCESS_STATUS);
    }


    async createProduct(product: Product): Promise<IResponse<string>> {
        if (!product.name ||
            !product.description ||
            !product.productCode ||
            !product.form ||
            !product.material ||
            !product.unit ||
            !product.quantityPerUnit ||
            !product.price ||
            !product.unitPrice ||
            !product.subCategoryId) {
            throw new InternalServerErrorException(INVALID_INFO.toJson())
        }
        let result;

        try {
            result = await this.productRepo.createQueryBuilder().insert().into(ProductEntity).values([
                {
                    name: product.name,
                    description: product.description,
                    product_code: product.productCode,
                    form: product.form,
                    material: product.material,
                    unit: product.unit,
                    quantity_per_unit: product.quantityPerUnit,
                    price: product.price,
                    unit_price: product.unitPrice,
                    sub_category_id: product.subCategoryId
                }
            ]).returning("id").execute()
        } catch (error) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }


        if (!result) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }

        return new IResponse(result.raw[0].id, SUCCESS_STATUS)
    }

    async updateProduct(product: Product): Promise<IResponse<boolean>> {

        if (!product.productId ||
            !product.name ||
            !product.description ||
            !product.productCode ||
            !product.form ||
            !product.material ||
            !product.unit ||
            !product.quantityPerUnit ||
            !product.price ||
            !product.unitPrice ||
            !product.subCategoryId) {
            throw new InternalServerErrorException(INVALID_INFO.toJson())
        }

        let result

        try {
            result = await this.productRepo.createQueryBuilder()
                .update(ProductEntity).set({
                    updated_at: currentTime(),
                    name: product.name,
                    description: product.description,
                    product_code: product.productCode,
                    form: product.form,
                    material: product.material,
                    unit: product.unit,
                    quantity_per_unit: product.quantityPerUnit,
                    price: product.price,
                    unit_price: product.unitPrice,
                }).where("id=:id", { id: product.productId }).execute();

        } catch (error) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }

        if (result.affected == 0) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }

        return new IResponse(true, SUCCESS_STATUS);
    }

    async deleteProduct(productId: string): Promise<IResponse<boolean>> {
        if (!productId) {
            throw new InternalServerErrorException(INVALID_INFO.toJson())
        }
        let result;
        try {
            result = await this.productRepo.delete({
                id: productId
            });
        } catch (err) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }


        if (result.affected == 0) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }


        return new IResponse(true, SUCCESS_STATUS)
    }

    async createProductColor(color: ProductColor): Promise<IResponse<string>> {
        if (!color.name ||
            !color.description ||
            !color.productId ||
            !color.hex) {
            throw new InternalServerErrorException(INVALID_INFO.toJson())
        }
        let result;
        try {
            result = await this.colorRepo.createQueryBuilder()
                .insert().into(ProductColorEntity).values([
                    {
                        name: color.name,
                        description: color.description,
                        product_id: color.productId,
                        color_hex: color.hex
                    }
                ]).returning("id").execute()
        } catch (err) { }


        if (!result) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }

        return new IResponse(result.raw[0].id, SUCCESS_STATUS)
    }


    async updateProductColor(color: ProductColor): Promise<IResponse<boolean>> {

        if (!color.name ||
            !color.description ||
            !color.colorId ||
            !color.hex) {
            throw new InternalServerErrorException(INVALID_INFO.toJson())
        }

        let result;

        try {
            result = await this.colorRepo.createQueryBuilder()
                .update(ProductColorEntity).set({
                    updated_at: currentTime(),
                    name: color.name,
                    description: color.description,
                    color_hex: color.hex
                }).where("id=:id", { id: color.colorId }).execute();

        } catch (error) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }

        if (result.affected == 0) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }

        return new IResponse(true, SUCCESS_STATUS);
    }

    async deleteProductColor(colorId: string): Promise<IResponse<boolean>> {
        if (!colorId) {
            throw new InternalServerErrorException(INVALID_INFO.toJson())
        }
        let result;
        try {
            result = await this.colorRepo.delete({
                id: colorId
            });
        } catch (err) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }


        if (result.affected == 0) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }


        return new IResponse(true, SUCCESS_STATUS)
    }

    async createProductSize(size: ProductSize): Promise<IResponse<string>> {
        if (!size.size ||
            !size.productId
        ) {
            throw new InternalServerErrorException(INVALID_INFO.toJson())
        }
        let result
        try {
            result = await this.sizeRepo.createQueryBuilder()
                .insert().into(ProductSizeEntity).values([
                    {
                        product_id: size.productId,
                        size: size.size
                    }
                ]).returning("id").execute()
        } catch (error) {

        }

        if (!result) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }

        return new IResponse(result.raw[0].id, SUCCESS_STATUS)
    }

    async updateProductSize(size: ProductSize): Promise<IResponse<boolean>> {

        if (!size.size ||
            !size.sizeId) {
            throw new InternalServerErrorException(INVALID_INFO.toJson())
        }

        let result
        try {
            result = await this.sizeRepo.createQueryBuilder()
                .update(ProductSizeEntity).set({
                    updated_at: currentTime(),
                    size: size.size,
                }).where("id=:id", { id: size.sizeId }).execute();

        } catch (error) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }
        if (result.affected == 0) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }

        return new IResponse(true, SUCCESS_STATUS);
    }

    async deleteProductSize(sizeId: string): Promise<IResponse<boolean>> {
        if (!sizeId) {
            throw new InternalServerErrorException(INVALID_INFO.toJson())
        }
        let result;
        try {
            result = await this.sizeRepo.delete({
                id: sizeId
            });
        } catch (err) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }


        if (result.affected == 0) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }

        return new IResponse(true, SUCCESS_STATUS)
    }

    async createProductColorRes(res: ProductColorRes): Promise<IResponse<string>> {
        if (!res.colorId ||
            !res.url ||
            !res.description ||
            res.mineType < 0 ||
            res.resType < 0
        ) {
            throw new InternalServerErrorException(INVALID_INFO.toJson())
        }

        let result
        try {
            result = await this.sizeRepo.createQueryBuilder()
                .insert().into(ProductColorResEntity).values([
                    {
                        color_id: res.colorId,
                        url: res.url,
                        description: res.description,
                        mine_type: res.mineType,
                        res_type: res.resType
                    }
                ]).returning("id").execute()

        } catch (error) {
        }

        if (!result) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }

        return new IResponse(result.raw[0].id, SUCCESS_STATUS)
    }

    async updateProductColorRes(res: ProductColorRes): Promise<IResponse<boolean>> {

        if (!res.resId ||
            !res.url ||
            !res.description ||
            res.mineType < 0 ||
            res.resType < 0
        ) {
            throw new InternalServerErrorException(INVALID_INFO.toJson())
        }

        let result
        try {
            result = await this.resRepo.createQueryBuilder()
                .update(ProductColorResEntity).set({
                    updated_at: currentTime(),
                    url: res.url,
                    description: res.description,
                    mine_type: res.mineType,
                    res_type: res.resType,
                    id: res.resId
                }).where("id=:id", { id: res.resId }).execute();

        } catch (error) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }
        if (result.affected == 0) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }

        return new IResponse(true, SUCCESS_STATUS);
    }

    async deleteProductColorRes(resId: string): Promise<IResponse<boolean>> {
        if (!resId) {
            throw new InternalServerErrorException(INVALID_INFO.toJson())
        }
        let result;
        try {
            result = await this.resRepo.delete({
                id: resId
            });
        } catch (err) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }


        if (result.affected == 0) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }

        return new IResponse(true, SUCCESS_STATUS)
    }
}

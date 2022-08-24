import { CartItemMapper } from "src/data/mappers/CartItemMapper";
import { CategoryMapper } from "src/data/mappers/CategoryMapper";
import { ProductColorMapper } from "src/data/mappers/ProductColorMapper";
import { ProductInfoMapper } from "src/data/mappers/ProductInfoMapper";
import { ProductMapper } from "src/data/mappers/ProductMapper";
import { ProductSizeMapper } from "src/data/mappers/ProductSizeMapper";
import { ProductColorResMapper } from "src/data/mappers/ResourceMapper";
import { SubCategoryMapper } from "src/data/mappers/SubCategoryMapper";
import { UserMapper } from "src/data/mappers/UserMapper";
import User from "src/domain/model/User";

export default class MapperModule {
    private static instance: MapperModule;

    private constructor() { }

    public static getInstance(): MapperModule {
        if (MapperModule.instance == null)
            MapperModule.instance = new MapperModule()
        return MapperModule.instance;
    }

    provideUserMapper(): UserMapper {
        return new UserMapper();
    }

    provideCategoryMapper(): CategoryMapper {
        return new CategoryMapper()
    }
    provideSubCategoryMapper(): SubCategoryMapper {
        return new SubCategoryMapper()
    }

    provideProductMapper(): ProductMapper {
        return new ProductMapper();
    }

    provideProducColortMapper(): ProductColorMapper {
        return new ProductColorMapper();
    }

    provideProductSizeMapper(): ProductSizeMapper {
        return new ProductSizeMapper();
    }

    provideProductColorResMapper(): ProductColorResMapper {
        return new ProductColorResMapper();
    }

    provideProductInfoMapper(): ProductInfoMapper {
        return new ProductInfoMapper();
    }
    provideCartItemMapper(): CartItemMapper {
        return new CartItemMapper()
    }
}

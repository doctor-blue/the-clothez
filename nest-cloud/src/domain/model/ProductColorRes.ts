export class ProductColorRes {
    resId: string;
    colorId: string;
    url: string;
    description: string;
    resType: number;
    mineType: number;

    constructor(
        resId: string,
        colorId: string,
        url: string,
        description: string,
        resType: number,
        mineType: number,
    ) {
        this.resId = resId;
        this.colorId = colorId;
        this.url = url;
        this.description = description;
        this.resType = resType;
        this.mineType = mineType;
    }

    static fromObj(obj: any): ProductColorRes {
        return new ProductColorRes(
            obj.resId,
            obj.colorId,
            obj.url,
            obj.description,
            obj.resType,
            obj.mineType,
        )
    }
}
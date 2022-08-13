export class ProductColorRes {
    res_id: string;
    url: string;
    description: string;
    res_type: number;
    mine_type: number;

    constructor(
        res_id: string,
        url: string,
        description: string,
        res_type: number,
        mine_type: number,
    ) {
        this.res_id = res_id;
        this.url = url;
        this.description = description;
        this.res_type = res_type;
        this.mine_type = mine_type;
    }

    static fromObj(obj: any): ProductColorRes {
        return new ProductColorRes(
            obj.res_id,
            obj.url,
            obj.description,
            obj.res_type,
            obj.mine_type,
        )
    }
}
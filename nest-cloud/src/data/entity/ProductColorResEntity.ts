export class ProductColorResEntity{
    res_id: string;
    url: string;
    description: string;
    res_type: number;
    mine_type: number;
    created_at: string;
    updated_at: string

    constructor(
        res_id: string,
        url: string,
        description: string,
        res_type: number,
        mine_type: number,
        created_at: string,
        updated_at: string,
    ) {
        this.res_id = res_id;
        this.url = url;
        this.description = description;
        this.res_type = res_type;
        this.mine_type = mine_type;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
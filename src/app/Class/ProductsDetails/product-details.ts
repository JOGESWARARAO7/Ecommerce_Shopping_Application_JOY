export class ProductDetails {

    productid?: number;
    productname!: string;
    brand!: string;
    price!: number;
    available!: number;
    productcolor!: string;
    gender!: string;
    productimage: File | null = null;
    productrightsideview: File | null = null;
    productleftsideview: File | null = null;
    productbacksideview: File | null = null;

}

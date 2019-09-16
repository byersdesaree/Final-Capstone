export class Item {
    id: number;
    name: String;
    category: String;
    price: number;
    quantity: number;
    available: number;
    domestic: boolean;
    imported: boolean;
    imageUrl: string;
    total: number;
    salesTax: number;
    importFee: number;

    constructor(name: String, category: String, price: number, quantity: number, available: number,imageUrl:string, total: number, imported?:boolean, domestic?: boolean, salesTax?: number, importFee?:number) {
        this.name = name;
        this.category = category;
        this.price = price; 
        this.quantity = quantity;
        this.available = available;
        this.imageUrl= imageUrl;
        this.total = total;
        this.imported = imported;
        this.domestic = domestic;
        this.salesTax = salesTax;
        this.importFee = importFee;
        
    }
}
export class FoodItem {
    id: number;
    itemName: string;
    itemDescription: string;
    isVeg: boolean;
    price: number;
    restaurantId: number;
    quantity: number;

    constructor(data?: any) {
        if (data) {
        this.id = data.id || 0;
        this.itemName = data.itemName || '';
        this.itemDescription = data.itemDescription || '';
        this.isVeg = data.isVeg !== undefined ? data.isVeg : true;
        this.price = data.price || 0;
        this.restaurantId = data.restaurantId || 0;
        this.quantity = data.quantity || 1;
        } else {
        this.id = 0;
        this.itemName = '';
        this.itemDescription = '';
        this.isVeg = true;
        this.price = 0;
        this.restaurantId = 0;
        this.quantity = 1;
    }
}
}

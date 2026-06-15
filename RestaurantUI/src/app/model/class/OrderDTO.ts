import { FoodItem } from "./FoodItem";
import { Restaurant } from "./Restaurant";

export class OrderDTO {
    foodItemsList: FoodItem[];
    userId: number;
    restaurant: Restaurant;

    constructor() {
        this.foodItemsList = [];
        this.userId = 1;
        this.restaurant = new Restaurant();
    }
}

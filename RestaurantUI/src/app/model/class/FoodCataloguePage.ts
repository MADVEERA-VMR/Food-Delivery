import { FoodItem } from "./FoodItem";
import { Restaurant } from "./Restaurant";

export class FoodCataloguePage {
    foodItemsList: FoodItem[];
    restaurant: Restaurant;

    constructor() {
        this.foodItemsList = [];
        this.restaurant = new Restaurant();
    }
}

import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FoodItemService } from '../../services/fooditem.service';
import { FoodCataloguePage } from '../../model/class/FoodCataloguePage';
import { FoodItem } from '../../model/class/FoodItem';
import { Restaurant } from '../../model/class/Restaurant';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-food-catalogue',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './food-catalogue.component.html',
  styleUrl: './food-catalogue.component.css'
})
export class FoodCatalogueComponent {

  restaurantId: number;
  foodItemResponse: FoodCataloguePage;
  foodItemCart: FoodItem[] = [];
  orderSummary: FoodCataloguePage;
  showDialog2: boolean = false;
  validationMessage: string = ''; // Add this property

  constructor(private route: ActivatedRoute, private foodItemService: FoodItemService, private router: Router) {
  }

  ngOnInit() {

    this.route.paramMap.subscribe(params => {
      this.restaurantId = +(params.get('id') ?? 0);
    });

      this.route.queryParams.subscribe(queryParams => {
    const selectedItems = queryParams['selectedItems'];
    if (selectedItems) {
      this.foodItemCart = JSON.parse(selectedItems); // Restore the selected items
      this.foodItemCart.forEach(item => {
        const food = this.foodItemResponse?.foodItemsList?.find(f => f.id === item.id);
        if (food) {
          food.quantity = item.quantity; // Restore the quantity
        }
      });
    }
  });
    this.getFoodItemsByRestaurant(this.restaurantId);
    
  }

  getFoodItemsByRestaurant(restaurant: number) {
    this.foodItemService.getFoodItemsByRestaurant(restaurant).subscribe(
      data => {
        this.foodItemResponse = data;
      }
    )
  }

  increment(food: any) {
    food.quantity++;
    this.validationMessage = '';
    const index = this.foodItemCart.findIndex(item => item.id === food.id);
    if (index === -1) {
      // If record does not exist, add it to the array
      this.foodItemCart.push(food);
    } else {
      // If record exists, update it in the array
      this.foodItemCart[index] = food;
    }
  }

  decrement(food: any) {
    if (food.quantity > 0) {
      food.quantity--;

      const index = this.foodItemCart.findIndex(item => item.id === food.id);
      if (this.foodItemCart[index].quantity == 0) {
        this.foodItemCart.splice(index, 1);
      } else {
        // If record exists, update it in the array
        this.foodItemCart[index] = food;
      }

    }
  }

  onCheckOut() {
    this.foodItemCart;
    if (this.foodItemCart.length === 0) {
      this.showDialog2 = true;
      //alert('Please select at least one item before proceeding to checkout.');
      this.validationMessage = 'Please select at least one item before proceeding to checkout.'; // Set the message
      return; // Prevent further execution
  }
  this.validationMessage = '';
    this.orderSummary = {
      foodItemsList: [],
      restaurant: this.foodItemResponse?.restaurant || {} as Restaurant // Use a default value
    };
    this.orderSummary.foodItemsList = this.foodItemCart;
    this.orderSummary.restaurant = this.foodItemResponse.restaurant;
    this.router.navigate(['/orderSummary'], { queryParams: { data: JSON.stringify(this.orderSummary) } });
  }

  closeDialog2() {
    this.showDialog2 = false;
    return; // Prevent further execution
  }

}

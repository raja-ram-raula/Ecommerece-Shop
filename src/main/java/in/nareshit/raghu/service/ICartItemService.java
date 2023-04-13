package in.nareshit.raghu.service;

import java.util.List;

import in.nareshit.raghu.entity.CartItem;

public interface ICartItemService {

	Long addCartItem(CartItem cartItem);
	void removeCartitem(Long cartItemId);
	
	List<CartItem> viewAllItems(Long custId);
	CartItem getOneCartItem(Long custId,Long prodId);
	void updateQty(Long cartItemId,Integer qty);
	int getCartItemsCount(Long custId);
	void deleteAllCartItems(List<CartItem> cartItems);
}

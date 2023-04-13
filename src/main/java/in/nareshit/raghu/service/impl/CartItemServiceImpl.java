package in.nareshit.raghu.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nareshit.raghu.entity.CartItem;
import in.nareshit.raghu.repo.CartItemRepository;
import in.nareshit.raghu.service.ICartItemService;

@Service
public class CartItemServiceImpl implements ICartItemService {

	@Autowired
	private CartItemRepository repo;
	
	public Long addCartItem(CartItem cartItem) {
		return repo.save(cartItem).getId();
	}

	public void removeCartitem(Long cartItemId) {
		repo.deleteById(cartItemId);
	}

	@Override
	public List<CartItem> viewAllItems(Long custId) {
		return repo.fetchCartItemsByCustomer(custId);
	}

	@Override
	public CartItem getOneCartItem(Long custId, Long prodId) {
		Optional<CartItem> opt = repo.fetchCartItemByCustomerAndProduct(custId, prodId);
		if(opt.isPresent()) 
			return opt.get();
		else
			return null;
	}

	@Override
	@Transactional
	public void updateQty(Long cartItemId, Integer qty) {
		repo.updateCartItemQty(cartItemId, qty);
	}
	
	@Override
	public int getCartItemsCount(Long custId) {
		return repo.getCartItemsCount(custId);
	}
	
	public void deleteAllCartItems(List<CartItem> cartItems) {
		repo.deleteAll(cartItems);
	}

}

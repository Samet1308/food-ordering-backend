package com.ecommerce.foodordering.services.concretes;

import com.ecommerce.foodordering.dtos.responses.product.AddProductInCartDto;
import com.ecommerce.foodordering.entities.CartItems;
import com.ecommerce.foodordering.entities.Order;
import com.ecommerce.foodordering.entities.Product;
import com.ecommerce.foodordering.entities.User;
import com.ecommerce.foodordering.enums.OrderStatus;
import com.ecommerce.foodordering.repository.CartItemsRepository;
import com.ecommerce.foodordering.repository.OrderRepository;
import com.ecommerce.foodordering.repository.ProductRepository;
import com.ecommerce.foodordering.repository.UserRepository;
import com.ecommerce.foodordering.services.abstracts.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartManager implements CartService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartItemsRepository cartItemsRepository;


    @Override
    public ResponseEntity<?> addProductToCart (AddProductInCartDto addProductInCartDto){
        Order activeOrder = orderRepository.findByUserIdAndStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);
        Optional<CartItems> optionalCartItems = cartItemsRepository.
                findByProductIdAndOrderIdAndUserId(addProductInCartDto.getProductId(), activeOrder.getId(), addProductInCartDto.getUserId());

        if(optionalCartItems.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }else{
            Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
            Optional<User> optionalUser = userRepository.findById(addProductInCartDto.getUserId());

            if(optionalProduct.isPresent() && optionalUser.isPresent()){
                CartItems cartItems = new CartItems();

                cartItems.setProduct(optionalProduct.get());
                cartItems.setPrice(optionalProduct.get().getPrice());
                cartItems.setQuantity(1L);
                cartItems.setUser(optionalUser.get());
                cartItems.setOrder (activeOrder);

                CartItems updatedCart = cartItemsRepository.save(cartItems);
                activeOrder.setTotalAmount (activeOrder.getTotalAmount() + cartItems.getPrice());
                activeOrder.setAmount (activeOrder.getAmount()+ cartItems.getPrice());
                activeOrder.getCartItems().add(cartItems);
                orderRepository.save(activeOrder);

                return ResponseEntity.status(HttpStatus.CREATED). body (cartItems);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Product not found");
            }
        }
    }


}

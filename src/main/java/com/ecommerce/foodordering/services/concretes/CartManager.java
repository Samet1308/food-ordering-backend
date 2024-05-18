package com.ecommerce.foodordering.services.concretes;

import com.ecommerce.foodordering.dtos.AddProductInCartDto;
import com.ecommerce.foodordering.dtos.CartItemsDto;
import com.ecommerce.foodordering.dtos.OrderDto;
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
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartManager implements CartService {


    private  final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private  final CartItemsRepository cartItemsRepository;


    @Override
    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto){
        Order activeOrder = orderRepository.findByUserIdAndStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);

        Optional<CartItems> optionalCartItems = cartItemsRepository.
                findByProductIdAndOrderIdAndUserId(addProductInCartDto.getProductId(), activeOrder.getId(), addProductInCartDto.getUserId());

        if(optionalCartItems.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }else{
            Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
            Optional<User> optionalUser = userRepository.findById(addProductInCartDto.getUserId());

            if(optionalProduct.isPresent() && optionalUser.isPresent()){

                CartItems cart = new CartItems();
                cart.setProduct(optionalProduct.get());
                cart.setPrice(optionalProduct.get().getPrice());
                cart.setQuantity(1L);
                cart.setUser(optionalUser.get());
                cart.setOrder(activeOrder);

                CartItems updatedCart = cartItemsRepository.save(cart);
                activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cart.getPrice());
                activeOrder.setAmount(activeOrder.getAmount()+ cart.getPrice());
                activeOrder.getCartItems().add(cart);
                orderRepository.save(activeOrder);

                return ResponseEntity.status(HttpStatus.CREATED).body(cart);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Product not found");
            }

        }
    }

    @Override
    public OrderDto getCartByUserId(Long userId){
        Order activeOrder = orderRepository.findByUserIdAndStatus(userId, OrderStatus.Pending);
        List<CartItemsDto> cartItemsDtoList = activeOrder.getCartItems().stream().map(CartItems::getCartDto).collect(Collectors.toList());
        OrderDto orderDto = new OrderDto();
        orderDto.setAmount(activeOrder.getAmount());
        orderDto.setId(activeOrder.getId());
        orderDto.setStatus(activeOrder.getStatus());
        orderDto.setDiscount(activeOrder.getDiscount());
        orderDto.setTotalAmount(activeOrder.getTotalAmount());
        orderDto.setCartItems(cartItemsDtoList);

        return orderDto;
    }

    @Override
    public void deleteOrder(Long orderId) {
        Optional<Order> optionalOrder= orderRepository.findById(orderId);
        if(optionalOrder.isPresent()){
            orderRepository.deleteById(orderId);
        }
    }


}

package springdemo.order.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springdemo.order.handlers.OrderItemRepository;
import springdemo.order.handlers.OrderRepository;
import springdemo.order.models.Order;
import springdemo.order.models.OrderItem;
import springdemo.product.models.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author lidong@date 2024-06-24@version 1.0
 */


@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(Long id) {
        Order ret = orderRepository.findById(id).orElse(null);
        for (OrderItem item :ret.getItems()){
            Optional<Product> productOpt = productService.findProductById(item.getProductId());
            if (productOpt.isPresent()) {
                Product product = productOpt.get();
//                System.out.println("=======get Product:" + product);
            }
        }
        return ret;
    }
    @Transactional
    public void placeOrder(Order order, List<OrderItem> items) {
        // Create an Order and its associated OrderItems
        for (OrderItem item : items) {
            Optional<Product> productOpt = productService.findProductById(item.getProductId());
            if (productOpt.isPresent()) {
                Product product = productOpt.get();

            } else {
                // Handle product not found
                throw new RuntimeException("Product not found with ID: " + item.getProductId());
            }
//            item.setOrder(order);
            OrderItem theItem = orderItemRepository.save(item);
        }
        order.setItems(items);
        Order theOrder = orderRepository.save(order);
    }
}
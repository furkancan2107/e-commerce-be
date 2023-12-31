package com.rf.ecommerce.Dto;

import com.rf.ecommerce.Dto.Admin.AdminDto;
import com.rf.ecommerce.Dto.Order.OrderDto;
import com.rf.ecommerce.Dto.Product.*;
import com.rf.ecommerce.Dto.User.UserDto;
import com.rf.ecommerce.Entity.Admin.Admin;
import com.rf.ecommerce.Entity.Order.Order;
import com.rf.ecommerce.Entity.Product.*;
import com.rf.ecommerce.Entity.User.User;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DtoConvert {
    public static UserDto userConvert(User from){
        return new UserDto(from.getId(), from.getUsername(), from.getEmail(),from.getAddress());
    }
    public static AdminDto adminConvert(Admin from){
        return new AdminDto(from.getId(), from.getUsername());
    }
    public static CategoryDto categoryConvert(Category from){
       return new CategoryDto(from.getId(), from.getName(), from.getImage());
    }
    public static ProductDto productConvert(Product product){
        return new ProductDto(
                product.getId(), product.getTitle(), product.getDescription(),
                adminConvert(product.getAdmin()), product.getImage(),
                categoryConvert(product.getCategory()) , product.getPrice());
    }
    public static CommentDto commentConvert(Comment from){
        return new CommentDto(from.getId(), from.getContent(),productConvert(from.getProduct()),userConvert(from.getUser()));
    }
    public CartDto cartConvert(Cart from){
        return new CartDto(from.getId(), userConvert(from.getUser()),productConvert(from.getProduct()));
    }
    public OrderDto orderConvert(Order from){
        return new OrderDto(from.getId(),productConvert(from.getProduct()),userConvert(from.getUser()), from.getTimestap(), from.getOrderStatus(), from.getAddress()
        ,from.getCity(),from.getDistrict(),from.getPostCode());
    }
    public LikeDto likeConvert(Like from){
        return new LikeDto(from.getId(), from.getCount(), productConvert(from.getProduct()),userConvert(from.getUser()));
    }
}

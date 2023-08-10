package com.rf.ecommerce.Controller.Product;

import com.rf.ecommerce.Dto.Product.CommentDto;
import com.rf.ecommerce.Entity.Product.Comment;
import com.rf.ecommerce.Entity.Product.Product;
import com.rf.ecommerce.Entity.User.User;
import com.rf.ecommerce.Service.Product.CommentService;
import com.rf.ecommerce.Service.Product.ProductService;
import com.rf.ecommerce.Service.User.UserService;
import com.rf.ecommerce.error.ApiError;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    CommentService commentService;

    // yorum ekleme
    @PostMapping("/addComment/{email}/{productId}")
    public ResponseEntity<?> addComment(@PathVariable String email, @PathVariable Long productId, @Valid @RequestBody Comment comment){
    boolean addToComment= commentService.addToComment(email,productId,comment);
        if(addToComment){
            return ResponseEntity.ok("Yorum eklendi");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kullanici veya paylaşım bulunamadı");
    }

    // bir paylaşıma ait yorumları getirme
    @GetMapping("getComments/{productId}")
    public List<CommentDto> getComments(@PathVariable Long productId){
        return commentService.getToComments(productId);
    }
    // yorum silme
    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId){
        boolean deleteToComment= commentService.deleteToComment(commentId);
        if(deleteToComment){
            return ResponseEntity.ok("Yorum Silindi");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Yorum Bulunamdı");
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError sendError(MethodArgumentNotValidException exception){
        ApiError apiError=new ApiError(400,"Hata","/api/product");
        Map<String,String> validationErross=new HashMap<>();
        for(FieldError fieldError: exception.getBindingResult().getFieldErrors()){
            validationErross.put(fieldError.getField(),fieldError.getDefaultMessage());
            apiError.setValidationErrors(validationErross);
        }
        return  apiError;
    }
}
package com.commercer.shop.controller;


import com.commercer.shop.model.Category;
import com.commercer.shop.model.Product;
import com.commercer.shop.service.CategoryService;
import com.commercer.shop.service.ProductService;
import com.commercer.shop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.awt.print.Pageable;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String getProductByCategory(Model model, @RequestParam("category") int id,  @RequestParam("page") String page,  @RequestParam(value = "limit", required = false) String limit){

        if (limit==null)
            limit ="8";
        List<Product> productsPage = productService.findProductByCategory(id, Integer.parseInt(page)-1, Integer.parseInt(limit));
        List<Category> categories = categoryService.findAll();

        List<Product> products = productService.findAllProductByCategory(id);

        int totalPage = products.size()/Integer.parseInt(limit);


        model.addAttribute("totalPage",totalPage);
        model.addAttribute("products", productsPage);
        model.addAttribute("categories", categories);


        return "public/category";
    }

    @GetMapping("/{id}")
    public String product(Model model, @PathVariable int id){
        Product product = productService.findProductById(id);


        model.addAttribute("product", product);
        model.addAttribute("products", productService.findOtherProduct());
        model.addAttribute("reviews", reviewService.findReviewByProductId(id));
        return "public/product-details";
    }


    @GetMapping("/shop")
    public String trending(Model model,@RequestParam("type") String type, @RequestParam("page") String page,  @RequestParam(value = "limit", required = false) String limit){


        if (limit == null || page == null){
            limit = "8";
            page = "1";
        }
        List<Product> products= null;
    if (type .equals( "trending")){
        products = productService.findProductByPage(Integer.parseInt(page)-1, Integer.parseInt(limit));
    }else {
        products = productService.findSaleProductByPage(Integer.parseInt(page)-1, Integer.parseInt(limit));
    }

        int totalPage =  productService.findAllProduct().size()/Integer.parseInt(limit);

        model.addAttribute("products", products);

        model.addAttribute("totalPage",totalPage);


        return "public/product";
    }



}

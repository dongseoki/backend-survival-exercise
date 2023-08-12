package com.example.onlineshop.controllers.admin;

import com.example.onlineshop.dtos.AdminCreateProductDto;
import com.example.onlineshop.dtos.AdminProductDetailDto;
import com.example.onlineshop.dtos.AdminProductListDto;
import com.example.onlineshop.dtos.AdminUpdateProductDto;
import com.example.onlineshop.models.CategoryId;
import com.example.onlineshop.models.Image;
import com.example.onlineshop.models.ImageId;
import com.example.onlineshop.models.Money;
import com.example.onlineshop.models.ProductId;
import com.example.onlineshop.models.ProductOption;
import com.example.onlineshop.models.ProductOptionId;
import com.example.onlineshop.models.ProductOptionItem;
import com.example.onlineshop.models.ProductOptionItemId;
import com.example.onlineshop.security.AdminRequired;
import com.example.onlineshop.services.CreateProductService;
import com.example.onlineshop.services.GetProductDetailService;
import com.example.onlineshop.services.GetProductListService;
import com.example.onlineshop.services.UpdateProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AdminRequired
@RestController
@RequestMapping("/admin/products")
public class AdminProductController {
  private final GetProductListService getProductListService;

  private final GetProductDetailService getProductDetailService;

  private final CreateProductService createProductService;

  private final UpdateProductService updateProductService;

  public AdminProductController(GetProductListService getProductListService,
                                GetProductDetailService getProductDetailService,
                                CreateProductService createProductService,
                                UpdateProductService updateProductService) {
    this.getProductListService = getProductListService;
    this.getProductDetailService = getProductDetailService;
    this.createProductService = createProductService;
    this.updateProductService = updateProductService;
  }

  @GetMapping
  public AdminProductListDto list() {
    return getProductListService.getAdminProductListDto();
  }

  @GetMapping("/{id}")
  public AdminProductDetailDto detail(@PathVariable String id) {
    ProductId productId = new ProductId(id);
    return getProductDetailService.getAdminProductDetailDto(productId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public String create(@Valid @RequestBody AdminCreateProductDto productDto) {
    createProductService.createProduct(
        new CategoryId(productDto.categoryId()),
        mapToImages(productDto.images()),
        productDto.name(),
        new Money(productDto.price()),
        mapToProductOptions(productDto.options()),
        productDto.description()
    );
    return "Created";
  }

  @PatchMapping("/{id}")
  public String update(
      @PathVariable String id,
      @Valid @RequestBody AdminUpdateProductDto productDto) {
//    기존 Entity를 찾아서 고치는 로직이 많아서, 여기선 DTO를 Domain Layer까지 전달하기로 함.
//
//        만약 JSON Schema가 Application Layer로 전파되는 걸 막고 싶다면, Application Layer와 Domain Layer를 위한 DTO를 따로 만들어서 변환해서 사용하면 된다
    updateProductService.updateProduct(new ProductId(id), productDto);
    return "Updated";
  }

  private List<Image> mapToImages(
      List<AdminCreateProductDto.ImageDto> imageDtos) {
    return imageDtos.stream()
                    .map(image -> new Image(ImageId.generate(), image.url()))
                    .toList();
  }

  private List<ProductOption> mapToProductOptions(
      List<AdminCreateProductDto.OptionDto> optionDtos) {
    return optionDtos.stream()
                     .map(option -> new ProductOption(
                         ProductOptionId.generate(),
                         option.name(),
                         mapToProductOptionItems(option.items())
                     ))
                     .toList();
  }

  private List<ProductOptionItem> mapToProductOptionItems(
      List<AdminCreateProductDto.OptionItemDto> optionItemDtos) {
    return optionItemDtos.stream()
                         .map(optionItem -> new ProductOptionItem(
                             ProductOptionItemId.generate(),
                             optionItem.name()
                         ))
                         .toList();
  }
}

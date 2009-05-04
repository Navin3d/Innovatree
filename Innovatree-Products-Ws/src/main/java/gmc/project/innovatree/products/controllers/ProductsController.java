package gmc.project.innovatree.shop;


@RestController
@RequestMapping("/product")
public class ProductsController {

	private final ProductsService productsService;

	public ProductsController(ProductsService productsService) {
		this.productsService = productsService;
	}

	@GetMapping
	public ResponseEntity<List<ProductModel>> shopMainPage() {
		List<Product> returnValue = productsService.getAllProducts();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnValue);
	}
}

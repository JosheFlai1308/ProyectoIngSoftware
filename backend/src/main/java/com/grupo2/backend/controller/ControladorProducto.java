

@Controller
@RequestMapping("producto")
public class ControladorProducto {
    @Autowired
	private ICrudServiceSS servicio;

	@ResponseBody
	@PostMapping("REST")
	public ProductoDTO agregarProducto(@Valid @NonNull @RequestBody ProductoDTO dto) {
		return servicio.save(dto);
	}

	@ResponseBody
	@GetMapping("REST/{id}")
	public ProductoDTO getProductoById(@PathVariable("id") int id) {
		Optional<ProductoDTO> oDto = servicio.findById(id);
		if (oDto.isPresent()) {
			ProductoDTO dto = oDto.get();
			return dto;
		} else {
			return null;
		}
	}

	@ResponseBody
	@PutMapping(("REST"))
	public ProductoDTO updateProducto(@Valid @NonNull @RequestBody ProductoDTO dto) {
		Optional<ProductoDTO> oDto = servicio.findById(dto.getId());
		if (oDto.isPresent() == true) {
			return servicio.save(dto);
		} else
			return null;
	}

	@ResponseBody
	@DeleteMapping("REST/{id}")
	public boolean deleteProductoById(@PathVariable("id") int id) {
		Optional<ProductoDTO> oDto = servicio.findById(id);
		if (oDto.isPresent() == true) {
			servicio.delete(oDto.get());
			return true;
		} else {
			return false;
		}
	}
	@ResponseBody
	@GetMapping("REST")
	public List<ProductoDTO> getAllProductos(@RequestParam(name = "search", required = false) String search) {
	    return servicio.findAll(search);
	}
}

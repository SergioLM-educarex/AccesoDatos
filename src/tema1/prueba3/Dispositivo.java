package tema1.prueba3;

public class Dispositivo {

	private int id;
	private String marca;
	private String modelo;
	private String tipo;
	private String almacenamiento;
	private String ram;
	private String color;
	private int stock;
	private double precioReacondicionado;
	private int garantiaMeses;

	public Dispositivo(int id, String marca, String modelo, String tipo, String almacenamiento, String ram,
			String color, int stock, double precioReacondicionado, int garantiaMeses) {
		super();
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.tipo = tipo;
		this.almacenamiento = almacenamiento;
		this.ram = ram;
		this.color = color;
		this.stock = stock;
		this.precioReacondicionado = precioReacondicionado;
		this.garantiaMeses = garantiaMeses;
	}

	public Dispositivo() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAlmacenamiento() {
		return almacenamiento;
	}

	public void setAlmacenamiento(String almacenamiento) {
		this.almacenamiento = almacenamiento;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrecioReacondicionado() {
		return precioReacondicionado;
	}

	public void setPrecioReacondicionado(double precioReacondicionado) {
		this.precioReacondicionado = precioReacondicionado;
	}

	public int getGarantiaMeses() {
		return garantiaMeses;
	}

	public void setGarantiaMeses(int garantiaMeses) {
		this.garantiaMeses = garantiaMeses;
	}

	@Override
	public String toString() {
		return "Dispositivo [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", tipo=" + tipo
				+ ", almacenamiento=" + almacenamiento + ", ram=" + ram + ", color=" + color + ", stock=" + stock
				+ ", precioReacondicionado=" + precioReacondicionado + ", garantiaMeses=" + garantiaMeses + "]";
	}

}

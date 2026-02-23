package tema4mapeobjetorelacional.aemet2013.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "clima_mensual")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatoClima {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Indicativo climatológico
    private String indicativo;
    
    // Año y mes (AAAA-X)
    private String fecha;

    // Precipitación máxima diaria del mes/año y fecha
    @JsonProperty("p_max")
    private String precipitacionMaxima;
    
    // Precipitación total mensual/anual
    @JsonProperty("p_mes")
    private Double precipitacionMensual;

    // Temperatura máxima absoluta del mes/año y fecha
    @JsonProperty("ta_max")
    private String temperaturaMaximaAbsoluta;
    
    // Temperatura mínima absoluta del mes/año y fecha
    @JsonProperty("ta_min")
    private String temperaturaMinimaAbsoluta;
    
    // Temperatura media mensual/anual de las máximas
    @JsonProperty("tm_max")
    private Double temperaturaMediaMaximas;
    
    // Temperatura media mensual/anual de las mínimas
    @JsonProperty("tm_min")
    private Double temperaturaMediaMinimas;
    
    // Temperatura media mensual/anual
    @JsonProperty("tm_mes")
    private Double temperatureMediaMensual;

    // Humedad relativa media mensual/anual (%)
    @JsonProperty("hr")
    private Integer humedadRelativa;
    
    // Media mensual/anual de la insolación diaria (horas)
    @JsonProperty("inso")
    private Double insolacion;

    // Presión máxima absoluta mensual/anual y fecha
    @JsonProperty("q_max")
    private String presionMaxima;
    
    // Presión mínima mensual/anual y fecha
    @JsonProperty("q_min")
    private String presionMinima;
    
    // Presión media mensual/anual al nivel de la estación (hPa)
    @JsonProperty("q_med")
    private Double presionMedia;

    // Velocidad media mensual del viento (km/h)
    @JsonProperty("w_med")
    private Integer velocidadVientoMedia;
    
    // Dirección, Velocidad y fecha de la racha máxima en el mes/año
    @JsonProperty("w_racha")
    private String rachaMaxima;

    // Constructor vacío
    public DatoClima() {}

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndicativo() {
        return indicativo;
    }

    public void setIndicativo(String indicativo) {
        this.indicativo = indicativo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPrecipitacionMaxima() {
        return precipitacionMaxima;
    }

    public void setPrecipitacionMaxima(String precipitacionMaxima) {
        this.precipitacionMaxima = precipitacionMaxima;
    }

    public Double getPrecipitacionMensual() {
        return precipitacionMensual;
    }

    public void setPrecipitacionMensual(Double precipitacionMensual) {
        this.precipitacionMensual = precipitacionMensual;
    }

    public String getTemperaturaMaximaAbsoluta() {
        return temperaturaMaximaAbsoluta;
    }

    public void setTemperaturaMaximaAbsoluta(String temperaturaMaximaAbsoluta) {
        this.temperaturaMaximaAbsoluta = temperaturaMaximaAbsoluta;
    }

    public String getTemperaturaMinimaAbsoluta() {
        return temperaturaMinimaAbsoluta;
    }

    public void setTemperaturaMinimaAbsoluta(String temperaturaMinimaAbsoluta) {
        this.temperaturaMinimaAbsoluta = temperaturaMinimaAbsoluta;
    }

    public Double getTemperaturaMediaMaximas() {
        return temperaturaMediaMaximas;
    }

    public void setTemperaturaMediaMaximas(Double temperaturaMediaMaximas) {
        this.temperaturaMediaMaximas = temperaturaMediaMaximas;
    }

    public Double getTemperaturaMediaMinimas() {
        return temperaturaMediaMinimas;
    }

    public void setTemperaturaMediaMinimas(Double temperaturaMediaMinimas) {
        this.temperaturaMediaMinimas = temperaturaMediaMinimas;
    }

    public Double getTemperatureMediaMensual() {
        return temperatureMediaMensual;
    }

    public void setTemperatureMediaMensual(Double temperatureMediaMensual) {
        this.temperatureMediaMensual = temperatureMediaMensual;
    }

    public Integer getHumedadRelativa() {
        return humedadRelativa;
    }

    public void setHumedadRelativa(Integer humedadRelativa) {
        this.humedadRelativa = humedadRelativa;
    }

    public Double getInsolacion() {
        return insolacion;
    }

    public void setInsolacion(Double insolacion) {
        this.insolacion = insolacion;
    }

    public String getPresionMaxima() {
        return presionMaxima;
    }

    public void setPresionMaxima(String presionMaxima) {
        this.presionMaxima = presionMaxima;
    }

    public String getPresionMinima() {
        return presionMinima;
    }

    public void setPresionMinima(String presionMinima) {
        this.presionMinima = presionMinima;
    }

    public Double getPresionMedia() {
        return presionMedia;
    }

    public void setPresionMedia(Double presionMedia) {
        this.presionMedia = presionMedia;
    }

    public Integer getVelocidadVientoMedia() {
        return velocidadVientoMedia;
    }

    public void setVelocidadVientoMedia(Integer velocidadVientoMedia) {
        this.velocidadVientoMedia = velocidadVientoMedia;
    }

    public String getRachaMaxima() {
        return rachaMaxima;
    }

    public void setRachaMaxima(String rachaMaxima) {
        this.rachaMaxima = rachaMaxima;
    }

    @Override
    public String toString() {
        return "DatoClima [id=" + id + ", indicativo=" + indicativo + ", fecha=" + fecha + 
               ", tempMedia=" + temperatureMediaMensual + "°C, precip=" + 
               precipitacionMensual + "mm, hr=" + humedadRelativa + "%]";
    }
}
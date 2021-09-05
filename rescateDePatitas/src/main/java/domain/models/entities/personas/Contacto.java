package domain.models.entities.personas;

import domain.models.entities.Persistente;
import domain.models.entities.notificaciones.MetodoDeEnvio;
import domain.models.entities.notificaciones.estrategias.Estrategia;
import domain.models.entities.notificaciones.estrategias.NotificadorEmail;
import domain.models.entities.notificaciones.estrategias.NotificadorSMS;
import domain.models.entities.notificaciones.estrategias.NotificadorWhatsapp;
import domain.models.entities.notificaciones.estrategias.adapters.email.AdapterJavaMailEmail;
import domain.models.entities.notificaciones.estrategias.adapters.sms.AdapterTwilioSMS;
import domain.models.entities.notificaciones.estrategias.adapters.wpp.AdapterTwilioWhatsapp;

import javax.persistence.*;
import java.io.IOException;

@Entity
@Table(name = "contacto")
public class Contacto extends Persistente {
    @ManyToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona persona;
    // Atributos
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "telefono")
    private String numeroCompleto;
    @Column(name = "email")
    private String email;
    // Como lo llevo a la base? Es un ENUM
    @Transient
    private Estrategia estrategiaDeEnvio;
    @Transient
    private MetodoDeEnvio metodoDeEnvio;
    @Transient
    private String mensaje;

    public Contacto(String nombre, String apellido, String numeroCompleto, String email, Estrategia estrategiaDeEnvio) {
        setNombre(nombre);
        setApellido(apellido);
        setNumeroCompleto(numeroCompleto);
        setEmail(email);
        setEstrategiaDeEnvio(estrategiaDeEnvio);
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void notificarContacto(String mensaje) {
        setMensaje(mensaje);
        enviarNotificacion();
    }

    public void enviarNotificacion() {
        metodoDeEnvio.enviarNotificacion(this);
    }

    public void setEstrategiaDeEnvio(Estrategia estrategiaDeEnvio) {
        this.estrategiaDeEnvio = estrategiaDeEnvio;
        try {
            setMetodoDeEnvio(estrategiaDeEnvio);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMensaje(String mensaje) {
        this.mensaje = "Hola " + nombre + ", " + mensaje;
    }

    public void setMetodoDeEnvio(Estrategia estrategiaDeEnvio) throws IOException {
        switch (estrategiaDeEnvio) {
            case SMS:
                AdapterTwilioSMS adapterTwilioSMS = new AdapterTwilioSMS();
                NotificadorSMS notificadorSMS = new NotificadorSMS(adapterTwilioSMS);
                MetodoDeEnvio metodoDeEnvioSMS = new MetodoDeEnvio(notificadorSMS);
                this.metodoDeEnvio = metodoDeEnvioSMS;
                break;
            case WHATSAPP:
                AdapterTwilioWhatsapp adapterTwilioWhatsapp = new AdapterTwilioWhatsapp();
                NotificadorWhatsapp notificadorWhatsapp = new NotificadorWhatsapp(adapterTwilioWhatsapp);
                MetodoDeEnvio metodoDeEnvioWhatsapp = new MetodoDeEnvio(notificadorWhatsapp);
                this.metodoDeEnvio = metodoDeEnvioWhatsapp;
                break;
            case EMAIL:
                AdapterJavaMailEmail adapterJavaMailEmail = new AdapterJavaMailEmail("src/main/resources/configuration.prop",
                        "Notificación de Patitas ✨");
                NotificadorEmail notificadorEmail = new NotificadorEmail(adapterJavaMailEmail);
                MetodoDeEnvio metodoDeEnvioEmail = new MetodoDeEnvio(notificadorEmail);
                this.metodoDeEnvio = metodoDeEnvioEmail;
                break;
            default:
                System.out.println("El valor ingresado es invalido");
        }
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumeroCompleto(String numeroCompleto) {
        this.numeroCompleto = numeroCompleto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNumeroCompleto() {
        return numeroCompleto;
    }

    public String getEmail() {
        return email;
    }

    public String getMensaje() {
        return mensaje;
    }
}

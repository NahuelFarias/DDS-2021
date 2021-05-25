package domain.notificaciones.estrategias.adapters.email;

import domain.notificaciones.Contacto;

public class AdapterMailgunEmail implements AdapterNotificadorEmail{
    public void enviarEmail(Contacto contacto) {
        System.out.println("Enviando email a "+ contacto.getEmail()+" por Mailgun: '"+ contacto.getMensaje()+"'");
    }
}

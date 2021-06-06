package domain.models.entities.notificaciones.estrategias.adapters.email;

import domain.models.entities.notificaciones.Notificacion;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.Properties;


public class AdapterJavaMailEmail implements AdapterNotificadorEmail{
    private Properties properties;
    private Session session;
    private String asunto;

    public AdapterJavaMailEmail(String ruta, String asunto) throws IOException {
        setAsunto(asunto);
        this.properties = new Properties();
        loadCongig(ruta);

        session = Session.getDefaultInstance(properties);
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    private void loadCongig(String ruta) throws IOException {
        InputStream is = new FileInputStream(ruta);
        this.properties.load(is);
        checkConfig();
    }

    private void checkConfig() {
        String[] keys = {
                "mail.smtp.host",
                "mail.smtp.port",
                "mail.smtp.user",
                "mail.smtp.password",
                "mail.smtp.starttls.enable",
                "mail.smtp.auth"
        };

        for (int i = 0; i < keys.length; i++) {
            if(this.properties.get(keys[i]) == null) {
                throw new InvalidParameterException("No existe la clave " + keys[i]);
            }
        }
    }

    public void enviarEmail(Notificacion notificacion) throws MessagingException {
        System.out.println("Enviando email a "+ notificacion.getEmail()+" por JavaMail: '"+ notificacion.getMensaje()+"'");

        MimeMessage contenedor = new MimeMessage(session);
        contenedor.setFrom(new InternetAddress((String) this.properties.get("mail.smtp.user")));
        contenedor.addRecipient(Message.RecipientType.TO, new InternetAddress(notificacion.getEmail()));
        contenedor.setSubject(asunto);
        contenedor.setText(notificacion.getMensaje());
        Transport t = session.getTransport("smtp");
        t.connect((String) this.properties.get("mail.smtp.user"), (String) this.properties.get("mail.smtp.password"));
        t.sendMessage(contenedor, contenedor.getAllRecipients());
    }
}

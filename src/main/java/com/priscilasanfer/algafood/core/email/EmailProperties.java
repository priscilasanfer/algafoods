package com.priscilasanfer.algafood.core.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@ConfigurationProperties("algafoods.email")
@Component
@Getter
@Setter
@Validated
public class EmailProperties {

    @NotNull
    private String remetente;

    private Implementacao impl = Implementacao.SANDBOX;

    private Sandbox sandbox = new Sandbox();

    public enum Implementacao {
        SMTP, FAKE, SANDBOX
    }

    @Getter
    @Setter
    public class Sandbox {

        private String destinatario;

    }
}

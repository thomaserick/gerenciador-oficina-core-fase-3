package com.fiap.pj.core.email.app;

import com.fiap.pj.core.email.app.gateways.EmailGateway;
import com.fiap.pj.core.email.app.usecase.EnviarEmailUseCase;
import com.fiap.pj.core.email.app.usecase.command.EnviarEmailCommand;
import com.fiap.pj.core.email.domain.EmailTemplate;
import com.fiap.pj.core.email.exception.EmailTemplateExceptions.EmailTemplateNaoEncontradoException;
import com.fiap.pj.core.email.exception.EmailTemplateExceptions.EmailTemplateNaoFoiPossivelEnviarEmailException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EnviarEmailUseCaseImpl implements EnviarEmailUseCase {

    public static final String UTF_8_ENCONDING = "UTF-8";

    private final EmailGateway emailGateway;
    private final JavaMailSender mailSender;

    public EnviarEmailUseCaseImpl(EmailGateway emailGateway, JavaMailSender mailSender) {
        this.emailGateway = emailGateway;
        this.mailSender = mailSender;
    }

    @Override
    public void handle(EnviarEmailCommand cmd) {
        try {
            EmailTemplate emailTemplate = this.emailGateway.buscarTemplate(cmd.template())
                    .orElseThrow(() -> new EmailTemplateNaoEncontradoException());

            MimeMessage message = this.mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCONDING);

            helper.setTo(cmd.destinatario());
            helper.setSubject(emailTemplate.getAssunto());
            helper.setText(
                    String.format(emailTemplate.getCorpo(), cmd.args().toArray()),
                    true
            );

            this.mailSender.send(message);
        } catch (Exception e) {
            throw new EmailTemplateNaoFoiPossivelEnviarEmailException(
                    "Não foi possível enviar o email ao destinatário.",
                    e
            );
        }
    }
}

package se.lexicon.emailsenderapi.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import se.lexicon.emailsenderapi.config.EmailProperties;
import se.lexicon.emailsenderapi.domain.dto.EmailDTO;
import se.lexicon.emailsenderapi.domain.entity.Email;
import se.lexicon.emailsenderapi.exception.EmailException;
import se.lexicon.emailsenderapi.repository.EmailRepository;

@Service
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;
    private final JavaMailSender javaMailSender;
    private final EmailProperties emailProperties;

    @Autowired
    public EmailServiceImpl(EmailRepository emailRepository, JavaMailSender javaMailSender, EmailProperties emailProperties) {
        this.emailRepository = emailRepository;
        this.javaMailSender = javaMailSender;
        this.emailProperties = emailProperties;
    }

    @Override
    public void sendEmail(EmailDTO emailDTO) {
        //todo Implement the manin logic for sending an email
        if (emailDTO == null) throw new IllegalArgumentException("Email is null.");

        //todo: send the email
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            mimeMessage.setContent(emailDTO.getHtml(), "text/html");
            helper.setTo(emailDTO.getTo());
            helper.setFrom(emailProperties.getUsername());
            helper.setSubject(emailDTO.getSubject());
            //convert dto to entity and save to database
            Email entity = Email.builder()
                    .to(emailDTO.getTo())
                    .from(emailProperties.getUsername())
                    .subject(emailDTO.getSubject())
                    .content(emailDTO.getHtml())
                    .type(emailDTO.getType())
                    .build();
            emailRepository.save(entity);
            //send the email
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new EmailException("Error sending email." + e.getMessage(), e);
        }
    }
    }


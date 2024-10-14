package se.lexicon.emailsenderapi.service;

import org.springframework.stereotype.Service;
import se.lexicon.emailsenderapi.domain.dto.EmailDTO;

@Service
public interface EmailService {
    void sendEmail(EmailDTO emailDTO);
}

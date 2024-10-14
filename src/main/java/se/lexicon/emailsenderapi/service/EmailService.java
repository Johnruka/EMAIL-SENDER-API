package se.lexicon.emailsenderapi.service;

import se.lexicon.emailsenderapi.domain.dto.EmailDTO;

public interface EmailService {
    void sendEmail(EmailDTO emailDTO);
}

package com.servingwebcontent.repos;

import com.servingwebcontent.models.MailAd;
import org.springframework.data.repository.CrudRepository;

public interface MailRepository extends CrudRepository<MailAd, Integer> {
}

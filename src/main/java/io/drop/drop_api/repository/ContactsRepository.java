package io.drop.drop_api.repository;

import io.drop.drop_api.models.entities.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRepository extends JpaRepository<Contacts, Integer> {
}

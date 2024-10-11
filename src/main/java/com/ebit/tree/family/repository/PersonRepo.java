package com.ebit.tree.family.repository;

import com.ebit.tree.family.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {
    List<Person> findByMotherIdOrFatherId(Long motherId, Long fatherId);

    List<Person> findByChildrenFromFather(Person childrenFromFather);
}

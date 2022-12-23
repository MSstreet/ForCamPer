package com.examplm.camp.dao;

import com.examplm.camp.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;

public interface UserRepository extends JpaRepository<User,Long> {
}

package com.examplm.camp.dao;

import com.examplm.camp.dao.serch.CampSearch;
import com.examplm.camp.domain.CampSite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampRepository extends JpaRepository<CampSite, Long>, CampSearch {
}

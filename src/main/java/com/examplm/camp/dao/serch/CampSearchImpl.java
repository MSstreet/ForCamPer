package com.examplm.camp.dao.serch;

import com.examplm.camp.domain.CampSite;
import com.examplm.camp.domain.QCampSite;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CampSearchImpl extends QuerydslRepositorySupport implements CampSearch {

    public CampSearchImpl(){
        super(CampSite.class);
    }

    @Override
    public Page<CampSite> search1(Pageable pageable){

        QCampSite campSite = QCampSite.campSite;

        JPQLQuery<CampSite> query = from(campSite);

        query.where(campSite.facltNm.contains("1"));

        this.getQuerydsl().applyPagination(pageable, query);

        List<CampSite> list = query.fetch();

        long count = query.fetchCount();

        return null;
    }

    @Override
    public Page<CampSite> searchAll(String[] types, String keyword, Pageable pageable){

        QCampSite campSite = QCampSite.campSite;
        JPQLQuery<CampSite> query = from(campSite);

        if((types != null && types.length > 0) && keyword != null){

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type: types){

                switch (type){
                    case "n": //캠핑장 이름
                        booleanBuilder.or(campSite.facltNm.contains(keyword));
                        break;
                    case "a": //캠핑장 주소
                        booleanBuilder.or(campSite.addr1.contains(keyword));
                        break;
                    case "s": //캠핑장 부대시설
                        booleanBuilder.or(campSite.sbrsCl.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        query.where(campSite.campNum.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);

        List<CampSite> list = query.fetch();

        long count = query.fetchCount();

        //return null;
        return new PageImpl<>(list, pageable, count);
    }
}

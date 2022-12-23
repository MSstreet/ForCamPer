package com.examplm.camp.dao.serch;

import com.examplm.camp.domain.Board;
import com.examplm.camp.domain.CampSite;
import com.examplm.camp.domain.QBoard;
import com.examplm.camp.domain.QCampSite;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch{

    public BoardSearchImpl(){
        super(Board.class);
    }

    @Override
    public Page<Board> search2(Pageable pageable){

        QBoard Board = QBoard.board;

        JPQLQuery<Board> query = from(Board);

        query.where(Board.title.contains("1"));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return null;
    }

    @Override
    public Page<Board> searchAll1(String[] types, String keyword, Pageable pageable){

        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        if((types != null && types.length > 0) && keyword != null){

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type: types){

                switch (type){
                    case "t": //캠핑장 이름
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c": //캠핑장 주소
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w": //캠핑장 부대시설
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        query.where(board.boardId.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        //return null;
        return new PageImpl<>(list, pageable, count);
    }

}

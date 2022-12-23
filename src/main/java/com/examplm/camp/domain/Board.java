package com.examplm.camp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

//    @ManyToOne
//    @JoinColumn(name="writer", nullable=false)
//    @OnDelete(action= OnDeleteAction.CASCADE)
//    private User writer;

    @Column(nullable = false)
    private String writer;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    private boolean deleteYn;

    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }
}

package com.hao.demo.dao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Builder
@ToString
@Entity
@DynamicInsert
@DynamicUpdate
public class User {

    @Id
    private Long id;

    private String email;

    private String userName;

    private String userPass;

    private String salt;


}

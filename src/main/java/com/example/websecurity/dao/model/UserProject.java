package com.example.websecurity.dao.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity(name = "USER_PROJECT")
@Table(name = "USER_PROJECT")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProject implements Serializable {

    @EmbeddedId
    private UserProjectId id;

    @Column(name = "is_user_approved", nullable = false)
    private boolean isUserApprovedForProject;

    @Column(name = "is_user_ovner", nullable = false)
    private boolean isUserOwner;
}

package com.example.websecurity.dao.query;

public interface RoleQuery {

    String FIND_BY_NAME = """
        SELECT
            r.*
        FROM
            role r
        WHERE
            r.role_name = :name
        """;
}

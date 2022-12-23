package com.example.websecurity.dao.query;

public interface UserQuery {

    String FIND_BY_USERNAME = """
                    SELECT *
                    FROM make_it.user usr
                    WHERE usr.username   LIKE CONCAT('%', :username, '%')
            """;

    String FIND_BY_ROLE = """
              SELECT *
                    FROM make_it.user usr
                    WHERE usr.ROLE_id   LIKE CONCAT('%', :roleId, '%')
            """;
}

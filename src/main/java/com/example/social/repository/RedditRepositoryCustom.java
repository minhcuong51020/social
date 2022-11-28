package com.example.social.repository;

import com.example.social.dto.request.RedditSearchRequest;
import com.example.social.entity.RedditEntity;
import com.hmc.common.persistence.support.SqlUtils;
import com.hmc.common.util.StrUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RedditRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public List<RedditEntity> search(RedditSearchRequest request) {
        Map<String, Object> values = new HashMap<>();
        StringBuilder sql = new StringBuilder("SELECT R FROM RedditEntity R ");
        sql.append(createWhereQuery(request, values));
        sql.append(createOrderQuery(request.getSortBy()));
        Query query = entityManager.createQuery(sql.toString(), RedditEntity.class);
        values.forEach(query::setParameter);
        query.setFirstResult((request.getPageIndex() - 1) * request.getPageSize());
        query.setMaxResults(request.getPageSize());
        return query.getResultList();
    }

    public Long count(RedditSearchRequest request) {
        Map<String, Object> values = new HashMap<>();
        StringBuilder sql = new StringBuilder("SELECT COUNT(R) FROM RedditEntity R ");
        sql.append(createWhereQuery(request, values));
        Query query = entityManager.createQuery(sql.toString(), Long.class);
        values.forEach(query::setParameter);
        return (Long) query.getSingleResult();
    }

    private String createOrderQuery(String sortBy) {
        StringBuilder sql = new StringBuilder(" ");
        if(StringUtils.hasLength(sortBy)){
            sql.append("ORDER BY R.").append(sortBy.replace("."," "));
        }else {
            sql.append("ORDER BY R.nameDisplay desc ");
        }
        return  sql.toString();
    }

    private String createWhereQuery(RedditSearchRequest request, Map<String, Object> values) {
        StringBuilder sql = new StringBuilder();
        sql.append("WHERE 1 = 1 ");
        sql.append("AND R.deleted = false ");
        if(!StrUtils.isBlank(request.getKeyword())) {
            sql.append("AND (R.nameDisplay like :nameDisplay ");
            values.put("nameDisplay", SqlUtils.encodeKeyword(request.getKeyword()));
            sql.append("OR R.username like :username) ");
            values.put("username", SqlUtils.encodeKeyword(request.getKeyword()));
        }
        return sql.toString();
    }

}

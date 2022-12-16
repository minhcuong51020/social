package com.example.social.repository;

import com.example.social.dto.request.TwitterSearchRequest;
import com.example.social.entity.TwitterEntity;
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
public class TwitterRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public List<TwitterEntity> search(TwitterSearchRequest request, String ownerId) {
        Map<String, Object> values = new HashMap<>();
        StringBuilder sql = new StringBuilder("SELECT R FROM TwitterEntity R ");
        sql.append(createWhereQuery(request, values, ownerId));
        sql.append(createOrderQuery(request.getSortBy()));
        Query query = entityManager.createQuery(sql.toString(), TwitterEntity.class);
        values.forEach(query::setParameter);
        query.setFirstResult((request.getPageIndex() - 1) * request.getPageSize());
        query.setMaxResults(request.getPageSize());
        return query.getResultList();
    }

    public Long count(TwitterSearchRequest request, String ownerId) {
        Map<String, Object> values = new HashMap<>();
        StringBuilder sql = new StringBuilder("SELECT COUNT(R) FROM TwitterEntity R ");
        sql.append(createWhereQuery(request, values, ownerId));
        Query query = entityManager.createQuery(sql.toString(), Long.class);
        values.forEach(query::setParameter);
        return (Long) query.getSingleResult();
    }

    private String createOrderQuery(String sortBy) {
        StringBuilder sql = new StringBuilder(" ");
        if(StringUtils.hasLength(sortBy)){
            sql.append("ORDER BY R.").append(sortBy.replace("."," "));
        }else {
            sql.append("ORDER BY R.id desc ");
        }
        return  sql.toString();
    }

    private String createWhereQuery(TwitterSearchRequest request, Map<String, Object> values, String ownerId) {
        StringBuilder sql = new StringBuilder();
        sql.append("WHERE 1 = 1 ");
        sql.append("AND R.deleted = false ");
        if(!StrUtils.isBlank(request.getKeyword())) {
            sql.append("AND LOWER(R.name) like :name ");
            values.put("name", SqlUtils.encodeKeyword(request.getKeyword()).toLowerCase());
        }
        sql.append("AND R.ownerId = :ownerId");
        values.put("ownerId", ownerId);
        return sql.toString();
    }

}

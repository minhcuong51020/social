package com.example.social.repository;

import com.example.social.dto.request.RedditGroupSearchRequest;
import com.example.social.entity.RedditGroupEntity;
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
public class RedditGroupRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public List<RedditGroupEntity> search(RedditGroupSearchRequest request) {
        Map<String, Object> values = new HashMap<>();
        StringBuilder sql = new StringBuilder("SELECT R FROM RedditGroupEntity R ");
        sql.append(createWhereQuery(request, values));
        sql.append(createOrderQuery(request.getSortBy()));
        Query query = entityManager.createQuery(sql.toString(), RedditGroupEntity.class);
        values.forEach(query::setParameter);
        query.setFirstResult((request.getPageIndex() - 1) * request.getPageSize());
        query.setMaxResults(request.getPageSize());
        return query.getResultList();
    }

    public Long count(RedditGroupSearchRequest request) {
        Map<String, Object> values = new HashMap<>();
        StringBuilder sql = new StringBuilder("SELECT COUNT(R) FROM RedditGroupEntity R ");
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
            sql.append("ORDER BY R.name desc ");
        }
        return  sql.toString();
    }

    private String createWhereQuery(RedditGroupSearchRequest request, Map<String, Object> values) {
        StringBuilder sql = new StringBuilder();
        sql.append("WHERE 1 = 1 ");
        sql.append("AND R.deleted = false ");
        if(!StrUtils.isBlank(request.getKeyword())) {
            sql.append("AND (LOWER(R.name) like :name ");
            values.put("name", SqlUtils.encodeKeyword(request.getKeyword()).toLowerCase());
            sql.append("OR LOWER(R.nameUrl) like :nameUrl) ");
            values.put("nameUrl", SqlUtils.encodeKeyword(request.getKeyword()).toLowerCase());
        }
        return sql.toString();
    }

}

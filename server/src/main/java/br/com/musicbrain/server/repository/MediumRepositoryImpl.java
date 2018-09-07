package br.com.musicbrain.server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.musicbrain.server.domain.Medium;

@Repository
public class MediumRepositoryImpl implements MediumRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public Medium getMediumById(int mediumId) {
		String sql = "SELECT m.name, m.release, m.id, m.last_updated FROM Medium as m WHERE m.id = ?";
		RowMapper<Medium> rowMapper = new BeanPropertyRowMapper<Medium>(Medium.class);
		Medium article = jdbcTemplate.queryForObject(sql, rowMapper, mediumId);
		return article;
    }
} 
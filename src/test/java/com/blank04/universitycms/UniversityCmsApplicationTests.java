package com.blank04.universitycms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(
		scripts = {"/sql/clear_tables.sql"},
		executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class UniversityCmsApplicationTests {

	@Test
	void contextLoads() {
	}

}

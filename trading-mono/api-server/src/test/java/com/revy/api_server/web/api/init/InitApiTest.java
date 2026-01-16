package com.revy.api_server.web.api.init;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
@DisplayName("InitApi 컨트롤러 단위 테스트")
class InitApiTest {

    @Test
    @DisplayName("init 엔드포인트는 문자열을 반환한다")
    void init_returnsString() {
        InitApi initApi = new InitApi();

        String result = initApi.init();

        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("init");
    }
}

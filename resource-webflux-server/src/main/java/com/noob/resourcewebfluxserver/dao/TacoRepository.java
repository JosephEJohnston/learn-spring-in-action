package com.noob.resourcewebfluxserver.dao;

import com.noob.commons.model.Taco;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

// 这玩意其实不能用，jpa 和 reactive 不兼容，作者老哥可能根本没测试过
public interface TacoRepository extends ReactiveCrudRepository<Taco, Long> {

}

package com.zsc.hahamall.member.feign;


import com.zsc.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * <p>Title: OrderFeignService</p>
 * Description：
 * date：2021/7/4 23:43
 */
@FeignClient("mall-order")
public interface OrderFeignService {

	@PostMapping("/order/order/listWithItem")
	R listWithItem(@RequestBody Map<String, Object> params);
}

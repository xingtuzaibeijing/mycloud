package org.qhs.mycloud.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author QianHuaSheng
 * @version V1.0
 * @description
 * @date 2021/12/30
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@TableName("test")
public class Test {

	private Integer id;

	private String name;

	private Long no;
}

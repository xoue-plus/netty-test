package com.ne.sne.component.netty.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * ClassName: TransBean
 * Description:
 * date: 2021/5/25 14:13
 *
 * @author User
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransBean {
    private Integer id;
    private String name;
    private List<String> nicks;
}

package com.ne.sne.component.netty.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomProtocol implements Serializable {
    private static final long serialVersionUID = 4671171056588401542L;
    private long id ;
    private String content ;
}
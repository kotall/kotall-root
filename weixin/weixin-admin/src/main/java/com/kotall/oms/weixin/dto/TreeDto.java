package com.kotall.oms.weixin.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zpwang
 * @version 1.0.0
 */
@Data
public class TreeDto<T> {

    T root;

    private List<TreeDto<T>> subList = new ArrayList<>();
}

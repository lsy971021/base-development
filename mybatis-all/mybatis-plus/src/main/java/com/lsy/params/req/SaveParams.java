package com.lsy.params.req;

import lombok.Data;

@Data
public class SaveParams {
    String id;
    String province;

    @Override
    public String toString() {
        return "SaveParams{" +
                "id='" + id + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
